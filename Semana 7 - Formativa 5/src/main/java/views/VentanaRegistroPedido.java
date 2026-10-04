package views;

import dao.PedidoDAO;
import models.EstadoPedido;
import models.Pedido;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private JTextField txtDireccion;
    private JComboBox<String> cbTipo;

    public VentanaRegistroPedido() {
        setTitle("SpeedFast - Registro de Pedido");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout de formulario con GridLayout
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Campos
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
        String direccion = txtDireccion.getText().trim();
        String tipo = (String) cbTipo.getSelectedItem();

        // Validación de campos
        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete la dirección.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear objeto Pedido
        Pedido nuevoPedido = new Pedido(direccion, tipo, EstadoPedido.PENDIENTE);

        // Guardar en la base de datos MySQL
        if (pedidoDAO.guardar(nuevoPedido)) {
            JOptionPane.showMessageDialog(this, "Pedido #" + nuevoPedido.getId() + " registrado con éxito en MySQL.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            txtDireccion.setText("");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el pedido en la base de datos.", "Error de Persistencia", JOptionPane.ERROR_MESSAGE);
        }
    }
}