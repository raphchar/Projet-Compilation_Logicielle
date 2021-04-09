package Contexts;

import Outils.Compte;
import Outils.Conversation;

import java.io.Serializable;

public class MessagePriveContext implements IContext, Serializable {
    private String message;
    private Conversation conversation;
    private Compte compte;
    public final int protocole = 5;
    public String etat;

    public MessagePriveContext(String message, Conversation conversation, Compte compte) {
        this.message = message;
        this.conversation = conversation;
        this.compte = compte;
    }


    public String getMessage() {
        return message;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Compte getCompte() {
        return compte;
    }

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
