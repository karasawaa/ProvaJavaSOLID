/**
 * Interface que define o contrato para diferentes estratégias de análise de risco.
 * 
 * Escolha do padrão Strategy:
 * - Permite encapsular diferentes algoritmos em classes separadas!!
 * - Facilita a troca de algoritmos em tempo de execução
 * - Segue o princípio Open/Closed do SOLID: podemos adicionar novos algoritmos sem modificar o código existente
 * - Segue o princípio de Inversão de Dependência: dependemos de abstrações, não implementações
 */
public interface EstrategiaAnaliseRisco {
    /**
     * Calcula o risco baseado nos parâmetros financeiros fornecidos
     * @param contexto Objeto contendo todos os parâmetros necessários para o cálculo
     * @return Resultado da análise de risco
     */
    String calcularRisco(ContextoFinanceiro contexto);
}