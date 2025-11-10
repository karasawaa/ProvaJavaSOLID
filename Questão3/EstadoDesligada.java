/**
 * Implementação do estado 'Desligada'.
 *
 * Escolha do design:
 * - Padrão State (Estado Concreto): Encapsula o comportamento e as
 * regras de transição específicas para quando a usina está DESLIGADA.
 * - Princípio S (Single Responsibility): A única responsabilidade desta
 * classe é gerenciar o estado DESLIGADA.
 * - Implementa as transições válidas:
 * - DESLIGADA -> OPERACAO_NORMAL (ao ligar)
 * - DESLIGADA -> MANUTENCAO (ao entrar em manutenção)
 */
public class EstadoDesligada implements EstadoUsina {

    @Override
    public void verificarCondicoes(UsinaNuclear usina) {
        // Nenhuma condição automática é verificada quando desligada
    }

    @Override
    public void ligar(UsinaNuclear usina) {
        System.out.println("Iniciando procedimentos... Ligando usina.");
        usina.setEstado(new EstadoOperacaoNormal());
    }

    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Usina já está desligada.");
    }

    @Override
    public void entrarManutencao(UsinaNuclear usina) {
        System.out.println("Entrando em manutenção a partir do estado DESLIGADA.");
        usina.setEstado(new EstadoManutencao());
    }

    @Override
    public void sairManutencao(UsinaNuclear usina) {
        System.out.println("Não está em manutenção.");
    }

    @Override
    public String getNomeEstado() {
        return "DESLIGADA";
    }
}