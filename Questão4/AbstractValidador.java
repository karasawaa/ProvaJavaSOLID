import java.util.concurrent.*;

/**
 * Esta classe é o cérebro da nossa cadeia. Utilizando Chain of Responsibility (Implementação Abstrata do Elo)
 * Ela usa o padrão CoR e o "sobrecarrega" para atender aos requisitos
 * complexos que um CoR simples não conseguiria.
 *
 * COMO AS REGRAS SÃO ATENDIDAS:
 * 1. Chain (Cadeia): Através do campo 'proximo' e do método 'setNext'.
 * 2. Circuit Breaker: O método 'executar' verifica o 'contexto.getContadorFalhas()'
 * antes de fazer qualquer coisa.
 * 3. Condicional: O método 'podeExecutar()' (implementado por subclasses)
 * permite pular a lógica.
 * 4. Timeout: O método 'executarComTimeout' usa um 'ExecutorService' e 'Future'
 * para impor um limite de tempo.
 * 5. Rollback: O método 'executar' retorna um booleano. Se 'sucessoProximo'
 * ou 'sucessoMeu' for falso, ele chama 'this.rollback()'.
 */
public abstract class AbstractValidador implements Validador {

    protected Validador proximo;
    protected final String nome;
    protected final long timeoutMillis;
    
    // Um pool de threads estático para gerenciar todos os timeouts
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public AbstractValidador(String nome, long timeoutMillis) {
        this.nome = nome;
        this.timeoutMillis = timeoutMillis;
    }

    @Override
    public void setNext(Validador proximo) {
        this.proximo = proximo;
    }

    /**
     * Método principal que gerencia a execução da cadeia.
     */
    @Override
    public boolean executar(ContextoValidacao contexto) {
        // 1. REGRA: CIRCUIT BREAKER
        if (contexto.getContadorFalhas() >= 3) {
            System.err.println("CIRCUIT BREAKER ATIVADO! Parando a cadeia em [" + nome + "]");
            return false; // Falha imediata
        }

        // 2. REGRA: VALIDAÇÃO CONDICIONAL
        if (!podeExecutar(contexto)) {
            System.out.println(">>> Pulando validador: " + nome + " (condição não atendida)");
            // Se pulamos, o sucesso depende apenas do resto da cadeia
            return chamarProximo(contexto);
        }

        System.out.println("--- Executando: " + nome + " ---");

        // 3. REGRA: TIMEOUT e EXECUÇÃO
        boolean sucessoMeu = executarComTimeout(contexto);
        
        // Registra o status para validações condicionais futuras
        contexto.registrarStatus(nome, sucessoMeu);

        // 4. CHAMADA DO PRÓXIMO ELO (CoR)
        boolean sucessoProximo = chamarProximo(contexto);

        // 5. REGRA: ROLLBACK
        boolean sucessoTotal = sucessoMeu && sucessoProximo;
        
        if (!sucessoTotal) {
            // Se eu ou qualquer um depois de mim falhou, eu rodo meu rollback
            System.out.println("Rollback acionado para: " + nome + " (SucessoMeu: " + sucessoMeu + ", SucessoProximo: " + sucessoProximo + ")");
            rollback(contexto);
        }

        return sucessoTotal;
    }

    /**
     * Helper para executar a validação real com controle de timeout.
     */
    private boolean executarComTimeout(ContextoValidacao contexto) {
        Future<Boolean> future = executor.submit(() -> {
            try {
                validar(contexto); // Método abstrato implementado pelo filho
                return true; // Sucesso
            } catch (ValidacaoException e) {
                contexto.adicionarFalha(nome, e.getMessage());
                return false; // Falha de validação
            }
        });

        try {
            // 3. REGRA: TIMEOUT INDIVIDUAL
            return future.get(this.timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true); // Interrompe a thread
            contexto.adicionarFalha(nome, "TIMEOUT (" + this.timeoutMillis + "ms)");
            return false;
        } catch (Exception e) {
            contexto.adicionarFalha(nome, "Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper para chamar o próximo elo da cadeia.
     */
    private boolean chamarProximo(ContextoValidacao contexto) {
        if (proximo != null) {
            return proximo.executar(contexto); // Passa para o próximo elo
        }
        return true; // Chegamos ao fim da cadeia sem falhas
    }

    /**
     * Desliga o pool de threads. Deve ser chamado no fim do programa.
     */
    public static void shutdownExecutor() {
        executor.shutdownNow();
    }
    
    // --- Métodos Abstratos para Subclasses ---

    /**
     * REGRA CONDICIONAL: Subclasses implementam esta lógica.
     */
    public abstract boolean podeExecutar(ContextoValidacao contexto);

    /**
     * A lógica de validação específica deste elo.
     */
    public abstract void validar(ContextoValidacao contexto) throws ValidacaoException;

    /**
     * REGRA DE ROLLBACK: Por padrão, não faz nada.
     * Subclasses (como a de BD) devem sobrescrever.
     */
    @Override
    public void rollback(ContextoValidacao contexto) {
        // Por padrão, validadores não têm rollback.
    }
}