package main.java.models;

import main.java.models.sincronizacion.ZonaDeCarga;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ZonaDeCarga getZonaDeCarga() {
        return zonaDeCarga;
    }

    public void setZonaDeCarga(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        while (true) {
            // 1. Retira pedido
            Pedido pedido = zonaDeCarga.retirarPedido();

            // Si no hay pedido, avisa y termina el hilo
            if (pedido == null) {
                System.out.println("[Repartidor] " + nombre + ": No hay más pedidos disponibles.");
                break;
            }

            // 2. Cambia el estado del pedido y muestra mensaje
            pedido.setEstado(EstadoPedido.EN_REPARTO);
            System.out.println("[Repartidor] " + nombre + " salió con el pedido #" + pedido.getId() + ". Estado: " + pedido.getEstado());

            // 3. Simula la entrega con Thread.sleep()
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("[Repartidor] " + nombre + " fue interrumpido durante la entrega.");
                Thread.currentThread().interrupt();
                break;
            }

            // 4. Cambia el estado a ENTREGADO
            pedido.setEstado(EstadoPedido.ENTREGADO);
            System.out.println("[Repartidor] " + nombre + " finalizó la entrega del pedido #" + pedido.getId() + ". Estado final: " + pedido.getEstado());
        }
    }
}