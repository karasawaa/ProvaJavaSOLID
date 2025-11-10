import java.util.HashMap;

/**
 * Interface que representa o sistema bancário legado.
 * 
 * Escolha do design:
 * - Mantém a assinatura original do legado
 * - Usa HashMap genérico conforme especificação
 * - Documenta os campos esperados no Map
 */
public interface SistemaBancarioLegado {
    /**
     * Processa uma transação no sistema legado.
     * 
     * Campos esperados no Map:
     * - "numeroCartao": String
     * - "valorTransacao": Double
     * - "codigoMoeda": Integer (USD=1, EUR=2, BRL=3)
     * - "timestamp": Long (obrigatório no legado)
     * 
     * @param parametros Map com os parâmetros da transação
     * @return Map com a resposta do processamento
     */
    HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros);
}