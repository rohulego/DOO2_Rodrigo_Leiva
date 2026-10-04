package main.java.cl.duoc.models;

import main.java.cl.duoc.contrato.Cancelable;
import main.java.cl.duoc.contrato.Despachable;
import main.java.cl.duoc.contrato.Rastreable;

public class PedidoEncomienda extends Pedido implements Despachable, Cancelable, Rastreable {

    public PedidoEncomienda(int idPedido, double distanciaKm, String direccionEntrega) {
        super(idPedido, distanciaKm, direccionEntrega);
    }

    @Override
    public double calcularTiempoEntrega() {
        //tiempos en min
        int tiempoBase = 20;
        int tiempoPorKm = (int) Math.round(1.5 * distanciaKm);
        return tiempoBase + tiempoPorKm;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignado repartidor con furgoneta");

    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido de Encomienda #" + idPedido + "...");
        System.out.println("Pedido cancelado exitosamente\n");
    }

    @Override
    public void despachar() {
        System.out.println("Pedido de Encomienda despachado correctamente\n");
    }

    @Override
    public void verHistorial(String nombreRepartidor) {
        System.out.println("- Pedido de Comida #" + idPedido + " - entregado por " + nombreRepartidor);
    }
}
