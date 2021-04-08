package Contexts;

import Outils.Conversation;

public class NouvelleConvoContext implements IContext{
    public String etat;
    public int protocole = 4;
    private String nameConvo;
    private String[] users;
    private String message;
    private Conversation conversation;


    public NouvelleConvoContext(String nameConvo, String[] users, String message) {
        this.nameConvo = nameConvo;
        this.users = users;
        this.message = message;
    }

    public String getNameConvo() {
        return nameConvo;
    }

    public void setNameConvo(String nameConvo) {
        this.nameConvo = nameConvo;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public int getProtocole() {
        return protocole;
    }

    @Override
    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String getEtat() {
        return etat;
    }
}
