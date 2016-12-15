package es.ujaen.git.practica1;

/**
 * @author Emilio Sánchez Catalán y Víctor Manuel Pérez Cámara
 * @version 1.0
 */

public class Authentication {
    protected String mUser;
    protected String mPass;
    protected String mIP;
    protected int mPort;

    /**
     * Constructor
     *
     * @param User
     * @param Pass
     * @param IP
     * @param Port
     */
    public Authentication(String User, String Pass, String IP, int Port) {
        mUser = User;
        mIP = IP;
        mPass = Pass;
        mPort = Port;

    }

    //Setters and Getters.

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

    public String getPass() {
        return mPass;
    }

    public void setPass(String Pass) {
        mPass = Pass;
    }

    public String getIP() {
        return mIP;
    }

    public void setIP(String IP) {
        mIP = IP;
    }

    public int getPort() {
        return mPort;
    }

    public void setPort(int Port) {
        mPort = Port;
    }
}
