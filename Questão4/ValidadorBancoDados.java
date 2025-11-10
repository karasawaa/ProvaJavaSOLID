public class ValidadorBancoDados extends AbstractValidador {
    public ValidadorBancoDados() { super("ValidadorBancoDados", 700); }

    @Override
    public boolean podeExecutar(ContextoValidacao contexto) {
        return true; // Sempre tenta
    }

    @Override
    public void validar(ContextoValidacao contexto) throws ValidacaoException {
        System.out.println("Verificando duplicidade no banco de dados...");
        //...
        // REGRA DE ROLLBACK: Simula a "modificação"
        System.out.println("Inserindo número " + contexto.getNfe().getId() + " no BD (estado 'PENDENTE')");
        contexto.getNfe().setModificadoNoBanco(true); // "Suja" o objeto
    }

    /**
     * REGRA DE ROLLBACK: Implementação que desfaz a ação de 'validar'.
     */
    @Override
    public void rollback(ContextoValidacao contexto) {
        if (contexto.getNfe().isModificadoNoBanco()) {
            System.out.println("ROLLBACK [BancoDados]: Removendo inserção pendente de " + contexto.getNfe().getId());
            contexto.getNfe().setModificadoNoBanco(false);
        }
    }
}