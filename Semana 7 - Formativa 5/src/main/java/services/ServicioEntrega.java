package services;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import models.Entrega;
import models.EstadoPedido;
import models.Pedido;
import models.Repartidor;

import java.util.List;
import java.util.function.Consumer;

public class ServicioEntrega {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final RepartidorDAO repartidorDAO = new RepartidorDAO();
    private final EntregaDAO entregaDAO = new EntregaDAO();

    // Método que ejecuta la simulación en un hilo secundario
    public void procesarSimulacionAsync(Consumer<String> logger, Runnable alFinalizar) {
        new Thread(() -> {
            List<Pedido> pedidos = pedidoDAO.listarTodos();
            List<Repartidor> repartidores = repartidorDAO.listarTodos();

            if (repartidores.isEmpty()) {
                repartidorDAO.guardar(new Repartidor("Max Salazar"));
                repartidorDAO.guardar(new Repartidor("Rodrigo Leiva"));
                repartidorDAO.guardar(new Repartidor("Peter Parker"));
                repartidores = repartidorDAO.listarTodos();
            }

            logger.accept("=== VERIFICANDO PEDIDOS PENDIENTES Y EN REPARTO ===");

            // 1. Completar pedidos en estado EN_REPARTO previa
            int completadosPrevios = 0;
            for (Pedido p : pedidos) {
                if (p.getEstado() == EstadoPedido.EN_REPARTO) {
                    logger.accept("\n[FINALIZANDO PREVIO] Pedido #" + p.getId() + " estaba en reparto.");
                    dormir(1500);
                    pedidoDAO.actualizarEstado(p.getId(), EstadoPedido.ENTREGADO);
                    logger.accept(" -> [ENTREGADO] ¡Pedido #" + p.getId() + " marcado como ENTREGADO en MySQL!");
                    completadosPrevios++;
                }
            }

            // 2. Procesar nuevos pedidos en PENDIENTE
            int nuevosProcesados = 0;
            for (Pedido p : pedidos) {
                if (p.getEstado() == EstadoPedido.PENDIENTE) {
                    Repartidor repartidor = repartidores.get(nuevosProcesados % repartidores.size());

                    // Cambiar estado a EN_REPARTO e insertar registro en 'entrega'
                    pedidoDAO.actualizarEstado(p.getId(), EstadoPedido.EN_REPARTO);
                    Entrega entrega = new Entrega(p.getId(), repartidor.getId());
                    entregaDAO.guardar(entrega);

                    logger.accept("\n[EN REPARTO] Pedido #" + p.getId() + " (" + p.getTipo() + ")");
                    logger.accept(" -> Repartidor: " + repartidor.getNombre());
                    logger.accept(" -> Estado actualizado a EN_REPARTO en MySQL.");
                    logger.accept(" -> Repartidor en camino al destino...");

                    dormir(3000);

                    // Cambiar a ENTREGADO
                    pedidoDAO.actualizarEstado(p.getId(), EstadoPedido.ENTREGADO);
                    logger.accept(" -> [ENTREGADO] ¡Pedido #" + p.getId() + " entregado con éxito!");

                    nuevosProcesados++;
                }
            }

            if (completadosPrevios == 0 && nuevosProcesados == 0) {
                logger.accept("\nNo hay pedidos 'PENDIENTES' ni 'EN_REPARTO' para procesar.");
            } else {
                logger.accept("\n=== PROCESO Y SIMULACIÓN COMPLETADOS EN MYSQL ===");
            }

            if (alFinalizar != null) {
                alFinalizar.run();
            }
        }).start();
    }

    private void dormir(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}