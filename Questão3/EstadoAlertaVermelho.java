/**
 * Implementação do estado 'Alerta Vermelho'.
 *
 * Escolha do design:
 * - Padrão State (Estado Concreto): Gerencia o comportamento em
 * ALERTA_VERMELHO.
 * - Princípio S (Single Responsibility): Focado apenas neste estado crítico.
 * - Regra de transição unidirecional estrita:
 * - ALERTA_VERMELHO -> EMERGENCIA (se resfriamento falhar)
 * - Esta classe é o "portão" para o estado EMERGENCIA, conforme a regra.
 * - Transição bidirecional de retorno:
 * - ALERTA_VERMELHO -> ALERTA_AMARELO (se condição de temp for resolvida)
 */
public class EstadoAlertaVermelho implements EstadoUsina {

    /**
     * Verifica as condições dos sensores.
     * REGRA 1: ALERTA_VERMELHO -> EMERGENCIA (se sistema de resfriamento falhar)
     * REGRA 2: ALERTA_VERMELHO -> ALERTA_AMARELO (se temp <= 400°C)
     *
     * @param usina O objeto de contexto
     */
    @Override
    public void verificarCondicoes(UsinaNuclear usina) {
        if (usina.isSistemaResfriamentoFalhou()) {
            System.out.println("CATASTROFE: Sistema de resfriamento falhou!");
            usina.setEstado(new EstadoEmergencia());
        }
        
        // Se o resfriamento não falhou E a temperatura baixou
        else if (usina.getTemperatura() <= 400) {
             System.out.println("INFO: Condição de Temp > 400°C resolvida. " + 
                                "Retornando para ALERTA_AMARELO.");
             usina.setEstado(new EstadoAlertaAmarelo());
        }
    }

    @Override
    public void ligar(UsinaNuclear usina) {
        System.out.println("Ação 'ligar' inválida em ALERTA_VERMELHO.");
    }

    @Override
    public void desligar(UsinaNuclear usina) {
        System.out.println("Iniciando desligamento de emergência...");
        usina.setEstado(new EstadoDesligada());
    }

    @Override
    public void entrarManutencao(UsinaNuclear usina) {
        System.out.println("Ação 'entrarManutencao' inválida em ALERTA_VERMELHO. " + 
                           "Resolva a emergência primeiro.");
    }

    @Override
    public void sairManutencao(UsinaNuclear usina) {
        System.out.println("Não está em manutenção.");
    }

    @Override
    public String getNomeEstado() {
        return "ALERTA_VERMELHO";
    }
}
