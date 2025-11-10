/**
 * Exceção customizada para falhas de validação.
 */
public class ValidacaoException extends Exception {
    public ValidacaoException(String message) {
        super(message);
    }
}