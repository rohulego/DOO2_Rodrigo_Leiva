package app;

import dao.ConexionDB;
import views.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Garantiza que la base de datos y las tablas existan antes de abrir la interfaz
        ConexionDB.inicializarBaseDeDatos();

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}