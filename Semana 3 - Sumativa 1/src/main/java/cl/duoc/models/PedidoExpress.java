package main.java.cl.duoc.models;

import main.java.cl.duoc.contrato.Cancelable;
import main.java.cl.duoc.contrato.Despachable;
import main.java.cl.duoc.contrato.Rastreable;

public class PedidoExpress extends Pedido implements Despachable, Cancelable, Rastreable {

    public PedidoExpress(int idPedido, double distanciaKm, String direccionEntrega) {
        super(idPedido, distanciaKm, direccionEntrega);
    }

    @Override
    public double calcularTiempoEntrega() {
        //tiempos en min
        int tiempoBase = 10;
        if (distanciaKm > 5){
            tiempoBase += 5;
        }
        return tiempoBase;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignado repartidor mas cercano con disponibilidad inmediata");
    }


    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido Express #" + idPedido + "...");
        System.out.println("Pedido cancelado exitosamente\n");
    }

    @Override
    public void despachar() {
        System.out.println("Pedido Express despachado correctamente\n");
    }

    @Override
    public void verHistorial(String nombreRepartidor) {
        System.out.println("- Pedido Express #" + idPedido + " - entregado por " + nombreRepartidor);
    }
}
