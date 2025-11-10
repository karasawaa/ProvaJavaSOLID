public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("==============================================");
            System.out.println("Simulação 1: NFe-001 (Sucesso Total)");
            System.out.println("==============================================");
            
            DocumentoNFe nfe1 = new DocumentoNFe("NFe-001", "<xml>...</xml>");
            ContextoValidacao contexto1 = new ContextoValidacao(nfe1);
            
            // Monta a cadeia e obtém o primeiro elo
            Validador cadeia = montarCadeia();
            
            // Executa a cadeia chamando APENAS o primeiro elo
            boolean sucesso1 = cadeia.executar(contexto1);
            imprimirResultado(contexto1, sucesso1);


            System.out.println("\n==============================================");
            System.out.println("Simulação 2: NFe-002 (Falha na SEFAZ e Rollback)");
            System.out.println("==============================================");
            
            DocumentoNFe nfe2 = new DocumentoNFe("NFe-002", "<xml>...</xml>");
            ContextoValidacao contexto2 = new ContextoValidacao(nfe2);
            
            Validador cadeia2 = montarCadeia();
            boolean sucesso2 = cadeia2.executar(contexto2);
            imprimirResultado(contexto2, sucesso2);
            
        } finally {
            // Desliga o pool de threads de timeout no final do programa
            AbstractValidador.shutdownExecutor();
        }
    }

    /**
     * Padrão: Builder (ou Factory)
     * Método de fábrica que constrói a Cadeia de Responsabilidade.
     * Ele liga os elos (v1 -> v2 -> v3) e retorna o PRIMEIRO elo.
     */
    private static Validador montarCadeia() {
        // 1. Cria todos os elos
        Validador v1 = new ValidadorSchemaXML();
        Validador v2 = new ValidadorCertificadoDigital();
        Validador v3 = new ValidadorRegrasFiscais();
        Validador v4 = new ValidadorBancoDados();
        Validador v5 = new ValidadorServicoSEFAZ();

        // 2. Constrói a cadeia ligando-os
        v1.setNext(v2);
        v2.setNext(v3);
        v3.setNext(v4);
        v4.setNext(v5);
        // v5 é o último, v5.setNext(null) é o padrão

        // 3. Retorna o PRIMEIRO elo
        return v1;
    }

    private static void imprimirResultado(ContextoValidacao contexto, boolean sucesso) {
        System.out.println("\n--- RESULTADO FINAL ---");
        System.out.println("Sucesso da Cadeia: " + sucesso);
        if (!sucesso) {
            System.out.println("Falhas registradas:");
            for (String falha : contexto.getFalhas()) {
                System.out.println("  - " + falha);
            }
        }
    }
}