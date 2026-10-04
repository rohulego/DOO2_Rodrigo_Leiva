package main.java.views;

import main.java.models.Repartidor;
import main.java.models.sincronizacion.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final ZonaDeCarga zonaDeCarga;

    public VentanaPrincipal(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Componentes / Botones
        JButton btnRegistrar = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listar Pedidos");
        JButton btnIniciarEntrega = new JButton("Asignar Repartidores / Iniciar Entrega");

        // Eventos de los botones
        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido vRegistro = new VentanaRegistroPedido(this.zonaDeCarga);
            vRegistro.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos vLista = new VentanaListaPedidos(this.zonaDeCarga);
            vLista.setVisible(true);
        });

        btnIniciarEntrega.addActionListener(e -> iniciarEntregaSimulada());

        // Agregar botones al panel
        panel.add(btnRegistrar);
        panel.add(btnListar);
        panel.add(btnIniciarEntrega);

        add(panel);
    }

    // Simula la entrega concurrente usando hilos como en el main.java.app.Main original
    private void iniciarEntregaSimulada() {
        if (zonaDeCarga.obtenerTodosLosPedidos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pedidos registrados en la Zona de Carga.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "¡Proceso de entrega iniciado con 3 repartidores!\nRevisa la consola para ver la ejecución de los hilos.", "SpeedFast", JOptionPane.INFORMATION_MESSAGE);

        Repartidor r1 = new Repartidor("Max Salazar", zonaDeCarga);
        Repartidor r2 = new Repartidor("Rodrigo Leiva", zonaDeCarga);
        Repartidor r3 = new Repartidor("Peter Parker", zonaDeCarga);

        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }
}