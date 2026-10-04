package views;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("SpeedFast - Gestión de Entregas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnRegistrar = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listar Pedidos");
        JButton btnIniciarEntrega = new JButton("Asignar Repartidores / Iniciar Entrega");

        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido vRegistro = new VentanaRegistroPedido();
            vRegistro.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos vLista = new VentanaListaPedidos();
            vLista.setVisible(true);
        });

        // AQUÍ CONECTAMOS LA VENTANA DE ASIGNACIÓN
        btnIniciarEntrega.addActionListener(e -> {
            VentanaAsignacionEntrega vAsignacion = new VentanaAsignacionEntrega();
            vAsignacion.setVisible(true);
        });

        panel.add(btnRegistrar);
        panel.add(btnListar);
        panel.add(btnIniciarEntrega);

        add(panel);
    }
}