package launchPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Serveur {

    private int numeroPort;
    private String nomServeur;
    private Socket socketServeur;
    private PrintStream socOut;
    private BufferedReader socIn;
    private String ordre;

    public Serveur(String unNomServeur, int unNumero) {
        numeroPort = unNumero;
        nomServeur = unNomServeur;
        ordre = "login";
    }

    public BufferedReader getSocIn() {
        return socIn;
    }
    public PrintStream getSocOut() {
        return socOut;
    }

    public boolean connectionAuServeur() {
        boolean ok = false;
        try {
            System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
            socketServeur = new Socket( nomServeur, numeroPort);
            socOut = new PrintStream(socketServeur.getOutputStream());
            socIn = new BufferedReader (
                    new InputStreamReader(socketServeur.getInputStream()));
            ok = true;
            System.out.println("Connexion faite");
        } catch (UnknownHostException e) {
            System.err.println("Serveur inconnu : " + e);
        } catch (ConnectException e) {
            System.err.println("Exception lors de la connexion:" + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Exception lors de l'echange de donnees:" + e);
        }
        return ok;
    }

    public void deconnectionDuServeur() {
        try {
            System.out.println("[ClientTCP] CLIENT : " + socketServeur);
            socOut.close();
            socIn.close();
            socketServeur.close();
        } catch (Exception e) {
            System.err.println("Exception lors de la deconnexion :  " + e);
        }
    }

    public void transmitioneOrdre(String ordre){
        this.ordre = ordre;
        try{
            socOut.println(this.ordre);
            socOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
