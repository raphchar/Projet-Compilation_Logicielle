package Outils;

import java.util.ArrayList;

public class Compte {
    public String userID;
    private ArrayList<Conversation> listConvos;

    public Compte(String userID, ArrayList<Conversation> listConvos) {
        this.userID = userID;
        this.listConvos = listConvos;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<Conversation> getListConvos() {
        return listConvos;
    }

    public void setListConvos(ArrayList<Conversation> listConvos) {
        this.listConvos = listConvos;
    }
}
