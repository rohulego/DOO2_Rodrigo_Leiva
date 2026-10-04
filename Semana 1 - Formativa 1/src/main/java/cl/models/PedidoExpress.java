package main.java.cl.models;

public class PedidoExpress extends Pedido{

    public PedidoExpress(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Express");
    }

    @Override
    public void asignarRepartidor() {
        super.asignarRepartidor();
    }

    //esto es sobrecarga al ingresar un parametro
    public void asignarRepartidor(String nombreRepartidor){
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
        super.asignarRepartidor(nombreRepartidor);

    }
}
