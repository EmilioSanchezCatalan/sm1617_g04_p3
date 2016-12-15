package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara.
 * @version 1.0
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AuthFragment extends Fragment {

    // Definición de variables e inicialización
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    private String mUser = "";
    private String mPass = "";
    private String mIp = "";
    private String mPort = "";

    private EditText mEditUser = null;
    private EditText mEditPass = null;
    private EditText mEditPort = null;
    private EditText mEditIp = null;
    private Button mButtonEnviar = null;

    private Authentication mAutentica = new Authentication("", "", "", 0);

    public AuthFragment() {

    }

    /**
     * Metodo que permiter instaciar el fragmento con unos parámetros iniciales.
     *
     * @param user nombre de usuario.
     * @param pass contraseña.
     * @param ip   dirección ip.
     * @param port puerto.
     * @return devuelve el fragment AuthFragment.
     */
    public static AuthFragment newInstance(String user, String pass, String ip, int port) {
        AuthFragment fragment = new AuthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, pass);
        args.putString(ARG_PARAM3, ip);
        args.putInt(ARG_PARAM4, port);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Método de creación del fragmento en el que se recuperan los valores
     * guardados en los argumentos.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            if (getArguments() != null) {
                mUser = getArguments().getString(ARG_PARAM1);
                mPass = getArguments().getString(ARG_PARAM2);
                mIp = getArguments().getString(ARG_PARAM3);
                mPort = getArguments().getString(ARG_PARAM4);
                mAutentica.setUser(mUser);
                mAutentica.setPass(mPass);
                mAutentica.setIP(mIp);
                try {
                    mAutentica.setPort(Integer.parseInt(mPort));
                } catch (NumberFormatException ex) {
                    mAutentica.setPort(0);
                }
            }
    }

    /**
     * Metodo ejecutado la primera vez que se dibuja la interfaz. Se encarga de controlar el envento del boton envía y hacer un
     * intent a la actividad ServicioRemotoActivity enviando los datos.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return devuelve la vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mAutentica.setUser(savedInstanceState.getString(ARG_PARAM1));
            mAutentica.setPass(savedInstanceState.getString(ARG_PARAM2));
            mAutentica.setIP(savedInstanceState.getString(ARG_PARAM3));
            mAutentica.setPort(Integer.parseInt(savedInstanceState.getString(ARG_PARAM4)));
        }
        View fragmento = inflater.inflate(R.layout.frag_authentication, container, false);

        reescribir(fragmento);

        mButtonEnviar = (Button) fragmento.findViewById(R.id.autenticacion_envia_boton);
        mButtonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.develop, Toast.LENGTH_SHORT).show();

            }
        });
        return fragmento;
    }

    /**
     * Obtiene los valores de los EditText y los almacena en un objeto de la clase Autenticación.
     */
    private void extraer() {
        mAutentica.setUser(mEditUser.getText().toString());
        mAutentica.setPass(mEditPass.getText().toString());
        mAutentica.setIP(mEditIp.getText().toString());
        mAutentica.setPort(Integer.parseInt(mEditPort.getText().toString()));
    }

    /**
     * Método que se encarga de almacenar los datos y volver a pintarlos en la interfaz cada vez
     * que se produzca un cambio de estado.
     *
     * @param container vista contener (en este caso el Fragmento).
     */
    private void reescribir(View container) {

        mEditUser = (EditText) container.findViewById(R.id.autenticacion_user_edittext);
        mEditPass = (EditText) container.findViewById(R.id.autenticacion_pass_edittext);
        mEditPort = (EditText) container.findViewById(R.id.autenticacion_port_edittext);
        mEditIp = (EditText) container.findViewById(R.id.autenticacion_ip_edittext);

        mEditUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAutentica.setUser(mEditUser.getText().toString());
            }
        });

        mEditPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAutentica.setPass(mEditPass.getText().toString());
            }
        });

        mEditIp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAutentica.setIP(mEditIp.getText().toString());
            }
        });

        mEditPort.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    mAutentica.setPort(Integer.parseInt(mEditPort.getText().toString()));
                } catch (NumberFormatException ex) {
                    mAutentica.setPort(0);
                }
            }
        });

        mEditUser.setText(mAutentica.getUser());
        mEditPass.setText(mAutentica.getPass());
        mEditIp.setText(mAutentica.getIP());
        mEditPort.setText(Integer.toString(mAutentica.getPort()));
    }

    /**
     * Método Encargado de guardar los parametros del usuario para conservarlos a pesar de
     * que produzcan cambios de estado.
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ARG_PARAM1, mAutentica.getUser());
        outState.putString(ARG_PARAM2, mAutentica.getPass());
        outState.putString(ARG_PARAM3, mAutentica.getIP());
        outState.putString(ARG_PARAM4, Integer.toString(mAutentica.getPort()));
    }
}
