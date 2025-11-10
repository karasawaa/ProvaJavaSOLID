/**
 * Classe principal que demonstra o uso do sistema.
 * 
 * Este exemplo mostra:
 * - Uso do padrão Adapter
 * - Conversão bidirecional entre interfaces
 * - Tratamento de campos obrigatórios
 * - Validações e tratamento de erros
 */
public class DemoTransacoes {
    public static void main(String[] args) {
        try {
            // Cria instância do sistema legado (mock)
            SistemaBancarioLegado legado = new SistemaBancarioLegadoMock();
            
            // Cria o adapter
            ProcessadorTransacoes processador = new ProcessadorTransacoesAdapter(legado);
            
            // Testa uma transação válida
            System.out.println("=== Testando transação válida ===");
            boolean resultado1 = processador.autorizar("1234-5678-9012-3456", 500.0, "USD");
            System.out.println("Resultado: " + (resultado1 ? "Aprovada" : "Negada"));
            
            // Testa uma transação que deve ser negada (valor alto)
            System.out.println("\n=== Testando transação com valor alto ===");
            boolean resultado2 = processador.autorizar("1234-5678-9012-3456", 1500.0, "EUR");
            System.out.println("Resultado: " + (resultado2 ? "Aprovada" : "Negada"));
            
            // Testa uma transação com moeda inválida
            System.out.println("\n=== Testando moeda inválida ===");
            processador.autorizar("1234-5678-9012-3456", 100.0, "JPY");
            
        } catch (TransacaoException e) {
            System.out.println("Erro na transação: " + e.getMessage());
        }
    }
}