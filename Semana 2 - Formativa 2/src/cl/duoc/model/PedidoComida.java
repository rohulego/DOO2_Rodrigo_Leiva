package cl.duoc.model;

public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, double distanciaKm, String direccionEntrega) {
        super(idPedido, "Comida", distanciaKm, direccionEntrega);
    }

    @Override
    public double calcularTiempoEntrega(){
        //tiempos en min
        int tiempoBase = 15;
        int tiempoPorKm = (int) Math.round(2 * distanciaKm);
        return tiempoBase + tiempoPorKm;

    }

}

