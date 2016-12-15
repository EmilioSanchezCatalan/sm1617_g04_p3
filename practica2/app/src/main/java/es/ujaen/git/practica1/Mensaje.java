package es.ujaen.git.practica1;
/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

/**
 *  Clase que proporciona una estuctura a los mensajes para que sean correctamente enviados por el metodo Get.
 */
public class Mensaje implements Service {


    protected int tipo;
    protected String header = "";
    protected Datos datos;

    /**
     *
     * @param tipo tipo de mensaje:
     *             0:Autentificación
     *             1:Listar
     *             2:Pedido
     *             3:cerrar_session
     *             4:verificacion
     * @param datos parametros del mensaje
     */
    public Mensaje(int tipo, Datos datos){
        this.tipo = tipo;
        header = headers[tipo];
        this.datos = datos;
    }

    /**
     *
     * @return devuelve la cadena de caracteres ya formateada para ser enviada al servidor
     */
    @Override
    public String toString() {
        return header + "?" + datos.toString();
    }
}
