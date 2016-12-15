package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * Clase que proporciona una estructura a los datos que van a ser enviado mediante el metodo Get.
 */
public class Datos implements Service {

    protected ArrayList<String> values = new ArrayList<>();
    protected String[] param;

    /**
     * Constructor de la clase
     *
     * @param tipo    tipo de parametros:
     *                0:Autentificación
     *                1:Listar
     *                2:Pedido
     *                3:cerrar_session
     * @param Valores los valores asociados a cada parametro en el orden correcto para:
     *                tipo = 0: 2 valores.
     *                tipo = 1: null.
     *                tipo = 2: 2 valores.
     *                tipo = 3: 2 valores.
     *                tipo = 4: 2 valores.
     */
    public Datos(int tipo, String... Valores) {
        param = params[tipo];
        if (Valores != null) {
            for (int i = 0; i < param.length; i++) {
                values.add(Valores[i]);
            }
        }
    }

    /**
     * @return devuelve la cadena de caracteres ya formateada para enviar parametros con formato de una peticion Get.
     */
    @Override
    public String toString() {
        String allparams = "";
        if (param.length != 0) {
            for (int i = 0; i < param.length; i++) {
                allparams = allparams + param[i] + "=" + values.get(i);
                if (i != param.length - 1) {
                    allparams = allparams + "&";
                }
            }
        }
        return allparams;
    }
}
