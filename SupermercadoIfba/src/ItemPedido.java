import java.text.DecimalFormat;

/**
 * Representação de um item de pedido.
 *
 * Esta classe armazena informações sobre um item de pedido, incluindo o produto,
 * a quantidade e o valor total.
 *
 * @autor franck.allyson, pedro.lopes, ana.carolina, clarisse.santana
 */
public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double valorTotal;

    /**
     * Construtor da classe ItemPedido.
     *
     * @param produto O produto do item de pedido.
     * @param quantidade A quantidade do produto.
     */
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = produto.getPreco() * quantidade;
    }

    /**
     * Obtém o valor total do item de pedido.
     *
     * @return O valor total do item de pedido.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Obtém o produto do item de pedido.
     *
     * @return O produto do item de pedido.
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Obtém a quantidade do produto no item de pedido.
     *
     * @return A quantidade do produto no item de pedido.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna uma representação em string do item de pedido,
     * incluindo o produto, a quantidade e o valor total formatado com duas casas decimais.
     *
     * @return Uma string representando o item de pedido.
     */
    @Override
    public String toString() {


        return "\n   {Produto: " + produto +
                ", Quantidade: " + quantidade +
                ", Valor Total: " + String.format("%.2f", valorTotal).replace(",", ".") +
                "}";
    }
}
