/**
 * POJO (Plain Old Java Object) que representa o documento fiscal.
 * Em um sistema real, isso seria um objeto complexo desserializado do XML.
 */
public class DocumentoNFe {
    private final String id;
    private final String xmlConteudo;
    private boolean modificadoNoBanco = false; // Flag para simular rollback

    public DocumentoNFe(String id, String xmlConteudo) {
        this.id = id;
        this.xmlConteudo = xmlConteudo;
    }

    public String getId() { return id; }
    public String getXmlConteudo() { return xmlConteudo; }

    // Usado pelo Validador de BD para simular a "sujeira"
    public void setModificadoNoBanco(boolean modificado) {
        this.modificadoNoBanco = modificado;
    }
    public boolean isModificadoNoBanco() {
        return modificadoNoBanco;
    }
}