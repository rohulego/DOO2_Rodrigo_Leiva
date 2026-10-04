package main.java.models;

public class Pedido {
    private int id;
    private String direccionEntrega;
    private String tipo;
    private EstadoPedido estado;

    // Constructor completo
    public Pedido(int id, String direccionEntrega, String tipo, EstadoPedido estado) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = estado;
    }

    // Constructor de compatibilidad (por si no se especifica tipo)
    public Pedido(int id, String direccionEntrega, EstadoPedido estado) {
        this(id, direccionEntrega, "Estándar", estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setEstado(String nuevoEstado) {
        this.estado = EstadoPedido.valueOf(nuevoEstado.toUpperCase());
    }

    @Override
    public String toString() {
        return id + " - Tipo: " + tipo + " - Dirección: " + direccionEntrega + " - Estado: " + estado;
    }
}