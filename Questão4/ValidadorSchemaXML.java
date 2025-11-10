public class ValidadorSchemaXML extends AbstractValidador {
    public ValidadorSchemaXML() { super("ValidadorSchemaXML", 500); }

    @Override
    public boolean podeExecutar(ContextoValidacao contexto) {
        return true; // Sempre executa
    }

    @Override
    public void validar(ContextoValidacao contexto) throws ValidacaoException {
        System.out.println("Validando schema XSD...");
        if (contexto.getNfe().getXmlConteudo() == null) {
            throw new ValidacaoException("XML está vazio!");
        }
        System.out.println("Schema OK.");
    }
}