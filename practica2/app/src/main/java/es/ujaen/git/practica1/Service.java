package es.ujaen.git.practica1;
/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */
/**
 * Interfaz en la que definimos todos los parámetros necesarios para usar en otras clases.
 */
public interface Service {

    public static final String servidor = "192.168.43.104";
    public static final int port = 8080;
    public static final String servicio = "Servidor_g05";
    public static final String headers[] = {"autentica.php", "listar.php", "pedido.php", "cierre_session.php", "verify.php"};
    public static final String params[][] = {{"cod_mesa", "num_session"}, {}, {"cod_productos", "cantidad"},
            {"cod_mesa", "num_session"}, {"SESION-ID", "EXPIRES"}};

    public static final String ERROR = "ER";
    public static final String OK = "OK";
    public static final String[] lresp = {"SESION-ID", "EXPIRES"};

    public static final String sharedpreferences = "MiPref";
}
