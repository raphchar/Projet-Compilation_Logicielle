package servPattern;
import Protocoles.ProtocoleDemarrage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe qui instancie le serveur pour les communications techniques TCP avant de transmettre une session de comm
 * client/serveur à un traitmentContext une fois une nouvelle connxion reçue
 */
public class ServeurTCP extends Thread{

	private static int nbConnexions = 0;
	/** Maximum de connexions client autorisées */
	private int maxConnexions;
	private Socket clientSocket;
	private int numeroPort;

	/* initialisation du serveur */
	ProtocoleDemarrage protocoleDemarrage = new ProtocoleDemarrage();

	public ServeurTCP(int unNumeroPort) {        
		numeroPort = unNumeroPort;
		maxConnexions = 10;
	}

	public String toString() {        
		return "[ServeurTCP] Port : " +  numeroPort ;
	}


	public ProtocoleDemarrage getProtocoleDemarrage() {
		return protocoleDemarrage;
	}

	/* l'ancienne methode go est remplacee par run */
	public void run() {        
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket ( numeroPort );
		} catch (IOException e) {
			System.out.println("[ServeurTCP] Could not listen on port: " + numeroPort + ", " + e);
			System.exit(1);
		}

		/* On autorise maxConnexions traitements*/
		while (nbConnexions <= maxConnexions) {
			try {
				System.out.println("[ServeurTCP] Attente du serveur pour la communication d'un client " );
				clientSocket = serverSocket.accept();
				nbConnexions ++;
				System.out.println("[ServeurTCP] Nb automates : " + nbConnexions);
			} catch (IOException e) {
				System.out.println("[ServeurTCP] Accept failed: " + serverSocket.getLocalPort() + ", " + e);
				System.exit(1);
			}
			TraitementContext st = new TraitementContext(clientSocket);
			st.start();
		}
		System.out.println("[ServeurTCP] Deja " + nbConnexions + " clients. Maximum autorisé atteint");

		try {
			serverSocket.close();
			nbConnexions --;
		} catch (IOException e) {
			System.out.println("[ServeurTCP] Could not close");
		}

	}
}
