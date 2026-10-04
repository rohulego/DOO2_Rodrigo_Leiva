package main.java.cl.duoc.models;

public class PedidoComida extends Pedido {


    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempoBase = 15;
        int tiempoPorKm = (int) Math.round(2 * getDistanciaKm());
        return tiempoBase + tiempoPorKm;
    }


}