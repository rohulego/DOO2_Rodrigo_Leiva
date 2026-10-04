package main.java.app;

import main.java.models.EstadoPedido;
import main.java.models.Pedido;
import main.java.models.sincronizacion.ZonaDeCarga;
import main.java.views.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Instancia compartida de la ZonaDeCarga
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // Pedidos por defecto del ejercicio previo
        zonaDeCarga.agregarPedido(new Pedido(1, "Calle Falsa 123", "Comida", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(2, "Avenida Siempre Viva 742", "Encomienda", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(3, "Pasaje Negra Arroyo 308", "Express", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(4, "Privet Drive N°4", "Encomienda", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(5, "Avenida Slough N°1725", "Comida", EstadoPedido.PENDIENTE));

        // Inicializar la interfaz gráfica en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(zonaDeCarga);
            ventana.setVisible(true);
        });
    }
}