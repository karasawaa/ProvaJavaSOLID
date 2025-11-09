/**
 * Classe que encapsula todos os parâmetros necessários para os cálculos de risco.
 * 
 * Escolha do padrão:
 * - Utiliza o princípio de Responsabilidade Única (SRP) do SOLID
 * - Encapsula todos os dados relacionados ao contexto financeiro
 * - Facilita a manutenção e extensão dos parâmetros necessários
 */
public class ContextoFinanceiro {
    private double valorInvestimento;
    private int periodoEmDias;
    private double taxaJuros;
    private double volatilidade;

    public ContextoFinanceiro(double valorInvestimento, int periodoEmDias, 
                            double taxaJuros, double volatilidade) {
        this.valorInvestimento = valorInvestimento;
        this.periodoEmDias = periodoEmDias;
        this.taxaJuros = taxaJuros;
        this.volatilidade = volatilidade;
    }

    // Getters
    public double getValorInvestimento() {
        return valorInvestimento;
    }

    public int getPeriodoEmDias() {
        return periodoEmDias;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public double getVolatilidade() {
        return volatilidade;
    }
}