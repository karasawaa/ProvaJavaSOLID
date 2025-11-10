/**
 * Classe principal para simular a operação da Usina Nuclear.
 *
 * Este exemplo demonstra:
 * - A instanciação da 'UsinaNuclear' (Contexto).
 * - A simulação de "ticks" do sistema, alterando os valores
 * dos sensores para disparar transições.
 * - A transição automática entre estados com base nas regras
 * (ex: OPERACAO_NORMAL -> ALERTA_AMARELO).
 * - A simulação de uma regra de transição complexa baseada em tempo
 * (o "tick30s" para ALERTA_VERMELHO).
 * - A validação do estado de Manutenção (ignora sensores).
 * - A validação de estados terminais (EMERGENCIA).
 */
public class Main {

    /**
     * Método principal da simulação.
     *
     * @param args Argumentos de linha de comando (não utilizados)
     * @throws InterruptedException Lançada por Thread.sleep()
     */
    public static void main(String[] args) throws InterruptedException {
        
        UsinaNuclear usina = new UsinaNuclear();

        // 1. Ligar
        usina.ligar(); // DESLIGADA -> OPERACAO_NORMAL
        
        // 2. Operação Normal
        tick(usina, 250, false); // Temp normal
        tick(usina, 290, false); // Temp normal

        // 3. Transição para Alerta Amarelo
        tick(usina, 301, false); // OPERACAO_NORMAL -> ALERTA_AMARELO
        tick(usina, 350, false); // Permanece em ALERTA_AMARELO

        // 4. Voltar ao Normal
        tick(usina, 299, false); // ALERTA_AMARELO -> OPERACAO_NORMAL
        
        // 5. Entrar em Manutenção
        usina.entrarManutencao(); // OPERACAO_NORMAL -> MANUTENCAO
        tick(usina, 500, true); // Temp alta, mas é ignorada
        tick(usina, 600, true); // Temp alta, mas é ignorada
        usina.sairManutencao();  // MANUTENCAO -> OPERACAO_NORMAL (estado anterior)

        // 6. Simular transição para Emergência
        System.out.println("\n--- SIMULANDO ESCALAÇÃO PARA EMERGÊNCIA ---");
        tick(usina, 301, false); // OPERACAO_NORMAL -> ALERTA_AMARELO
        
        // Simula a regra dos 30 segundos
        tick30s(usina); // ALERTA_AMARELO -> ALERTA_VERMELHO
        
        // Simula a falha final
        tick(usina, 450, false); // Permanece em ALERTA_VERMELHO
        tick(usina, 460, true);  // ALERTA_VERMELHO -> EMERGENCIA
        
        // 7. Estado de Emergência
        tick(usina, 500, true);  // Permanece em EMERGENCIA
        tick(usina, 100, false); // Permanece em EMERGENCIA (só sai com intervenção)
        
        // 8. Desligamento manual pós-emergência
        usina.desligar(); // EMERGENCIA -> DESLIGADA
        tick(usina, 25, false); // Permanece DESLIGADA
    }

    /**
     * Helper que simula um "tick" de tempo do sistema (1 segundo).
     *
     * @param usina A instância da usina
     * @param temp A temperatura a ser simulada
     * @param falhaResfriamento O estado do sistema de resfriamento
     * @throws InterruptedException para Thread.sleep
     */
    private static void tick(UsinaNuclear usina, double temp, boolean falhaResfriamento) throws InterruptedException {
        System.out.println("\n...[TICK]... Temp: " + temp + "°C, Falha Resfriamento: " + falhaResfriamento);
        usina.atualizarSensores(temp, 150.0, 10.0, falhaResfriamento);
        Thread.sleep(1000); // Pausa de 1 segundo para simular o tempo
    }
    
    /**
     * Helper que simula a regra de tempo (30s) para o
     * Alerta Vermelho, enviando ticks de 1 segundo.
     *
     * @param usina A instância da usina
     * @throws InterruptedException para Thread.sleep
     */
    private static void tick30s(UsinaNuclear usina) throws InterruptedException {
        System.out.println("\n--- SIMULANDO REGRA DOS 30 SEGUNDOS (Temp > 400°C) ---");
        for (int i = 0; i <= 31; i++) {
             System.out.println("\n...[TICK " + i + "s]... Temp: 410°C, Falha Resfriamento: false");
             usina.atualizarSensores(410.0, 150.0, 10.0, false);
             Thread.sleep(1000); // Simula 1 segundo
             
             // Para o loop se o estado mudar (o que deve acontecer aos 31s)
             if (usina.getEstadoAtual() instanceof EstadoAlertaVermelho) {
                 break;
             }
        }
    }
}