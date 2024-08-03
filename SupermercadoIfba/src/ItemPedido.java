import java.text.DecimalFormat;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double valorTotal;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = produto.preco * quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {


        return "\nItemPedido{" +
                "produto: " + produto +
                ", quantidade: " + quantidade +
                ", valorTotal: " + String.format("%.2f", valorTotal).replace(",", ".") +
                "}";
    }
}
