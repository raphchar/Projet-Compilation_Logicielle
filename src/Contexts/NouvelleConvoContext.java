package Contexts;

import Outils.Compte;
import Outils.Conversation;
/**
 * Context envoyé lors d'une requete de création d'une nouvelle conversation
 */
public class NouvelleConvoContext implements IContext{
    public String etat;
    public int protocole = 4;
    private String nameConvo;
    private String userRaw;
    private String message;
    private Compte compte;
    private Conversation conversation;

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public NouvelleConvoContext(String nameConvo, String users, String message, Compte compte) {
        this.nameConvo = nameConvo;
        this.userRaw = users;
        this.message = message;
        this.compte = compte;
    }

    public String getNameConvo() {
        return nameConvo;
    }

    public void setNameConvo(String nameConvo) {
        this.nameConvo = nameConvo;
    }

    public String getUserRaw() {
        return userRaw;
    }

    public void setUserRaw(String userRaw) {
        this.userRaw = userRaw;
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
