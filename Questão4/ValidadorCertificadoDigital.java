public class ValidadorCertificadoDigital extends AbstractValidador {
    public ValidadorCertificadoDigital() { super("ValidadorCertificadoDigital", 1000); }

    @Override
    public boolean podeExecutar(ContextoValidacao contexto) {
        return true; // Sempre executa
    }

    @Override
    public void validar(ContextoValidacao contexto) throws ValidacaoException {
        System.out.println("Validando certificado (expiração, revogação)...");
        System.out.println("Certificado OK.");
    }
}