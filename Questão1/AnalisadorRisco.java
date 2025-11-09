/**
 * Classe que demonstra o uso do sistema de análise de risco. É como se fosse a "MAIN"
 * 
 * Padrões e Princípios utilizados:
 * - Strategy Pattern: Permite trocar algoritmos em tempo de execução
 * - Dependency Injection: AnalisadorRisco recebe a estratégia via construtor
 */
public class AnalisadorRisco {
    private EstrategiaAnaliseRisco estrategiaAtual;
    
    public AnalisadorRisco(EstrategiaAnaliseRisco estrategiaInicial) {
        this.estrategiaAtual = estrategiaInicial;
    }
    
    /**
     * Permite trocar a estratégia em tempo de execução
     */
    public void setEstrategia(EstrategiaAnaliseRisco novaEstrategia) {
        this.estrategiaAtual = novaEstrategia;
    }
    
    /**
     * Executa a análise de risco usando a estratégia atual
     */
    public String analisarRisco(ContextoFinanceiro contexto) {
        return estrategiaAtual.calcularRisco(contexto);
    }
    
    public static void main(String[] args) {
        // Criando o contexto financeiro
        ContextoFinanceiro contexto = new ContextoFinanceiro(
            1000000.0,  // R$ 1 milhão de investimento
            21,         // 21 dias
            0.1375,     // Taxa de juros de 13.75%
            0.20        // Volatilidade de 20%
        );
        
        // Criando o analisador com estratégia inicial VaR
        AnalisadorRisco analisador = new AnalisadorRisco(new ValueAtRiskStrategy());
        
        // Testando diferentes estratégias
        System.out.println("=== Análise de Risco Financeiro ===\n");
        
        System.out.println("1. Usando Value at Risk (VaR):");
        System.out.println(analisador.analisarRisco(contexto) + "\n");
        
        System.out.println("2. Mudando para Expected Shortfall:");
        analisador.setEstrategia(new ExpectedShortfallStrategy());
        System.out.println(analisador.analisarRisco(contexto) + "\n");
        
        System.out.println("3. Mudando para Stress Testing:");
        analisador.setEstrategia(new StressTestingStrategy());
        System.out.println(analisador.analisarRisco(contexto));
    }
}