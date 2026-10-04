package views;

import services.ServicioEntrega;

import javax.swing.*;
import java.awt.*;

public class VentanaAsignacionEntrega extends JFrame {

    private final ServicioEntrega servicioEntrega = new ServicioEntrega();

    private JTextArea txtLog;
    private JButton btnIniciar;

    public VentanaAsignacionEntrega() {
        setTitle("SpeedFast - Simulación de Reparto en Tiempo Real");
        setSize(520, 380);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtLog);

        btnIniciar = new JButton("Iniciar Simulación de Reparto");
        btnIniciar.addActionListener(e -> ejecutarSimulacion());

        add(new JLabel(" Registro de Proceso y Tiempos en MySQL:"), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnIniciar, BorderLayout.SOUTH);
    }

    private void ejecutarSimulacion() {
        btnIniciar.setEnabled(false);

        // Delegamos completamente la simulación y los hilos a la capa de servicio
        servicioEntrega.procesarSimulacionAsync(
                mensaje -> agregarLog(mensaje),
                () -> SwingUtilities.invokeLater(() -> btnIniciar.setEnabled(true))
        );
    }

    private void agregarLog(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            txtLog.append(mensaje + "\n");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        });
    }
}