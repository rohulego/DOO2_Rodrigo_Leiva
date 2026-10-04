package main.java.cl.duoc.models;

import main.java.cl.duoc.contrato.Cancelable;
import main.java.cl.duoc.contrato.Despachable;
import main.java.cl.duoc.contrato.Rastreable;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    private final int idPedido;
    private final String direccionEntrega;
    private final double distanciaKm;
    private volatile Estadopedido estado;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        if (idPedido <= 0) {
            throw new IllegalArgumentException("El ID del pedido debe ser mayor a 0");
        }
        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }

        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = Estadopedido.PENDIENTE;
    }

    public abstract int calcularTiempoEntrega();

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public Estadopedido getEstado() {
        return estado;
    }

    public synchronized void cambiarEstado(Estadopedido nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        this.estado = nuevoEstado;
    }

    @Override
    public void despachar() {
        cambiarEstado(Estadopedido.EN_CAMINO);
    }

    @Override
    public void cancelar() {
        cambiarEstado(Estadopedido.CANCELADO);
    }

    @Override
    public String obtenerUbicacionActual() {
        return "Pedido #" + idPedido + " - Estado actual: " + estado;
    }

    public void mostrarResumen() {
        System.out.printf("Pedido #%d | Dirección: %s | Distancia: %.1f km | Tiempo Est.: %d seg | Estado: %s%n",
                idPedido, direccionEntrega, distanciaKm, calcularTiempoEntrega(), estado);
    }
}