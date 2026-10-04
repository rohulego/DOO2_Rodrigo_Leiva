package views;

import dao.PedidoDAO;
import models.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private DefaultTableModel model;
    private JTable tablaPedidos;

    public VentanaListaPedidos() {
        setTitle("SpeedFast - Listado de Pedidos");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Configuración de la JTable
        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        model = new DefaultTableModel(columnas, 0);
        tablaPedidos = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        add(scrollPane, BorderLayout.CENTER);

        // Botón de Refrescar
        JButton btnRefrescar = new JButton("Refrescar Tabla");
        btnRefrescar.addActionListener(e -> cargarDatosTabla());

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnRefrescar);
        add(panelInferior, BorderLayout.SOUTH);

        // Cargar datos iniciales al abrir desde MySQL
        cargarDatosTabla();
    }

    public void cargarDatosTabla() {
        model.setRowCount(0); // Limpiar datos previos
        List<Pedido> lista = pedidoDAO.listarTodos();

        for (Pedido p : lista) {
            Object[] fila = {p.getId(), p.getDireccionEntrega(), p.getTipo(), p.getEstado()};
            model.addRow(fila);
        }
    }
}