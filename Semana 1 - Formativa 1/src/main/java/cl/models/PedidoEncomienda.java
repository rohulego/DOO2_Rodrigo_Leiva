package main.java.cl.models;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Encomienda");
    }

    @Override
    public void asignarRepartidor() {
        super.asignarRepartidor();
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor){
        System.out.println("→ Validando peso y embalaje... OK");
        super.asignarRepartidor(nombreRepartidor);

    }
}

