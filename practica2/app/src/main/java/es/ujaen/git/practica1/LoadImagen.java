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
 * Created by windic on 28/12/2016.
 */

public class LoadImagen{
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
