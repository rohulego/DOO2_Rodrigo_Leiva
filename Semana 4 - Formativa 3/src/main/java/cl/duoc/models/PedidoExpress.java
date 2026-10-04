package main.java.cl.duoc.models;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Tiempos en min
        int tiempoBase = 10;
        if (getDistanciaKm() > 5) {
            tiempoBase += 5;
        }
        return tiempoBase;
    }
}