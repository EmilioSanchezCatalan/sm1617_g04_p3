package es.ujaen.git.practica1;

import android.graphics.Bitmap;

/**
 * Created by windic on 27/12/2016.
 */

public class Producto {
    int cod_producto;
    String nombre;
    Bitmap imagen;
    String simagen;
    double precio;
    String descripcion;

    public Producto(int cod_producto, String nombre, Bitmap imagen, String simagen, double precio, String descripcion) {
        this.cod_producto = cod_producto;
        this.nombre = nombre;
        this.imagen = imagen;
        this.simagen = simagen;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getSimagen(){return simagen; }

    public void setSimagen(){this.simagen = simagen; }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
