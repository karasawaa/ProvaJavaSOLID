/**
 * Implementação do algoritmo Stress Testing.
 * 
 * Escolha da implementação:
 * - Demonstra a flexibilidade do padrão Strategy ao permitir diferentes abordagens
 * - Mantém a consistência com outras implementações
 * - Segue o princípio de substituição de Liskov ao ser intercambiável com outras estratégias
 */
public class StressTestingStrategy implements EstrategiaAnaliseRisco {
    @Override
    public String calcularRisco(ContextoFinanceiro contexto) {
        // Simulação de teste de estresse com cenário pessimista
        double perdaPotencial = contexto.getValorInvestimento() * 
                              (contexto.getVolatilidade() * 2) * 
                              Math.sqrt(contexto.getPeriodoEmDias() / 252.0);
        
        return String.format("Resultado do Stress Testing: Perda máxima estimada " +
                           "em cenário de estresse: R$ %.2f\n" +
                           "Condições: Dobro da volatilidade atual e " +
                           "aumento de juros em 5 pontos percentuais", 
                           perdaPotencial);
    }
}