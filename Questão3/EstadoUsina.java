/**
 * Contrato de abstração para todos os estados possíveis da Usina Nuclear.
 *
 * Escolha do design:
 * - Padrão State (Abstração do Estado): Define a interface comum que o 
 * Contexto (UsinaNuclear) irá utilizar.
 * - Princípio D (Dependency Inversion): A classe de alto nível 'UsinaNuclear' 
 * depende desta abstração, e não de implementações concretas de estado.
 * - Princípio O (Open/Closed): Novos estados (ex: 'Evacuação') podem ser 
 * adicionados implementando esta interface sem modificar o código existente.
 */
public interface EstadoUsina {

    /**
     * Avalia as condições atuais (sensores) para determinar se uma 
     * transição de estado é necessária. Este é o "motor" da máquina 
     * de estados.
     *
     * @param usina O objeto de contexto (a própria UsinaNuclear)
     */
    void verificarCondicoes(UsinaNuclear usina);

    /**
     * Tenta executar a ação de ligar a usina. 
     * A implementação concreta definirá se esta ação é válida no estado atual.
     *
     * @param usina O objeto de contexto (a própria UsinaNuclear)
     */
    void ligar(UsinaNuclear usina);

    /**
     * Tenta executar a ação de desligar a usina.
     *
     * @param usina O objeto de contexto (a própria UsinaNuclear)
     */
    void desligar(UsinaNuclear usina);

    /**
     * Tenta colocar a usina em modo de manutenção.
     *
     * @param usina O objeto de contexto (a própria UsinaNuclear)
     */
    void entrarManutencao(UsinaNuclear usina);

    /**
     * Tenta retirar a usina do modo de manutenção.
     *
     * @param usina O objeto de contexto (a própria UsinaNuclear)
     */
    void sairManutencao(UsinaNuclear usina);

    /**
     * Retorna o nome do estado atual para fins de logging e depuração.
     *
     * @return String com o nome legível do estado.
     */
    String getNomeEstado();
}