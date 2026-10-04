package main.java.cl.app;

import main.java.cl.models.Pedido;

import main.java.cl.models.PedidoComida;
import main.java.cl.models.PedidoEncomienda;
import main.java.cl.models.PedidoExpress;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Pedido pedido1 = new PedidoComida(1, "Los Pinares 123");
        Pedido pedido2 = new PedidoEncomienda(2, "Calle Falsa 123");
        Pedido pedido3 = new PedidoExpress(3, "Los Copihues 333");

        pedido1.asignarRepartidor(); //metodo sobreescrito
        pedido1.asignarRepartidor("Juan N."); // metodo sobrecargado

        pedido2.asignarRepartidor();
        pedido2.asignarRepartidor("Rodrigo L.");

        pedido3.asignarRepartidor();
        pedido3.asignarRepartidor("Lilian J.");

    }
}
