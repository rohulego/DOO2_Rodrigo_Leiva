package main.java.app;

import main.java.models.EstadoPedido;
import main.java.models.Pedido;
import main.java.models.Repartidor;
import main.java.models.sincronizacion.ZonaDeCarga;

public class Main {
    public static void main(String[] args) {
        // Instancia de ZonaDeCarga
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // 5 pedidos agregador
        zonaDeCarga.agregarPedido(new Pedido(1, "Calle Falsa 123", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(2, "Avenida Siempre Viva 742", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(3, "Pasaje Negra Arroyo 308", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(4, "Privet Drive N°4", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(5, "Avenida Slough N°1725", EstadoPedido.PENDIENTE));

        System.out.println("\n--- INICIANDO PROCESO DE ENTREGA ---\n");

        // Creamos tres repartidores
        Repartidor repartidor1 = new Repartidor("Max Salazar", zonaDeCarga);
        Repartidor repartidor2 = new Repartidor("Rodrigo Leiva", zonaDeCarga);
        Repartidor repartidor3 = new Repartidor("Peter Parker", zonaDeCarga);

        // Creamos e iniciamos los 3 hilos de tipo Repartidos
        Thread hilo1 = new Thread(repartidor1);
        Thread hilo2 = new Thread(repartidor2);
        Thread hilo3 = new Thread(repartidor3);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        //esperamos finalizacion con join()
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
            Thread.currentThread().interrupt();
        }

        // mensaje final
        System.out.println("\nTodos los pedidos han sido entregados correctamente.");
    }
}