/**
 * Implementação do algoritmo Value at Risk (VaR).
 * 
 * Escolha da implementação:
 * - Segue o princípio de Responsabilidade Única (SRP): classe tem apenas uma razão para mudar
 * - Implementa a interface EstrategiaAnaliseRisco, seguindo o princípio de Segregação de Interface (ISP)
 * - Permite fácil substituição por outras implementações (Liskov Substitution Principle)
 */
public class ValueAtRiskStrategy implements EstrategiaAnaliseRisco {
    @Override
    public String calcularRisco(ContextoFinanceiro contexto) {
        // Simulação do cálculo VaR
        double varEstimado = contexto.getValorInvestimento() * 
                            contexto.getVolatilidade() * 
                            Math.sqrt(contexto.getPeriodoEmDias() / 252.0);
        
        return String.format("Value at Risk (VaR) calculado: R$ %.2f " +
                           "(%.2f%% do investimento)", 
                           varEstimado, 
                           (varEstimado/contexto.getValorInvestimento()) * 100);
    }
}