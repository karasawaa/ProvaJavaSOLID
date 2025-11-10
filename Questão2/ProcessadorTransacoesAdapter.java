import java.util.HashMap;
/**
 * Adapter que converte entre a interface moderna e o sistema legado.
 * 
 * Escolha do Design Pattern Adapter:
 * - Permite que interfaces incompatíveis trabalhem juntas
 * - Encapsula a complexidade da conversão
 * - Segue o Open/Closed Principle: podemos adicionar novos adapters sem modificar código existente
 * - Segue o Single Responsibility Principle: classe focada apenas na conversão
 */
public class ProcessadorTransacoesAdapter implements ProcessadorTransacoes {
    
    private final SistemaBancarioLegado legado;
    
    public ProcessadorTransacoesAdapter(SistemaBancarioLegado legado) {
        this.legado = legado;
    }
    
    @Override
    public boolean autorizar(String cartao, double valor, String moeda) throws TransacaoException {
        // Converte os parâmetros para o formato do legado
        HashMap<String, Object> parametrosLegado = new HashMap<>();
        parametrosLegado.put("numeroCartao", cartao);
        parametrosLegado.put("valorTransacao", valor);
        parametrosLegado.put("codigoMoeda", Moeda.porString(moeda).getCodigoLegado());
        
        // Adiciona timestamp (campo obrigatório no legado)
        parametrosLegado.put("timestamp", System.currentTimeMillis());
        
        try {
            // Chama o sistema legado
            HashMap<String, Object> resposta = legado.processarTransacao(parametrosLegado);
            
            // Converte a resposta do legado
            return converterRespostaLegado(resposta);
        } catch (Exception e) {
            throw new TransacaoException("Erro ao processar transação no legado", e);
        }
    }
    
    /**
     * Converte a resposta do sistema legado para o formato moderno
     */
    private boolean converterRespostaLegado(HashMap<String, Object> resposta) throws TransacaoException {
        if (resposta == null) {
            throw new TransacaoException("Resposta nula do sistema legado");
        }
        
        Object statusObj = resposta.get("statusTransacao");
        if (statusObj == null) {
            throw new TransacaoException("Status da transação não encontrado na resposta");
        }
        
        // No legado, status 1 significa aprovado
        if (statusObj instanceof Integer) {
            return ((Integer) statusObj) == 1;
        }
        
        throw new TransacaoException("Formato de status inválido na resposta");
    }
}