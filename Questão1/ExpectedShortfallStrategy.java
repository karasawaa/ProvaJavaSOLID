/**
 * Implementação do algoritmo Expected Shortfall (ES).
 * 
 * Escolha da implementação:
 * - Mantém coesão alta ao focar apenas no cálculo do ES
 * - Segue o mesmo contrato da interface, garantindo consistência
 * - Permite extensão sem modificação (Open/Closed Principle)
 */
public class ExpectedShortfallStrategy implements EstrategiaAnaliseRisco {
    @Override
    public String calcularRisco(ContextoFinanceiro contexto) {
        // Simulação do cálculo ES (geralmente mais conservador que VaR)
        double esEstimado = contexto.getValorInvestimento() * 
                           contexto.getVolatilidade() * 
                           Math.sqrt(contexto.getPeriodoEmDias() / 252.0) * 1.3;
        
        return String.format("Expected Shortfall (ES) calculado: R$ %.2f " +
                           "(considera cenários mais extremos que o VaR)", 
                           esEstimado);
    }
}