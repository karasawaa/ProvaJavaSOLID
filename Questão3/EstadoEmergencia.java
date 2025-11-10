/**
 * Implementação do estado 'Emergência'.
 *
 * Escolha do design:
 * - Padrão State (Estado Concreto): Representa um estado terminal.
 * - Prevenção de transições circulares: Uma vez em EMERGENCIA,
 * o método 'verificarCondicoes' não faz nada. A saída deste
 * estado só pode ocorrer por uma intervenção manual ('desligar').
 * - Bloqueia ações de operação normais (ligar, manutenção).
 */
public class EstadoEmergencia implements EstadoUsina {

    /**
     * Estado terminal. Nenhuma verificação automática muda o estado.
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void verificarCondicoes(UsinaNuclear usina) {
        System.out.println("EM EMERGENCIA! Requer intervenção manual!");
    }

    @Override
    public void ligar(UsinaNuclear usina) {
        System.out.println("Ação 'ligar' inválIDA em EMERGENCIA.");
    }

    /**
     * Ação de desligamento manual pós-emergência.
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Requer procedimento de desligamento manual " +
                           "pós-emergência...");
        usina.setEstado(new EstadoDesligada());
    }

    @Override
    public void entrarManutencao(UsinaNuclear usina) {
        System.out.println("Ação 'entrarManutencao' inválida em EMERGENCIA.");
    }

    @Override
    public void sairManutencao(UsinaNuclear usina) {
        System.out.println("Não está em manutenção.");
    }

    @Override
    public String getNomeEstado() {
        return "EMERGENCIA";
    }
}