package cl.duoc.model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, double distanciaKm, String direccionEntrega) {
        super(idPedido, "Encomienda", distanciaKm, direccionEntrega);
    }

    @Override
    public double calcularTiempoEntrega(){
        //tiempos en min
        int tiempoBase = 20;
        int tiempoPorKm = (int) Math.round(1.5 * distanciaKm);
        return tiempoBase + tiempoPorKm;

    }
}

