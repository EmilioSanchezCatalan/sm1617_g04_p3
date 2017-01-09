package es.ujaen.git.practica1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 *
 * Clase encargada de parsear la información codificada en Json
 */

public class JsonMenuParser {

    /**
     *
     * @param reader clase para leer un json
     * @return devuelve un objecto de la clase producto
     * @throws IOException
     */
    public Producto leerProducto(JsonReader reader) throws IOException{
        int cod_producto = 0;
        String nombre = null;
        String simagen = null;
        double precio = 0;
        String descripcion = null;



        reader.beginObject();
        while (reader.hasNext()){
            String name = reader.nextName();
            switch (name){
                case "cod_producto":
                    cod_producto = reader.nextInt();
                    break;
                case "nombre":
                    nombre = reader.nextString();
                    break;
                case "imagen":
                    simagen = reader.nextString();
                    break;
                case "precio":
                    precio = reader.nextDouble();
                    break;
                case "descripcion":
                    descripcion = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Producto(cod_producto,nombre,null,simagen,precio,descripcion);
    }

    /**
     *
     * @param reader clase para leer un json
     * @return devuelve un Arraylist de los productos leidos.
     * @throws IOException
     */
    public ArrayList leerArrayMenu(JsonReader reader) throws  IOException{
        ArrayList productos = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()){
            productos.add(leerProducto(reader));
        }
        reader.endArray();
        return productos;
    }

    /**
     *
     * @param in inputStream que almacena los datos que se van a parsear
     * @return devuelve un arraylist de la clase producto con los datos
     *         parseados.
     * @throws IOException
     */
    public ArrayList<Producto> readJsonStream(InputStream in) throws IOException{
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try{
            return leerArrayMenu(reader);
        }finally {
            reader.close();
        }
    }
}
