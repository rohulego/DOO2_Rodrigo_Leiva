package main.java.cl.models;

public class PedidoComida extends Pedido{

    public PedidoComida(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Comida");
    }

    //sobrescribe
    @Override
    public void asignarRepartidor() {
        super.asignarRepartidor();
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor){
        System.out.println("→ Verificando mochila termica... OK");
        super.asignarRepartidor(nombreRepartidor);

    }
}
