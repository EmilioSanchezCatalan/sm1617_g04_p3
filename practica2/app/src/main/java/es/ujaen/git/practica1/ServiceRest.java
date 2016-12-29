package es.ujaen.git.practica1;

import android.app.Activity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */
public class ServiceRest implements Service {
    //Definimos variables
    private String urlget;

    /**
     * Constructor de la clase.
     * Le pasamos mensajeReq donde van los parametros.
     * Tambien damos forma a la url.
     * @param mensajeReq
     */
    public ServiceRest(String mensajeReq) {
        urlget = "http://" + servidor + ":" + port + "/" + servicio + "/" + mensajeReq;
    }

    /**
     *Método que realiza la conexión con el servidor mediante una url y obtiene como resultado la respuesta del servidor
     *
     * @return
     */
    public String reqGet() {
        String res = "";
        try {
            urlget = urlget.replace(" ", "%20");
            URL url = new URL(urlget);
            URLConnection conexion = url.openConnection();
            conexion.connect();
            InputStream in = conexion.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int leido;
            char[] buffer = new char[1000];
            while ((leido = br.read(buffer)) > 0) {
                res = res + new String(buffer, 0, leido);
            }
        }catch (java.net.ConnectException ex){
            res = "ERROR SERVER";
        }catch (Exception ex) {
            res = "ERROR";
            ex.printStackTrace();
        }
        return res;
    }
}
