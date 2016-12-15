package es.ujaen.git.practica1;
/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ParseQR implements Service {
    //Definición de variables
    private URL qr = null;
    private ArrayList<String> valores = new ArrayList<>();

    /**
     * Constructor de la clase. Aquí pasamos la url a acceder (contenido) a nuestra variable (qr) y comprobamos
     * que el formato url sea correcto.
     * @param contenido
     */
    public ParseQR(String contenido) {
        try {
            qr = new URL(contenido);
        } catch (MalformedURLException ex) {
        }
    }

    /**
     * En esta función queremos coger el Path de la url, por eso usamos getPath(). Los indexof los utizamos para
     * coger el trozo exacto que queremos.
     * @return
     */
    private String parseoServicio() {
        return qr.getPath().substring(qr.getPath().indexOf("/") + 1, qr.getPath().lastIndexOf("/"));
    }

    /**
     * Función igual que la anterior (parseoServicio). El objetivo de esta función es coger la cabecera.
     * @return
     */
    private String parseoHeader() {
        return qr.getFile().substring(qr.getFile().lastIndexOf("/") + 1, qr.getFile().lastIndexOf("?"));
    }

    /**
     * En esta función obtenemos los parametros de la url. Los almacenamos en el array de Strings parse_param y comprobamos que solo hay 2.
     * A continuación comprobamos que los parámetros que tenemos en la url, son los que verdaderamente tenemos almacenados.
     * En el caso de que así sea, guardamos los valores de estos parámetros en el arraylist que definimos al inicio de la clase (valores).
     * @return
     */
    private boolean parseoParams() {
        String str_params = qr.getFile().substring(qr.getFile().lastIndexOf("?") + 1, qr.getFile().length());
        String[] parse_param = str_params.split("&");
        if (parse_param.length == 2) {
            for (int i = 0; i < parse_param.length; i++) {
                String[] param_valor = parse_param[i].split("=");
                if (param_valor[0].equals(params[0][i])) {
                    valores.add(param_valor[1]);
                }
            }
        }
        return valores.size() == 2;
    }

    /**
     * En esta función comprobamos que el formato de la url se corresponda
     * con el correcto para acceder al servidor.
     * En el caso de que así sea se devolverá true, en cualquier otro caso false.
     * @return
     */
    public boolean parseado() {
        try {
            if (qr != null) {
                if (qr.getProtocol().equals("http") && qr.getHost().equals(servidor) && qr.getPort() == port && parseoServicio().equals(servicio)
                        && parseoHeader().equals(headers[0]) && parseoParams()) {
                    return true;
                } else {
                    return false;
                }
            }else return false;
        }catch (Exception ex){
            return false;
        }
    }

    /**
     * Devolvemos el valor de codigo de mesa almacenado en el arraylist valores.
     * @return
     */
    public String getCod_mesa() {
        return valores.get(0);
    }

    /**
     * Devolvemos el valor de numero de sesión almacenado en el arraylist valores.
     * @return
     */
    public String getNum_session() {
        return valores.get(1);
    }
}
