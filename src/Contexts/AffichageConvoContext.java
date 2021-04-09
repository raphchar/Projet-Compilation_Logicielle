package Contexts;

import Outils.Compte;
import Outils.Conversation;

import java.io.Serializable;

/**
 * Ce context est envoyé lors d'une reqête d'afficge d'une conversation en particulier
 */
public class AffichageConvoContext implements IContext, Serializable {
    private String etat;
    public int protocole = 3;
    private String nomConvo;
    private Compte compte;
    private Conversation conversation;

    public AffichageConvoContext(String nomConvo, Compte compte) {
        this.nomConvo = nomConvo;
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "AffichConvo{" +
                "nomConvo='" + nomConvo + '\'' +
                '}';
    }

    public Compte getCompte() {
        return compte;
    }


    public String getNomConvo() {
        return nomConvo;
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
