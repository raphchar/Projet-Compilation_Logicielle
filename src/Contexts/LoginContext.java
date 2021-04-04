package Contexts;

import java.io.Serializable;

public class LoginContext implements IContext, Serializable {
    private String id = "";
    private String pw = "";
    public final int protocole = 1;

    @Override
    public String toString() {
        return "L{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }

    @Override
    public int getProtocole() {
        return this.protocole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public LoginContext(String login, String pass) {
        this.id = login;
        this.pw = pass;
    }

}

