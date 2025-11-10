/**
 * Padrão: Chain of Responsibility (Elo da Corrente)
 *
 * Esta é a interface 'Handler' (Manipulador) do padrão CoR.
 * - Define o contrato para todos os validadores (elos).
 * - 'setNext' é o método que permite "ligar" um elo ao próximo, formando a cadeia.
 * - 'executar' é o método 'handleRequest' que processa a solicitação.
 */
public interface Validador {

    /**
     * Define o próximo validador na cadeia.
     * @param proximo O próximo elo da corrente.
     */
    void setNext(Validador proximo);

    /**
     * Executa a validação deste elo e, em seguida, chama o próximo.
     * Implementa a lógica de rollback.
     *
     * @param contexto O objeto de dados que flui pela cadeia.
     * @return true se este elo E todos os elos subsequentes tiverem sucesso.
     */
    boolean executar(ContextoValidacao contexto);

    /**
     * Desfaz qualquer modificação feita por este elo.
     * Chamado se 'executar' retornar 'false'.
     *
     * @param contexto O objeto de dados que flui pela cadeia.
     */
    void rollback(ContextoValidacao contexto);
}