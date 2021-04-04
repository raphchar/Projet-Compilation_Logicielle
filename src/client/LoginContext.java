package client;

import servPattern.IContext;

public class LoginContext implements IContext {
    private String id = "";
    private String pw = "";


    public LoginContext(String login, String pass) {
        setId(login);
        setPw(pass);
    }

    // A modifier : choisir le moyen de transmission des données
    @Override
    public String toString() {
        return "L{" + "id='" + id + '\'' + ", pw='" + pw + '\'' + '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }


    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}

