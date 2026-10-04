package cl.duoc.model;

public abstract class Pedido {

    protected int idPedido;
    protected String tipoEntrega;
    protected double distanciaKm;
    protected String direccionEntrega;

    public Pedido(int idPedido, String tipoEntrega, double distanciaKm, String direccionEntrega) {
        this.idPedido = idPedido;
        this.tipoEntrega = tipoEntrega;
        this.distanciaKm = distanciaKm;
        this.direccionEntrega = direccionEntrega;
    }

    public void mostrarResumen(){
        System.out.println("Pedido " + tipoEntrega + " #" + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Tiempo estimado de entrega: " + calcularTiempoEntrega() + " min" + "\n");
    }

    public abstract double calcularTiempoEntrega();

}
