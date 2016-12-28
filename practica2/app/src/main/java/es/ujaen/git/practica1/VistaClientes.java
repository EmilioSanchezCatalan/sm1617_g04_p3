package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
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

    ImageButton cerrar_sesion;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_clientes);
        listView = (ListView)findViewById(R.id.vistaclientes_listamenu_listview);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                ArrayList<Producto> productos = new ArrayList();
                Bundle datos = msg.getData();
                Toast.makeText(getApplicationContext(), datos.getString("key"), Toast.LENGTH_SHORT).show();
                InputStream stream = new ByteArrayInputStream(datos.getString("key").getBytes(StandardCharsets.UTF_8));
                JsonMenuParser parser = new JsonMenuParser();
                try{
                    productos = parser.readJsonStream(stream);
                }catch (IOException ex){

                }
                AdapterMenuList adapter =  new AdapterMenuList(VistaClientes.this, productos);
                listView.setAdapter(adapter);
            }
        };
        new Thread(new Listar(handler)).start();
        cerrar_sesion = (ImageButton) findViewById(R.id.vistaclientes_cerrarsesion_button);
        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences(sharedpreferences, 0);
                SharedPreferences.Editor editorPref = pref.edit();
                editorPref.putString(lresp[0], null);
                editorPref.putString(lresp[1], null);
                editorPref.commit();
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }
}
