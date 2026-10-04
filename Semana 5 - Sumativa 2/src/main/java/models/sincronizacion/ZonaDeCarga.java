package main.java.models.sincronizacion;

import main.java.models.Pedido;
import main.java.models.EstadoPedido;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ZonaDeCarga {

    // Almacena los pedidos de forma concurrente
    private final BlockingQueue<Pedido> colaPedidos;
    // Cerrojo explícito para proteger las operaciones críticas
    private final ReentrantLock lock = new ReentrantLock();

    public ZonaDeCarga() {
        this.colaPedidos = new LinkedBlockingQueue<>();
    }

    //Agrega pedido y le asigna PENDIENTE
    public synchronized void agregarPedido(Pedido p) {
        lock.lock();
        try {
            p.setEstado(EstadoPedido.PENDIENTE);
            colaPedidos.add(p);
            System.out.println("[ZonaDeCarga] Pedido #" + p + " agregado.");
        } finally {
            lock.unlock();
        }
    }

    //Metodo para retirar pedido
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



}