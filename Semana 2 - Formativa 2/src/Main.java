import cl.duoc.model.Pedido;
import cl.duoc.model.PedidoComida;
import cl.duoc.model.PedidoEncomienda;
import cl.duoc.model.PedidoExpress;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Pedido[] pedidos = {
                new PedidoComida(
                        001,
                        2,
                        "Linares 2235"),
                new PedidoEncomienda(
                        002,
                        15,
                        "Jorge Santander Torres 1010"),
                new PedidoExpress(
                        003,
                        9,
                        "Calle Falsa 123")

        };

        for (Pedido lista : pedidos){
            lista.mostrarResumen();

        }
    }
}