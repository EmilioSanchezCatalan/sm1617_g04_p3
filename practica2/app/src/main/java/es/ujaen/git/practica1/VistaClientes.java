package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity mostrada tras la autenticaión correcta, incluye un botón de cerrar sesión el cual
 * vacía los valores de sesión almacenados.
 */
public class VistaClientes extends AppCompatActivity implements Service {

    ListView listView;
    ArrayList<Producto> productos = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_clientes);
        listView = (ListView) findViewById(R.id.vistaclientes_listamenu_listview);
        if (savedInstanceState == null) {
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Bundle datos = msg.getData();
                    InputStream stream = new ByteArrayInputStream(datos.getString("key").getBytes(StandardCharsets.UTF_8));
                    JsonMenuParser parser = new JsonMenuParser();
                    try {
                        productos = parser.readJsonStream(stream);
                    } catch (IOException ex) {

                    }
                    CargaImagen load = new CargaImagen();
                    load.execute();
                }
            };
            new Thread(new Listar(handler)).start();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.configuration:
                SharedPreferences pref = getSharedPreferences(sharedpreferences, 0);
                SharedPreferences.Editor editorPref = pref.edit();
                editorPref.putString(lresp[0], null);
                editorPref.putString(lresp[1], null);
                editorPref.commit();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("size", productos.size());
        for (int i = 0; i < productos.size(); i++) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            productos.get(i).getImagen().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            outState.putString("nombre" + i, productos.get(i).getNombre());
            outState.putByteArray("imagen" + i, byteArray);
            outState.putDouble("precio" + i, productos.get(i).getPrecio());
            outState.putString("descripcion" + i, productos.get(i).getDescripcion());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < savedInstanceState.getInt("size"); i++) {
            byte[] byteArray = savedInstanceState.getByteArray("imagen" + i);
            productos.get(i).setNombre(savedInstanceState.getString("nombre" + i));
            productos.get(i).setImagen(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
            productos.get(i).setPrecio(savedInstanceState.getDouble("precio"+i));
            productos.get(i).setDescripcion(savedInstanceState.getString("descripcion"+i));
        }
        AdapterMenuList adapter = new AdapterMenuList(VistaClientes.this, productos);
        listView.setAdapter(adapter);
    }

    class CargaImagen extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            BitmapFactory.Options bmOptions;
            bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
            for (int i = 0; i < productos.size(); i++) {
                productos.get(i).setImagen(LoadImagen.loadBitmap("http://" + servidor + ":" + port + "/" + servicio
                        + productos.get(i).getSimagen(), bmOptions));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void producto) {
            AdapterMenuList adapter = new AdapterMenuList(VistaClientes.this, productos);
            listView.setAdapter(adapter);
        }
    }
}
