package main.java.cl.models;

import main.java.cl.interfaz.Repartible;

//clase base que implementa la interfaz y que sobreescribe el metodo. Que implemente Repartible obliga a implementar el metodo


public class Pedido implements Repartible {

    private int idPedido;
    private String direccionEntrega;
    private String tipoPedido;

    public Pedido(int idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    //este se sobreescribe
    @Override
    public void asignarRepartidor(){
        System.out.println("\n[Pedido: " + tipoPedido + "]");
        System.out.println("Asignando repartidor...");

    }

    public void asignarRepartidor(String nombreRepartidor){
        System.out.println("→ Pedido asignado a " + nombreRepartidor);

    }

}
