package Protocoles;


import servPattern.IContext;
import servPattern.IProtocole;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ProtocoleLoginClient implements IProtocole {

    private Tools Ids = new Tools();
    private PrintStream socOut;
    private BufferedReader socIn;
    private Socket clientSocket;
    private int connexion = 0;
    private int numeroPort;

    public ProtocoleLoginClient(int unNumeroPort) {
        numeroPort = unNumeroPort;
    }

    public void execute(IContext c , InputStream unInput , OutputStream unOutput ) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(numeroPort);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + numeroPort + ", " + e);
            System.exit(1);
        }
        int maxConnexions = 1;
        while (connexion < maxConnexions) {
            try {
                System.out.println("Attente du serveur pour la communication d'un client " );
                clientSocket = serverSocket.accept();
                connexion ++;
                System.out.println("Connexion faite" );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("Création socket ");
            socOut = new PrintStream(clientSocket.getOutputStream());
            socIn = new BufferedReader (
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Essaie nom + mdp" );
            String verifs;
            boolean verif;
            String nom;
            String mdp;

            nom = socIn.readLine();
            System.out.println("nom + mdp reçu");
            mdp = socIn.readLine();
            System.out.println(nom + " " + mdp);
            verif = Ids.checkMDP(nom, mdp);

            verifs = Boolean.toString(verif);
            System.out.println(verifs);

            socOut.println( verifs );
            socOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            serverSocket.close();
            connexion --;
        } catch (IOException e) {
            System.out.println("Could not close");
        }
    }
}
