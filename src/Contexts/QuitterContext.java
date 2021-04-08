package Contexts;

import java.io.Serializable;

public class QuitterContext implements IContext, Serializable {
    public final int protole = 10;
    public  String etat;

    @Override
    public String toString() {
        return "Q{" + '}';
    }

    @Override
    public int getProtocole() {
        return protole;
    }

    @Override
    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String getEtat() {
        return this.etat;
    }
}
