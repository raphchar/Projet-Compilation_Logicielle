package Contexts;

public class CreationCompteContext implements IContext {
    private String id = "";
    private String pw = "";

    public CreationCompteContext(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "C{" + "id='" + id + '\'' + ", pw='" + pw + '\'' + '}';
    }
}
