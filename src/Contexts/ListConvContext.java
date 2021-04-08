package Contexts;

import Outils.Conversation;

import java.io.Serializable;
import java.util.ArrayList;

public class ListConvContext implements IContext, Serializable {
    private ArrayList<Conversation> conversations;
    public final int protocole = 4;
    public String etat;

    public ArrayList<Conversation> getConversations() {return conversations; }
    public ArrayList<Conversation> setConversations(ArrayList<Conversation> convos) {this.conversations = convos}

    @Override
    public int getProtocole() {
        return this.protocole;
    }

    @Override
    public String getEtat() {
        return etat;
    }

    @Override
    public void setEtat(String etat) {
        this.etat = etat;
    }
}
