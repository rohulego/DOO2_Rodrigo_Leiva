package main.java.views;

import main.java.models.EstadoPedido;
import main.java.models.Pedido;
import main.java.models.sincronizacion.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private final ZonaDeCarga zonaDeCarga;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cbTipo;

    public VentanaRegistroPedido(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        setTitle("SpeedFast - Registro de Pedido");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout de formulario con GridLayout
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Campos
        panelFormulario.add(new JLabel("ID Pedido:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Tipo de Pedido:"));
        String[] tipos = {"Comida", "Encomienda", "Express"};
        cbTipo = new JComboBox<>(tipos);
        panelFormulario.add(cbTipo);

        JButton btnGuardar = new JButton("Guardar");
        panelFormulario.add(new JLabel()); // Espacio en blanco para cuadrar la grilla
        panelFormulario.add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarPedido());

        add(panelFormulario);
    }

    private void guardarPedido() {
        String idText = txtId.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String tipo = (String) cbTipo.getSelectedItem();

        // Validación de campos
        if (idText.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Pedido nuevoPedido = new Pedido(id, direccion, tipo, EstadoPedido.PENDIENTE);

            // Agregar al contenedor común
            zonaDeCarga.agregarPedido(nuevoPedido);

            JOptionPane.showMessageDialog(this, "Pedido #" + id + " registrado con éxito.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos y cerrar
            txtId.setText("");
            txtDireccion.setText("");
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}