/**
 * Enum para representar as moedas e seus códigos no sistema legado.
 * 
 * Escolha do design:
 * - Enum garante tipo seguro para as moedas
 * - Encapsula a lógica de conversão entre códigos e strings
 * - Facilita manutenção (Single Responsibility Principle)
 */
public enum Moeda {
    USD(1),
    EUR(2),
    BRL(3);

    private final int codigoLegado;

    Moeda(int codigoLegado) {
        this.codigoLegado = codigoLegado;
    }

    public int getCodigoLegado() {
        return codigoLegado;
    }

    /**
     * Converte código do legado para enum Moeda
     */
    public static Moeda porCodigo(int codigo) throws TransacaoException {
        for (Moeda moeda : values()) {
            if (moeda.codigoLegado == codigo) {
                return moeda;
            }
        }
        throw new TransacaoException("Código de moeda inválido: " + codigo);
    }

    /**
     * Converte string da moeda para enum Moeda
     */
    public static Moeda porString(String moeda) throws TransacaoException {
        try {
            return valueOf(moeda.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TransacaoException("Moeda inválida: " + moeda);
        }
    }
}