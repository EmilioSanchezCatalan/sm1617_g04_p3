package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AuthQRFragment extends Fragment implements Service {

    //Definición de variables
    private ImageButton mButton;
    private ParseQR parseQR;

    public AuthQRFragment() {

    }

    /**
     * Metodo ejecutado la primera vez que dibuja la interfaz. Se encarga de controlar controlar el boton que llama al escaner QR
     * o abrir GooglePlay en caso de que no se tenga instalado el escaner QR.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return devuelve la vista del fragmento
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmento = inflater.inflate(R.layout.fragment_auth_qr, container, false);
        mButton = (ImageButton) fragmento.findViewById(R.id.autenticacionQR_scan_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO instalador de la apk en caso de que no este.
                try {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");

                    intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, 0);
                } catch (Exception ex) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android"));
                    startActivity(intent);
                }
            }
        });
        return fragmento;
    }

    /**
     * Metodo encargado de evaluar el resultado obtenido de el escaner. En caso de que se escanee un codigo QR invalido o
     * no se encanee ninguno devolvera el error como un Toast y en caso de que el código QR sea valido se ejecutará una tarea
     * Asyncrona.
     *
     * @param requestCode respuesta del código escaneado
     * @param resultCode  resultado obtenido
     * @param data        intent con los datos de la aplicación QR
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == MainActivity.RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                parseQR = new ParseQR(contents);
                if (parseQR.parseado() == true) {
                    Autenticator auth = new Autenticator();
                    auth.execute(parseQR.getCod_mesa(), parseQR.getNum_session());
                } else {
                    Toast.makeText(getActivity(), R.string.fail_qr, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), R.string.no_qr, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Clase Asyncrona que se encarga de autenticarse con el servidor.
     */
    private class Autenticator extends AsyncTask<String, Void, String> implements Cliente {
        /**
         * Ejecución de la autenticación
         *
         * @param params parametros recibidos del codigo QR (cod_mesa y num_session)
         * @return el resultado de la autenticación
         */
        @Override
        protected String doInBackground(String... params) {
            return autentificacion(params[0], params[1]);
        }

        /**
         * Ejecución tras la autenticación. En caso de ser correcta se almacenan el Session-ID y el tiempo
         * de expiración en las preferencias compartidas y se abre la vista clientes.
         *
         * @param s resultado de la autenticación recibido del método doInBackground.
         */
        @Override
        protected void onPostExecute(String s) {
            ParseServerResponses serverResponses = new ParseServerResponses(s);
            if (serverResponses.autentica() == true) {
                SharedPreferences pref = getActivity().getSharedPreferences(sharedpreferences, 0);
                SharedPreferences.Editor sharedEditor = pref.edit();
                sharedEditor.putString(lresp[0], serverResponses.getSessionID());
                sharedEditor.putString(lresp[1], serverResponses.getExpires());
                sharedEditor.commit();
                Intent intent = new Intent(getActivity(), VistaClientes.class);
                startActivity(intent);
            } else if (s.contains("SERVER")){
                Toast.makeText(getActivity(), R.string.fail_server, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(), R.string.fail_auth, Toast.LENGTH_SHORT).show();
            }

        }

        /**
         * Método encargado de realizar la autenticación con el servidor.
         *
         * @param cod_mesa    número de la mesa del comensal
         * @param num_session número de sesión, es un número aleatorio almacenado en la base de datos
         *                    que solo conoce el cliente y el servidor
         * @return devuelve la respuesta del servidor.
         */
        @Override
        public String autentificacion(String cod_mesa, String num_session) {
            Datos datos = new Datos(0, cod_mesa, num_session);
            Mensaje mensaje = new Mensaje(0, datos);
            ServiceRest rest = new ServiceRest(mensaje.toString());
            return rest.reqGet();

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

        @Override
        public String verificacion(String sesion_id, String expires) {
            return null;
        }
    }
}
