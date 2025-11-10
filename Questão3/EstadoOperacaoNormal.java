/**
 * Implementação do estado 'Operação Normal'.
 *
 * Escolha do design:
 * - Padrão State (Estado Concreto): Encapsula o comportamento e as
 * regras de transição específicas para OPERACAO_NORMAL.
 * - Princípio S (Single Responsibility): Única responsabilidade é
 * gerenciar este estado.
 * - Implementa as regras de transição:
 * - OPERACAO_NORMAL -> ALERTA_AMARELO (se temp > 300°C)
 * - OPERACAO_NORMAL -> DESLIGADA (ao desligar)
 * - OPERACAO_NORMAL -> MANUTENCAO (ao entrar em manutenção)
 */
public class EstadoOperacaoNormal implements EstadoUsina {

    /**
     * Verifica as condições dos sensores.
     * REGRA: OPERACAO_NORMAL -> ALERTA_AMARELO se temp > 300°C.
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void verificarCondicoes(UsinaNuclear usina) {
        if (usina.getTemperatura() > 300) {
            System.out.println("ALERTA: Temperatura > 300°C!");
            usina.setEstado(new EstadoAlertaAmarelo());
        }
    }

    @Override
    public void ligar(UsinaNuclear usina) {
        System.out.println("Usina já está em operação normal.");
    }

    /**
     * Ação de desligar a usina.
     * Transição permitida: OPERACAO_NORMAL -> DESLIGADA
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Iniciando desligamento...");
        usina.setEstado(new EstadoDesligada());
    }

    @Override
    public void entrarManutencao(UsinaNuclear usina) {
        System.out.println("Colocando usina em manutenção...");
        usina.setEstado(new EstadoManutencao());
    }

    @Override
    public void sairManutencao(UsinaNuclear usina) {
        System.out.println("Não está em manutenção.");
    }

    @Override
    public String getNomeEstado() {
        return "OPERACAO_NORMAL";
    }
}