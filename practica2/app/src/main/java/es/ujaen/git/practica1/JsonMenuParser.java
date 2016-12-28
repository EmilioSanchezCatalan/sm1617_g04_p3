package es.ujaen.git.practica1;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by windic on 27/12/2016.
 */

public class JsonMenuParser {

    public Producto leerProducto(JsonReader reader) throws IOException{
        int cod_producto = 0;
        String nombre = null;
        String imagen = null;
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
                    imagen = reader.nextString();
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
        return new Producto(cod_producto,nombre,imagen,precio,descripcion);
    }
    public ArrayList leerArrayMenu(JsonReader reader) throws  IOException{
        ArrayList productos = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()){
            productos.add(leerProducto(reader));
        }
        reader.endArray();
        return productos;
    }
    public ArrayList<Producto> readJsonStream(InputStream in) throws IOException{
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try{
            return leerArrayMenu(reader);
        }finally {
            reader.close();
        }
    }
}
