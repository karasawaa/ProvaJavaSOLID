/**
 * Exceção personalizada para erros de transação.
 * 
 * Escolha do design:
 * - Exceção específica para o domínio (melhor que usar Exception genérica)
 * - Facilita o tratamento específico de erros de transação
 */
public class TransacaoException extends Exception {
    public TransacaoException(String message) {
        super(message);
    }

    public TransacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}