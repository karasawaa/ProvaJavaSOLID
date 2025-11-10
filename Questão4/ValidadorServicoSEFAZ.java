public class ValidadorServicoSEFAZ extends AbstractValidador {
    public ValidadorServicoSEFAZ() { super("ValidadorServicoSEFAZ", 2000); }

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
        System.out.println("Consultando serviço online da SEFAZ...");
        // Simula uma falha da SEFAZ
        if (contexto.getNfe().getId().equals("NFe-002")) {
            throw new ValidacaoException("Erro de comunicação com SEFAZ (timeout simulado).");
        }
        System.out.println("SEFAZ OK.");
    }
}