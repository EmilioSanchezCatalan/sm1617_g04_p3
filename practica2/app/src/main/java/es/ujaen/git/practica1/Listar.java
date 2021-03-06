package es.ujaen.git.practica1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 *
 * Clase encargada de hacer la conexión con el servidor para recibir
 * el listado del menu
 */

public class Listar implements Runnable, Service, Cliente {

    Handler mhandler;

    public Listar(Handler handler) {
        mhandler = handler;
    }

    @Override
    public void run() {
        //Acceso a la informacion del servidor
        String jMenu = listar();

        //Envio de datos mediante handler
        Message msg = new Message();
        Bundle datos = new Bundle();
        datos.putString("key", jMenu);
        msg.setData(datos);
        mhandler.sendMessage(msg);


    }

    @Override
    public String autentificacion(String cod_mesa, String num_session) {
        return null;
    }

    /**
     * Metodo para llamar a listar.php
     * @return devuelve la respuesta del servidor.
     */
    @Override
    public String listar() {
        Datos datos = new Datos(1);
        Mensaje mensaje = new Mensaje(1, datos);
        ServiceRest rest = new ServiceRest(mensaje.toString());
        return rest.reqGet();
    }

    @Override
    public String cerrar_session(String cod_mensa, String num_session) {
        return null;
    }

    @Override
    public String pedido(String cod_pedido, String cantidad) {
        return null;
    }

    @Override
    public String verificacion(String sesion_id, String expires) {
        return null;
    }
}
