package es.ujaen.git.practica1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 *
 * Clase encargada de cargar las imagenes del servidor
 */

public class LoadImagen{
    /**
     * Método para crear un bitmap a partir de la url donde guardamos las imagenes.
     * @param URL dirección donde se ubican las imagenes.
     * @param options
     * @return devuelve un bitmap de la imagen descargada
     */
    public static Bitmap loadBitmap(String URL, BitmapFactory.Options options) {
        Bitmap bitmap = null;
        InputStream in;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
        }
        return bitmap;
    }

    /**
     * Método para realizar  la conexión con el servidor.
     * @param urlget url para establecer conexión al servidor.
     * @return
     */
    private static InputStream OpenHttpConnection(String urlget) {
        urlget = urlget.replace(" ", "%20");
        InputStream in = null;
        try {
            URL url = new URL(urlget);
            URLConnection conexion = url.openConnection();
            conexion.connect();
            in = conexion.getInputStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return in;
    }
}
