/**
 * Interface moderna para processamento de transações.
 * 
 * Escolha do design:
 * - Interface limpa e coesa (Princípio da Responsabilidade Única)
 * - Métodos com parâmetros bem definidos e tipados
 * - Nomes claros e auto-explicativos
 */
public interface ProcessadorTransacoes {
    /**
     * Autoriza uma transação com cartão.
     * 
     * @param cartao Número do cartão
     * @param valor Valor da transação
     * @param moeda Código da moeda (USD, EUR, BRL)
     * @return true se autorizado, false caso contrário
     * @throws TransacaoException em caso de erro no processamento
     */
    boolean autorizar(String cartao, double valor, String moeda) throws TransacaoException;
}