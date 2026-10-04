package main.java.cl.duoc.app;

import main.java.cl.duoc.models.Pedido;
import main.java.cl.duoc.models.PedidoComida;
import main.java.cl.duoc.models.PedidoEncomienda;
import main.java.cl.duoc.models.PedidoExpress;
import main.java.cl.duoc.tareas.Repartidor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {


        System.out.println("===== SPEEDFAST - ASIGNACION EXCLUSIVA DE PEDIDOS =====");


        // Creacion de Pedidos por Categoria

        // Exclusivos Comida (Rodrigo)
        Pedido comida1 = new PedidoComida(101, "Av. Espana 123", 2.5);
        Pedido comida2 = new PedidoComida(102, "Pasaje Los Olivos 321", 1.8);

        // Exclusivos Encomienda (Lilian)
        Pedido encomienda1 = new PedidoEncomienda(201, "Av. Providencia 789", 4.0);
        Pedido encomienda2 = new PedidoEncomienda(202, "Calle Central 555", 5.0);

        // Exclusivos Express (Max)
        Pedido express1 = new PedidoExpress(301, "Calle Las Flores 456", 1.0);
        Pedido express2 = new PedidoExpress(302, "Alameda 900", 6.2);

        // Creacion de repartidores con Asignacion Exclusiva (estas son las tareas)
        Repartidor rodrigo = new Repartidor("Rodrigo (Moto)", List.of(comida1, comida2));
        Repartidor lilian  = new Repartidor("Lilian (Furgoneta)", List.of(encomienda1, encomienda2));
        Repartidor max     = new Repartidor("Max (Auto)", List.of(express1, express2));

        // Resumen Inicial detallado mostrando el Tipo de Pedido
        System.out.println("\n--- RESUMEN INICIAL Y ASIGNACION ---");

        // Recorremos lista y mostramos por repartidores
        System.out.println("\n Repartidor: Rodrigo (Moto) [Exclusivo COMIDA]");
        rodrigo.getListaPedidos().forEach(p -> {
            System.out.print("   -> Tipo: " + p.getClass().getSimpleName() + " | ");
            p.mostrarResumen();
        });

        System.out.println("\n Repartidor: Lilian (Furgoneta) [Exclusiva ENCOMIENDA]");
        lilian.getListaPedidos().forEach(p -> {
            System.out.print("   -> Tipo: " + p.getClass().getSimpleName() + " | ");
            p.mostrarResumen();
        });

        System.out.println("\n Repartidor: Max (Auto) [Exclusivo EXPRESS]");
        max.getListaPedidos().forEach(p -> {
            System.out.print("   -> Tipo: " + p.getClass().getSimpleName() + " | ");
            p.mostrarResumen();
        });

        System.out.println("\n--------------------------------------------------\n");

        // Ejecucion mediante ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(3);
        long tiempoInicio = System.currentTimeMillis();

        // Esto es en vez de start
        executor.execute(rodrigo);
        executor.execute(lilian);
        executor.execute(max);

        executor.shutdown();


        try {
            // Esto se usa en vez de join
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("La simulacion fue interrumpida.");
        }

        double segundosTranscurridos = (System.currentTimeMillis() - tiempoInicio) / 1_000.0;

        System.out.println();
        System.out.printf(" Simulacion finalizada en %.2f segundos.%n", segundosTranscurridos);
        System.out.println("=== Fin de simulación ===");
    }
}