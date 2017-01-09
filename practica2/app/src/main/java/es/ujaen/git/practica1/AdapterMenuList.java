package es.ujaen.git.practica1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 * Clase que permite adaptar y representar los datos en un listView
 */

public class AdapterMenuList extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Producto> items;

    /**
     *
     * @param activity activity en el que mostrar el listView
     * @param items datos a representar
     */
    public AdapterMenuList (Activity activity, ArrayList<Producto> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Producto> producto) {
        for (int i = 0; i < producto.size(); i++) {
            items.add(producto.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Método encargado de devolver la vista con los respectivos valores
     * asociados al listView.
     *
     * @param position poscicion en el array
     * @param convertView vista a modificar
     * @param parent
     * @return devuelve la vista modifcada
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_menu, null);
        }

        Producto dir = items.get(position);

        TextView nombre = (TextView) v.findViewById(R.id.item_menu_nombre_textview);
        nombre.setText(dir.getNombre());

        TextView description = (TextView) v.findViewById(R.id.item_menu_descripcion_textview);
        description.setText(dir.getDescripcion());

        TextView precio = (TextView) v.findViewById(R.id.item_menu_precio_textview);
        precio.setText(Double.toString(dir.getPrecio()));

        ImageView imagen = (ImageView) v.findViewById(R.id.item_menu_imagen_imageview);
        imagen.setImageBitmap(dir.getImagen());
        return v;
    }
}
