/**
 * Implementação do estado 'Alerta Amarelo'.
 *
 * Escolha do design:
 * - Padrão State (Estado Concreto): Gerencia o comportamento em
 * ALERTA_AMARELO.
 * - Lógica de estado interna: Esta classe mantém um 'timer'
 * (tempoInicioAlertaTemp) para gerenciar a regra complexa de
 * transição baseada em tempo (temp > 400°C por 30s).
 * - Transições bidirecionais: Implementa a lógica para
 * escalar (-> ALERTA_VERMELHO) ou reverter (-> OPERACAO_NORMAL).
 */
public class EstadoAlertaAmarelo implements EstadoUsina {

    // Variável para controlar o tempo da regra de transição
    private long tempoInicioAlertaTemp; // em milissegundos

    /**
     * Construtor. Inicializa o timer do alerta.
     */
    public EstadoAlertaAmarelo() {
        this.tempoInicioAlertaTemp = 0; // 0 = timer não iniciado
    }

    /**
     * Verifica as condições dos sensores.
     * REGRA 1: ALERTA_AMARELO -> ALERTA_VERMELHO (se temp > 400°C por > 30s)
     * REGRA 2: ALERTA_AMARELO -> OPERACAO_NORMAL (se temp <= 300°C)
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void verificarCondicoes(UsinaNuclear usina) {
        
        if (usina.getTemperatura() > 400) {
            if (tempoInicioAlertaTemp == 0) {
                // Inicia o timer
                System.out.println("AVISO: Temp > 400°C. Iniciando contagem de 30s...");
                tempoInicioAlertaTemp = System.currentTimeMillis();
            } else {
                long duracao = System.currentTimeMillis() - tempoInicioAlertaTemp;
                if (duracao > 30000) { // 30 segundos
                    System.out.println("PERIGO: Temp > 400°C por mais de 30s!");
                    usina.setEstado(new EstadoAlertaVermelho());
                } else {
                     System.out.println("AVISO: Temp > 400°C. (Contagem: " + (duracao/1000) + "s)");
                }
            }
        } else if (usina.getTemperatura() <= 300) {
            // Condição voltou ao normal
            System.out.println("INFO: Temperatura normalizada. Voltando para OPERACAO_NORMAL.");
            usina.setEstado(new EstadoOperacaoNormal());
        } else {
            // Se a temp está entre 300 e 400, ou caiu abaixo de 400,
            // resetamos o timer.
            if (tempoInicioAlertaTemp != 0) {
                 System.out.println("INFO: Temp < 400°C. Timer de 30s resetado.");
            }
            tempoInicioAlertaTemp = 0;
        }
    }

    @Override
    public void ligar(UsinaNuclear usina) {
        System.out.println("Ação 'ligar' inválida em ALERTA_AMARELO.");
    }

    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Iniciando desligamento de emergência...");
        usina.setEstado(new EstadoDesligada());
    }

    @Override
    public void entrarManutencao(UsinaNuclear usina) {
        System.out.println("Colocando usina em manutenção...");
        usina.setEstado(new EstadoManutencao());
    }

    @Override
    public void sairManutencao(UsinaNuclear usina) {
        System.out.println("Não está em manutenção.");
    }
    
    @Override
    public String getNomeEstado() {
        return "ALERTA_AMARELO";
    }
}