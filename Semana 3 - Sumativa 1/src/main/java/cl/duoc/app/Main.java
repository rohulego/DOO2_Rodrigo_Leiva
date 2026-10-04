package main.java.cl.duoc.app;

import main.java.cl.duoc.contrato.Despachable;
import main.java.cl.duoc.models.*;
import main.java.cl.duoc.servicio.ControladorDeEnvios;
import main.java.cl.duoc.contrato.Cancelable;
import main.java.cl.duoc.contrato.Rastreable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControladorDeEnvios gestor = new ControladorDeEnvios();
        List<Pedido> listaPedidos = new ArrayList<>();

        //NOTA, DEBIMOS CREAR EL ATRIBUTO REPARTIDOR PARA QUE QUEDE MEJOR O DEBERIAMOS MANEJAR UNA LISTA CON LOS REPARTIDORES, SUS CARACTERISTICAS Y DISPONIBILIDAD PARA PODER ASIGNARLOS POR PEDIDO
        //objetos precargador
        listaPedidos.add(new PedidoComida(1, 3.0, "Av. Siempre Viva 666"));
        listaPedidos.add(new PedidoEncomienda(2, 7.0, "Jorge Santander Torres 1000"));
        listaPedidos.add(new PedidoExpress(3, 5.0, "Calle Falsa 123"));

        int opcion = 0;

        //menu interactivo
        do {
            System.out.println("--- BIENVENIDOS A SPEEDFAST ---");
            System.out.println("Selecciona tu opción:");
            System.out.println("1. Ver detalles");
            System.out.println("2. Historial");
            System.out.println("3. Cancelar pedido");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            System.out.println();

            switch (opcion) {
                case 1:

                    System.out.println("=== DETALLES DE PEDIDOS ===");
                    for (Pedido p : listaPedidos) {


                        if (p instanceof PedidoComida) {
                            System.out.println("-Pedido de Comida-");
                            p.mostrarResumen("Homero Simpson");
                        } else if (p instanceof PedidoEncomienda) {
                            System.out.println("-Pedido de Encomienda-");
                            p.mostrarResumen("Rodrigo Leiva");
                        } else if (p instanceof PedidoExpress) {
                            System.out.println("-Pedido Express-");
                            p.mostrarResumen("Bart Simpson");
                        }

                        // ejecutamos @Override del metodo asignarRepartidor() de la subclase
                        p.asignarRepartidor();

                        // el metodo del gestor
                        if(p instanceof Despachable){
                            gestor.procesarDespacho((Despachable)p);
                        }

                        System.out.println();
                    }
                    break;

                case 2:

                    System.out.println("=== HISTORIAL DE ENTREGAS ===");
                    for (Pedido p : listaPedidos) {
                        if (p instanceof Rastreable) {
                            if (p instanceof PedidoComida) {
                                gestor.consultarHistorial((Rastreable) p, "Homero Simpson");
                            } else if (p instanceof PedidoEncomienda) {
                                gestor.consultarHistorial((Rastreable) p, "Rodrigo Leiva");
                            } else if (p instanceof PedidoExpress) {
                                gestor.consultarHistorial((Rastreable) p, "Bart Simpson");
                            }
                        }
                    }
                    System.out.println();
                    break;
//NOTA: PARA FUTURO CREAR OPCION CUANDO ELIMINEMOS A TODOS LOS PEDIDOS
                case 3:

                    System.out.print("Ingresa el ID del pedido a cancelar: ");
                    int idBuscar;
                    try {
                        idBuscar = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        idBuscar = 0;
                    }

                    boolean encontrado = false;

                    for (int i = 0; i < listaPedidos.size(); i++) {
                        Pedido p = listaPedidos.get(i);
                        if (p.getIdPedido() == idBuscar) {
                            if (p instanceof Cancelable) {
                                // ejecutamos metodo cancelar() del ControladordeEnvios
                                gestor.procesarCancelacion((Cancelable) p);
                            }
                            // removemos el objeto escogido
                            listaPedidos.remove(i);
                            encontrado = true;
                            System.out.println();
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontró ningún pedido con el ID #" + idBuscar + "\n");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo de SpeedFast...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.\n");
                    break;
            }

        } while (opcion != 4);

        scanner.close();
    }
}

//NOTA FINAL: PARA FUTURO: PODER IMPLEMENTAR EL AGREGAR PEDIDO, ASIGNAR REPARTIDOR AUTOMATICAMENTE E INFO POR CONSOLA CUANDO NO SE TENGAN PEDIDOS.