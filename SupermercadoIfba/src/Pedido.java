import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pedido {

    private ArrayList<ItemPedido> itensPedido;
    private double valorTotalPedido;

    public Pedido() {
        itensPedido = new ArrayList<>();
        valorTotalPedido = 0d;
    }

    public ItemPedido addItem(Produto produto, int quantidade){
        ItemPedido item = new ItemPedido(produto, quantidade);
        itensPedido.add(item);
        valorTotalPedido += item.getValorTotal();

        return item;
    }

    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public double getValorTotalPedido() {
        return valorTotalPedido;
    }

    @Override
    public String toString() {

        return "\nPedido: \n{" +
                "\n   itensPedido: " + itensPedido +
                "\n   valorTotalPedido: " + String.format("%.2f", valorTotalPedido).replace(",", ".") +
                "\n}\n";
    }
}
