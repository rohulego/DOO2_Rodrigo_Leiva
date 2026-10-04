package main.java.models.sincronizacion;

import main.java.models.Pedido;
import main.java.models.EstadoPedido;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ZonaDeCarga {

    private final BlockingQueue<Pedido> colaPedidos;
    private final List<Pedido> historialPedidos; // Lista auxiliar para que la JTable pueda mostrar todos los pedidos cargados
    private final ReentrantLock lock = new ReentrantLock();

    public ZonaDeCarga() {
        this.colaPedidos = new LinkedBlockingQueue<>();
        this.historialPedidos = new ArrayList<>();
    }

    public synchronized void agregarPedido(Pedido p) {
        lock.lock();
        try {
            p.setEstado(EstadoPedido.PENDIENTE);
            colaPedidos.add(p);
            historialPedidos.add(p);
            System.out.println("[ZonaDeCarga] Pedido #" + p.getId() + " agregado.");
        } finally {
            lock.unlock();
        }
    }

    public synchronized Pedido retirarPedido() {
        lock.lock();
        try {
            Pedido pedido = colaPedidos.poll();
            if (pedido != null) {
                System.out.println("[ZonaDeCarga] Pedido #" + pedido.getId() + " acaba de ser retirado.");
            }
            return pedido;
        } finally {
            lock.unlock();
        }
    }

    // Método necesario para poblar el JTable en VentanaListaPedidos
    public synchronized List<Pedido> obtenerTodosLosPedidos() {
        lock.lock();
        try {
            return new ArrayList<>(historialPedidos);
        } finally {
            lock.unlock();
        }
    }
}