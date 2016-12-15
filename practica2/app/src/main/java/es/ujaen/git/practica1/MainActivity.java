package es.ujaen.git.practica1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 2.0
 */
public class MainActivity extends AppCompatActivity implements Service {

    TabLayout tabLayout;
    ViewPager viewPager;

    /**
     * Metodo encargado de crear la Activity principal. Donde se vincula la activity al layout principal
     * y realiza la transacción con los fragmentos Authfragment  y AuthQRFragment.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.activity_main_pager_viewpager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AuthQRFragment(), getString(R.string.menu_0));
        adapter.addFragment(new AuthFragment(), getString(R.string.menu_1));
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.activity_main_tabs_tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    /*
     * Método encargado de comprobar si ya se ha iniciado sesión anteriormente y en caso de ser así
     * comprobar si la autenticacíon siga siendo válida.
     */
    @Override
    protected void onResume() {
        super.onResume();
        String sesion_id, expires;
        tabLayout.setVisibility(View.INVISIBLE);
        viewPager.setVisibility(View.INVISIBLE);
        SharedPreferences preferences = getSharedPreferences(sharedpreferences, 0);
        sesion_id = preferences.getString(lresp[0], null);
        expires = preferences.getString(lresp[1], null);
        if (sesion_id != null && expires != null) {
            Verificacion verf = new Verificacion();
            verf.execute(sesion_id, expires);

        } else {
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Clase Asyncrona que se encarga de verificar la sesión con el servidor.
     */
    private class Verificacion extends AsyncTask<String, Void, String> implements Cliente {
        /**
         * Ejecución de la verificación
         *
         * @param params parametros almacenados (Session-ID y Expires)
         * @return el resultado del servidor
         */
        @Override
        protected String doInBackground(String... params) {
            return verificacion(params[0], params[1]);
        }

        /**
         * Método ejecutado tras recibir la respuesta del servidor. En caso de ser correcta la verificación
         * se iniciará el la vista clientes en caso de ser incorrecta se permanecerá en la activity actual.
         *
         * @param s respuesta del servidor recibido del metodo doInBackground.
         */
        @Override
        protected void onPostExecute(String s) {
            ParseServerResponses serverResponses = new ParseServerResponses(s);
            if (serverResponses.verify() == true) {
                Intent intent = new Intent(MainActivity.this, VistaClientes.class);
                startActivity(intent);
            } else {
                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public String autentificacion(String cod_mensaje, String num_session) {
            return null;
        }

        @Override
        public String listar() {
            return null;
        }

        @Override
        public String pedido(String cod_pedido, String cantidad) {
            return null;
        }

        @Override
        public String cerrar_session(String cod_mensaje, String num_session) {
            return null;
        }

        /**
         * Metodo encargado de realizar la verificación de sesión con el servidor.
         * @param sesion_id cod_mesa codificado en base64 con el num_session.
         * @param expires tiempo de expiracion de la sesión.
         * @return devuelve la respuesta del servidor.
         */
        @Override
        public String verificacion(String sesion_id, String expires) {
            Datos datos = new Datos(4, sesion_id, expires);
            Mensaje mensaje = new Mensaje(4, datos);
            ServiceRest rest = new ServiceRest(mensaje.toString());
            return rest.reqGet();
        }
    }
}


