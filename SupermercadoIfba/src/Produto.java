/**
 * Representação de um produto.
 *
 * Esta classe armazena informações sobre um produto, incluindo código, descrição e preço.
 *
 * @autor franck.allyson, pedro.lopes, ana.carolina, clarisse.santana
 */
public class Produto {
    private int codigo;
    private String descricao;
    private double preco;

    /**
     * Construtor da classe Produto.
     *
     * @param codigo O código do produto.
     * @param descricao A descrição do produto.
     * @param preco O preço do produto.
     */
    public Produto(int codigo, String descricao, double preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Retorna uma representação em string do produto.
     *
     * @return Uma string representando o produto.
     */
    @Override
    public String toString() {
        return "Codigo:" + codigo +
                ", Descricao:'" + descricao + '\'' +
                ", Preco Unitario:" + preco +
                "}";
    }

    /**
     * Obtém o código do produto.
     *
     * @return O código do produto.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código do produto.
     *
     * @param codigo O código do produto.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao A descrição do produto.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return O preço do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco O preço do produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
