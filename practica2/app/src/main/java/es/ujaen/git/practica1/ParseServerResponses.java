package es.ujaen.git.practica1;
/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */
import java.util.HashMap;
import java.util.Map;

public class ParseServerResponses implements Service {
    //Definimos las variables
    private String resp;
    final private Map<String, String> responses = new HashMap<>();

    /**
     * Constructor de la clase
     * @param resp
     */
    public ParseServerResponses(String resp) {
        this.resp = resp;
    }

    /**
     * Metodo diseñado para parsear la respuesta del servidor sobre la autenticación
     * y guardar los valores de session id y tiempo de expiración
     * @return si resp=OK (autenticación correcta) deolvemos true, en cualquier otro caso false
     */
    public boolean autentica() {
        if (resp.substring(0, 2).equalsIgnoreCase(OK)) {
            String clave_valor[];
            resp = resp.replace("\r\n", "");
            clave_valor = resp.substring(resp.indexOf(" ") + 1, resp.length()).split("&");
            responses.put(clave_valor[0].split("::")[0], clave_valor[0].split("::")[1]);
            responses.put(clave_valor[1].split("::")[0], clave_valor[1].split("::")[1]);
            return true;
        } else if (resp.substring(0, 2).equalsIgnoreCase(ERROR)) {
            return false;
        }
        return false;
    }

    /**
     * Comprobamos la respuesta del servidor.
     * @return en caso de que sea OK, devolvemos true. En cualquier otro caso, devolvemos false.
     */
    public boolean verify() {
        if (resp.substring(0, 2).equalsIgnoreCase(OK)) {
            return true;
        } else if (resp.substring(0, 2).equalsIgnoreCase(ERROR)) {
            return false;
        }
        return false;
    }

    /**
     * Metodo que comprueba si el session id está en el map
     * @return puede devolver el session id o null
     */
    public String getSessionID() {
        if (responses.containsKey(lresp[0])) {
            return responses.get(lresp[0]);
        } else return null;
    }

    /**
     * Metodo que comprueba si el tiempo de expiración está en el map
     * @return puede devolver el tiempo de expiración o null
     */
    public String getExpires() {
        if (responses.containsKey(lresp[1])) {
            return responses.get(lresp[1]);
        } else return null;
    }
}
