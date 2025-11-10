public class ValidadorRegrasFiscais extends AbstractValidador {
    public ValidadorRegrasFiscais() { super("ValidadorRegrasFiscais", 800); }

    /**
     * REGRA CONDICIONAL: Só executa se Schema e Certificado passarem.
     */
    @Override
    public boolean podeExecutar(ContextoValidacao contexto) {
        boolean schemaOK = contexto.getStatus("ValidadorSchemaXML");
        boolean certOK = contexto.getStatus("ValidadorCertificadoDigital");
        return schemaOK && certOK;
    }

    @Override
    public void validar(ContextoValidacao contexto) throws ValidacaoException {
        System.out.println("Validando regras fiscais (cálculo de impostos)...");
        System.out.println("Regras Fiscais OK.");
    }
}