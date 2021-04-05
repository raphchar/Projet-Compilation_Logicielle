package Contexts;

import java.io.Serializable;

public class MessagePriveContext implements IContext, Serializable {
    private String message;
    private String sender = "";
    private String receiver = "";
    public final int protocole = 3;
    public String etat;


    public MessagePriveContext(String message, String sender, String receiver) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "P{" +
                "message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
