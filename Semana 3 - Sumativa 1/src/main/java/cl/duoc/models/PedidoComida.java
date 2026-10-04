package main.java.cl.duoc.models;

import main.java.cl.duoc.contrato.Cancelable;
import main.java.cl.duoc.contrato.Despachable;
import main.java.cl.duoc.contrato.Rastreable;

public class PedidoComida extends Pedido implements Despachable, Cancelable, Rastreable{

    public PedidoComida(int idPedido, double distanciaKm, String direccionEntrega) {
        super(idPedido, distanciaKm, direccionEntrega);
    }

    @Override
    public double calcularTiempoEntrega() {
        //tiempos en min
        int tiempoBase = 15;
        int tiempoPorKm = (int) Math.round(2 * distanciaKm);
        return tiempoBase + tiempoPorKm;

    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignado repartidor con mochila termica");
    }


    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido de Comida #" + idPedido + "...");
        System.out.println("Pedido cancelado exitosamente\n");
    }

    @Override
    public void despachar() {
        System.out.println("Pedido de Comida despachado correctamente\n");
    }

    @Override
    public void verHistorial(String nombreRepartidor) {
        System.out.println("- Pedido de Comida #" + idPedido + " - entregado por " + nombreRepartidor);
    }
}