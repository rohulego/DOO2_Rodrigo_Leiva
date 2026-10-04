package models;

public class Entrega {
    private int id;
    private int idPedido;
    private int idRepartidor;

    public Entrega() {}

    public Entrega(int idPedido, int idRepartidor) {
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
    }

    public Entrega(int id, int idPedido, int idRepartidor) {
        this.id = id;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdRepartidor() { return idRepartidor; }
    public void setIdRepartidor(int idRepartidor) { this.idRepartidor = idRepartidor; }
}