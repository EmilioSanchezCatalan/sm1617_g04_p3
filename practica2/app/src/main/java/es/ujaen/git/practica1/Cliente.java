package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

/**
 * Intefaz Cliente el cual posee todas las acciones que una aplicación cliente puede realizar
 * respecto al servidor.
 */
public interface Cliente {
    /**
     * Metodo que se encarga de realizar la autenticación con el servidor
     *
     * @param cod_mesa    número de la mesa del comensal
     * @param num_session número de sesión, es un número aleatorio almacenado en la base de datos
     *                    que solo conoce el cliente y el servidor
     * @return devuleve el código (OK || ER) en caso delcódigo afirmativo irá seguido de un session-id
     *         y un tiempo de expiración.
     */
    public String autentificacion(String cod_mesa, String num_session);

    /**
     * Método encargado de pedir el menú al servidor.
     * @return devuleve un JSON con el menú de los productos.
     */
    public String listar();

    /**
     * Método encargado de realizar una comanda.
     * @param cod_pedido número del producto.
     * @param cantidad  cantidad del producto.
     * @return devuelve un código de asetimiento positivo (OK) o negativo (ER)
     */
    public String pedido(String cod_pedido, String cantidad);

    /**
     * Método encargado de cerrar la sesión
     * @param cod_mensa número de la mesa del comensal
     * @param num_session número de sesión, es un número aleatorio almacenado en la base de datos
     *                    que solo conoce el cliente y el servidor
     * @return devuelve un código de asetimiento positivo (OK) o negativo (ER)
     */
    public String  cerrar_session(String cod_mensa, String num_session);

    /**
     * Método encargado de comprobar si ha caducado la sesión del cliente.
     * @param sesion_id cod_mesa codificado en base64 con el num_session.
     * @param expires tiempo de expiracion de la sesión.
     * @return  devuelve un codigo de asentimiento positivo(OK) o negativo (ER)
     */
    public String verificacion(String sesion_id, String expires);
}
