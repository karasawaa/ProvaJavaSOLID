import java.util.HashMap;

/**
 * Implementação mock do sistema legado para demonstração.
 * 
 * Escolha do design:
 * - Implementação simplificada para testes
 * - Demonstra o funcionamento do sistema legado
 * - Permite testar o adapter sem um sistema real
 */
public class SistemaBancarioLegadoMock implements SistemaBancarioLegado {
    
    @Override
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        // Validação dos campos obrigatórios do legado
        if (!parametros.containsKey("timestamp")) {
            throw new IllegalArgumentException("Campo obrigatório 'timestamp' não encontrado");
        }
        
        // Validação básica dos parâmetros
        String numeroCartao = (String) parametros.get("numeroCartao");
        Double valor = (Double) parametros.get("valorTransacao");
        Integer codigoMoeda = (Integer) parametros.get("codigoMoeda");
        
        if (numeroCartao == null || valor == null || codigoMoeda == null) {
            throw new IllegalArgumentException("Parâmetros obrigatórios faltando");
        }
        
        // Simula uma validação simples: aprova transações abaixo de 1000
        HashMap<String, Object> resposta = new HashMap<>();
        resposta.put("statusTransacao", valor < 1000 ? 1 : 0);
        resposta.put("mensagem", valor < 1000 ? "Aprovado" : "Valor excede limite");
        resposta.put("codigoAutorizacao", valor < 1000 ? "AUTH" + System.currentTimeMillis() : null);
        
        return resposta;
    }
}