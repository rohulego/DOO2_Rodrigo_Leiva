package main.java.cl.duoc.models;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Tiempos en min
        int tiempoBase = 20;
        int tiempoPorKm = (int) Math.round(1.5 * getDistanciaKm());
        return tiempoBase + tiempoPorKm;
    }
}