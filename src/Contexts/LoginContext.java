package Contexts;

public class LoginContext implements IContext {
    private String id = "";
    private String pw = "";


    public LoginContext(String login, String pass) {
        this.id = login;
        this.pw = pass;
    }

    @Override
    public String toString() {
        return "L{" + "id='" + id + '\'' + ", pw='" + pw + '\'' + '}';
    }
}

