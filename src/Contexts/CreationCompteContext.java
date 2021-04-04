package Contexts;

public class CreationCompteContext implements IContext {
    private String id = "";
    private String pw = "";
    private String verifPw = "";

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

    public String getVerifPw() {
        return verifPw;
    }

    public void setVerifPw(String verifPw) {
        this.verifPw = verifPw;
    }

    @Override
    public String toString() {
        return "CreationCompteContext{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", verifPw='" + verifPw + '\'' +
                '}';
    }

    public CreationCompteContext(String id, String pw, String verifPw) {
        this.id = id;
        this.pw = pw;
        this.verifPw = verifPw;
    }

}
