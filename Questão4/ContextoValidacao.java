import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Objeto que flui através da cadeia (Chain of Responsibility), 
 * carregando o estado global da validação.
 */
public class ContextoValidacao {
    private final DocumentoNFe nfe;
    private final List<String> falhas = new ArrayList<>();
    // Mapa para registrar o sucesso/falha de cada etapa
    private final Map<String, Boolean> statusValidacoes = new HashMap<>();

    public ContextoValidacao(DocumentoNFe nfe) {
        this.nfe = nfe;
    }

    public DocumentoNFe getNfe() { return nfe; }

    public void adicionarFalha(String validadorNome, String mensagem) {
        System.err.println("FALHA [" + validadorNome + "]: " + mensagem);
        falhas.add(validadorNome + ": " + mensagem);
    }

    public void registrarStatus(String nomeValidador, boolean sucesso) {
        statusValidacoes.put(nomeValidador, sucesso);
    }

    public boolean getStatus(String nomeValidador) {
        return statusValidacoes.getOrDefault(nomeValidador, false);
    }

    public int getContadorFalhas() {
        return falhas.size();
    }

    public List<String> getFalhas() {
        return falhas;
    }
}