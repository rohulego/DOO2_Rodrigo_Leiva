package cl.duoc.model;

public class PedidoExpress extends Pedido{

    public PedidoExpress(int idPedido, double distanciaKm, String direccionEntrega) {
        super(idPedido, "Express", distanciaKm, direccionEntrega);
    }

    @Override
    public double calcularTiempoEntrega(){
        //tiempos en min
        int tiempoBase = 10;
        if (distanciaKm > 5){
            tiempoBase += 5;
        }
        return tiempoBase;
    }
}
