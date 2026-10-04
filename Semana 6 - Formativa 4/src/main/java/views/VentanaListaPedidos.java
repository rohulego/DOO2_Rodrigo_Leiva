package main.java.views;

import main.java.models.Pedido;
import main.java.models.sincronizacion.ZonaDeCarga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private final ZonaDeCarga zonaDeCarga;
    private DefaultTableModel model;
    private JTable tablaPedidos;

    public VentanaListaPedidos(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

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

        // Cargar datos iniciales al abrir
        cargarDatosTabla();
    }

    public void cargarDatosTabla() {
        model.setRowCount(0); // Limpiar datos previos
        List<Pedido> lista = zonaDeCarga.obtenerTodosLosPedidos();

        for (Pedido p : lista) {
            Object[] fila = {p.getId(), p.getDireccionEntrega(), p.getTipo(), p.getEstado()};
            model.addRow(fila);
        }
    }
}