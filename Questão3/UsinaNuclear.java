/**
 * Classe de Contexto que gerencia o estado atual da usina e os dados
 * dos sensores.
 *
 * Escolha do design:
 * - Padrão State (Contexto): Mantém a referência ao estado atual ('estadoAtual')
 * e delega todo o comportamento para o objeto de estado.
 * - Princípio S (Single Responsibility): A responsabilidade da UsinaNuclear é
 * gerenciar os dados (sensores) e *qual* é o estado atual. A lógica de
 * *o que fazer* em cada estado é delegada para as classes de estado.
 * - Lógica de Manutenção: Armazena 'estadoAnterior' para implementar a regra de
 * negócio de "retornar" ao estado prévio após a manutenção.
 */
public class UsinaNuclear {

    private EstadoUsina estadoAtual;
    private EstadoUsina estadoAnterior; // Usado para restaurar após manutenção

    // Dados dos sensores
    private double temperatura;
    private double pressao;
    private double nivelRadiacao;
    private boolean sistemaResfriamentoFalhou;

    /**
     * Construtor da UsinaNuclear.
     * Define o estado inicial como 'DESLIGADA'.
     */
    public UsinaNuclear() {
        // A usina começa desligada por padrão
        this.estadoAtual = new EstadoDesligada();
        this.estadoAnterior = this.estadoAtual;
        System.out.println("Usina criada. Estado inicial: " + estadoAtual.getNomeEstado());
    }

    /**
     * Método principal do "loop" do sistema. Atualiza os sensores
     * e delega a verificação de condições para o estado atual.
     *
     * @param temp Temperatura atual em °C
     * @param press Pressão atual
     * @param radiacao Nível de radiação atual
     * @param falhaResfriamento true se o sistema de resfriamento falhou
     */
    public void atualizarSensores(double temp, double press, double radiacao, boolean falhaResfriamento) {
        this.temperatura = temp;
        this.pressao = press;
        this.nivelRadiacao = radiacao;
        this.sistemaResfriamentoFalhou = falhaResfriamento;

        // Delega a verificação de regras para o objeto de estado atual
        this.estadoAtual.verificarCondicoes(this);
    }
    
    // --- Delegação de Ações para o Estado Atual ---

    /**
     * Delega a ação 'ligar' para o estado atual.
     */
    public void ligar() {
        this.estadoAtual.ligar(this);
    }
    
    /**
     * Delega a ação 'desligar' para o estado atual.
     */
    public void desligar() {
        this.estadoAtual.desligar(this);
    }

    /**
     * Delega a ação 'entrarManutencao' para o estado atual.
     */
    public void entrarManutencao() {
        this.estadoAtual.entrarManutencao(this);
    }

    /**
     * Delega a ação 'sairManutencao' para o estado atual.
     */
    public void sairManutencao() {
        this.estadoAtual.sairManutencao(this);
    }

    // --- Getters e Setters para o Estado e Sensores ---
    
    /**
     * Define o novo estado da usina, registrando a transição.
     * Este é o método central para a Máquina de Estados.
     *
     * @param novoEstado O objeto de estado para o qual a usina deve transicionar.
     */
    public void setEstado(EstadoUsina novoEstado) {
        if (this.estadoAtual.getClass() != novoEstado.getClass()) {
            System.out.println("-------------------------------------------------");
            System.out.println("TRANSIÇÃO: " + this.estadoAtual.getNomeEstado() + 
                               " -> " + novoEstado.getNomeEstado());
            System.out.println("-------------------------------------------------");
            
            // Guarda o estado anterior APENAS se não estivermos 
            // já em manutenção
            if (!(this.estadoAtual instanceof EstadoManutencao)) {
                this.estadoAnterior = this.estadoAtual;
            }
            this.estadoAtual = novoEstado;
        }
    }
    
    /**
     * Retorna o estado salvo antes de entrar em manutenção.
     * @return O objeto EstadoUsina anterior.
     */
    public EstadoUsina getEstadoAnterior() {
        return this.estadoAnterior;
    }
    
    /**
     * @return A temperatura atual do sensor.
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * @return true se o sistema de resfriamento falhou, false caso contrário.
     */
    public boolean isSistemaResfriamentoFalhou() {
        return sistemaResfriamentoFalhou;
    }

    /**
     * @return O objeto de estado atual.
     */
    public EstadoUsina getEstadoAtual() {
        return estadoAtual;
    }
}