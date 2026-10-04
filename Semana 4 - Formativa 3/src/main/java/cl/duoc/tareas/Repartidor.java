package main.java.cl.duoc.tareas;

import main.java.cl.duoc.models.Estadopedido;
import main.java.cl.duoc.models.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {

    private final String nombre;
    private final List<Pedido> listaPedidos;
    private final Random random = new Random();

    public Repartidor(String nombre, List<Pedido> listaPedidos) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del repartidor no puede ser nulo o vacio");
        }
        if (listaPedidos == null || listaPedidos.isEmpty()) {
            throw new IllegalArgumentException("La lista de pedidos no puede estar vacia");
        }
        this.nombre = nombre;
        this.listaPedidos = new ArrayList<>(listaPedidos);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();
        System.out.printf("[%s] %s inicio su ruta exclusiva con %d pedido(s).%n",
                nombreHilo, nombre, listaPedidos.size());

        for (Pedido pedido : listaPedidos) {
            try {
                // Obtener el tipo de pedido de forma dinamica (PedidoComida, PedidoEncomienda, PedidoExpress)
                String tipoPedido = pedido.getClass().getSimpleName();

                // Despachar
                pedido.despachar();
                int tiempoCalculadoMin = pedido.calcularTiempoEntrega();

                // Mensaje en consola informando el TIPO DE PEDIDO que esta repartiendo
                System.out.printf("[%s] %s esta repartiendo un [%s] (ID: #%d) hacia '%s' (Est: %d min)...%n",
                        nombreHilo, nombre, tipoPedido, pedido.getIdPedido(), pedido.getDireccionEntrega(), tiempoCalculadoMin);

                // Simular tiempo de viaje - variacionAleatoria entrega un tiempo random como simulando el trafico vehicular
                int variacionAleatoriaSeg = random.nextInt(3);
                long tiempoEsperaMs = (tiempoCalculadoMin * 100L) + (variacionAleatoriaSeg * 1_000L);

                Thread.sleep(tiempoEsperaMs);

                // Confirmar entrega
                pedido.cambiarEstado(Estadopedido.ENTREGADO);
                System.out.printf("[%s] %s ENTREGO el [%s] (ID: #%d) en '%s'.%n",
                        nombreHilo, nombre, tipoPedido, pedido.getIdPedido(), pedido.getDireccionEntrega());

            } catch (InterruptedException e) {
                String tipoPedido = pedido.getClass().getSimpleName();
                pedido.cancelar();
                Thread.currentThread().interrupt();
                System.out.printf("[%s] %s fue INTERRUMPIDO mientras entregaba el [%s] (ID: #%d).%n",
                        nombreHilo, nombre, tipoPedido, pedido.getIdPedido());
                break;
            }
        }

        System.out.printf("[%s] %s finalizo todas sus entregas exclusivas.%n", nombreHilo, nombre);
    }
}