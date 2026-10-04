package main.java.cl.duoc.servicio;

import main.java.cl.duoc.contrato.Cancelable;
import main.java.cl.duoc.contrato.Despachable;
import main.java.cl.duoc.contrato.Rastreable;

public class ControladorDeEnvios {

    //EJECUTAMOS INTERFAZ DESPACHABLE PARA CUALQUIER PEDIDO
    public void procesarDespacho(Despachable pedido){
        pedido.despachar();
    }

    //EJECUTAMOS INTERFAZ CANCELABLE PARA CUALQUIER PEDIDO
    public void procesarCancelacion(Cancelable pedido){
        pedido.cancelar();
    }

    //EJECUTAMOS INTERFAZ RASTREABLE PARA CUALQUIER PEDIDO
    public void consultarHistorial(Rastreable pedido, String nombreRepartidor){
        pedido.verHistorial(nombreRepartidor);
    }
}
