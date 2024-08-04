import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Representação de um pedido.
 *
 * Esta classe armazena uma lista de itens de pedido e calcula o valor total do pedido.
 *
 * @autor franck.allyson, pedro.lopes, ana.carolina, clarisse.santana
 */
public class Pedido {

    private ArrayList<ItemPedido> itensPedido;
    private double valorTotalPedido;

    /**
     * Construtor padrão da classe Pedido.
     *
     * Inicializa a lista de itens do pedido e define o valor total do pedido como 0.
     */
    public Pedido() {
        itensPedido = new ArrayList<>();
        valorTotalPedido = 0d;
    }

    /**
     * Adiciona um item ao pedido.
     *
     * @param produto O produto a ser adicionado ao pedido.
     * @param quantidade A quantidade do produto a ser adicionada.
     * @return O item de pedido adicionado.
     */
    public ItemPedido addItem(Produto produto, int quantidade){
        ItemPedido item = new ItemPedido(produto, quantidade);
        itensPedido.add(item);
        valorTotalPedido += item.getValorTotal();

        return item;
    }

    /**
     * Obtém a lista de itens do pedido.
     *
     * @return A lista de itens do pedido.
     */
    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    /**
     * Obtém o valor total do pedido.
     *
     * @return O valor total do pedido.
     */
    public double getValorTotalPedido() {
        return valorTotalPedido;
    }

    /**
     * Retorna uma representação em string do pedido,
     * incluindo a lista de itens e o valor total do pedido formatado com duas casas decimais.
     *
     * @return Uma string representando o pedido.
     */
    @Override
    public String toString() {

        return "\nPedido: {" +
                "\nitensPedido: " + itensPedido +
                "\nvalorTotalPedido: " + String.format("%.2f", valorTotalPedido).replace(",", ".") +
                "\n}\n";
    }
}
