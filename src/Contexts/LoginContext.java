package Contexts;

public class LoginContext implements IContext {
    private String id = "";
    private String pw = "";

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

    @Override
    public String toString() {
        return "L{" + "id='" + id + '\'' + ", pw='" + pw + '\'' + '}';
    }
}

