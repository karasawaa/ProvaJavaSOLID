/**
 * Implementação do estado 'Manutenção'.
 *
 * Escolha do design:
 * - Padrão State (Estado Concreto): Implementa um estado que
 * "sobrescreve" a lógica normal.
 * - Comportamento de "Override": O método 'verificarCondicoes'
 * ignora intencionalmente os sensores de operação (temp, etc.)
 * conforme a regra de negócio.
 * - Lógica de Retorno: O método 'sairManutencao' usa o 'estadoAnterior'
 * armazenado no Contexto (UsinaNuclear) para retornar ao
 * estado correto em que a usina estava antes da manutenção.
 */
public class EstadoManutencao implements EstadoUsina {

    /**
     * Em manutenção, ignoramos os sensores de operação.
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void verificarCondicoes(UsinaNuclear usina) {
        System.out.println("Em manutenção. Sensores de operação ignorados.");
    }

    @Override
    public void ligar(UsinaNuclear usina) {
        System.out.println("Ações de operação bloqueadas durante manutenção.");
    }

    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Ações de operação bloqueadas durante manutenção.");
    }

    @Override
    public void entrarManutencao(UsinaNuclear usina) {
        System.out.println("Usina já está em manutenção.");
    }

    /**
     * Sai do modo de manutenção e restaura o estado anterior.
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void sairManutencao(UsinaNuclear usina) {
        System.out.println("Saindo do modo de manutenção...");
        // Retorna ao estado salvo ANTES de entrar em manutenção
        usina.setEstado(usina.getEstadoAnterior());
    }

    @Override
    public String getNomeEstado() {
        return "MANUTENCAO";
    }
}