package main.java.cl.duoc.models;

public abstract class Pedido {

    protected int idPedido;
    protected double distanciaKm;
    protected String direccionEntrega;

    public Pedido(int idPedido, double distanciaKm, String direccionEntrega) {
        this.idPedido = idPedido;
        this.distanciaKm = distanciaKm;
        this.direccionEntrega = direccionEntrega;

    }

    public int getIdPedido() {
        return idPedido;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }


    //Metodo del calculo del tiempo personalizado por cada subclase
    public abstract double calcularTiempoEntrega();

    //Metodo para sobreescribir por subclases (asignacion automática)
    public abstract void asignarRepartidor();

    //Metodo SOBRECARGADO (asignacion manual)
    public void asignarRepartidor(String nombre){
        System.out.println("Repartidor asignado: " + nombre);
    }

    public void mostrarResumen(String nombreRepartidor){
        System.out.println("Pedido #" + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        asignarRepartidor(nombreRepartidor);
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " min");


    }
}