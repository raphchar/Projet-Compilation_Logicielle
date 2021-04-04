package servPattern;
import Contexts.IContext;
import Protocoles.IProtocole;

import java.io.*;
import java.net.*;


public class ServeurTCP extends Thread{

	private static int nbConnexions = 0;
	
	/** Maximum de connexions client autorisées */
	private int maxConnexions;
	
	private Socket clientSocket;

	private IContext contexte;
	
	private IProtocole protocole;
	
	private int numeroPort;

	public ServeurTCP(int unNumeroPort) {        
		numeroPort = unNumeroPort;
		maxConnexions = 10;
	} 

	public ServeurTCP(IContext context, IProtocole protocol, int port) {
		this(port);
		contexte = context;
		this.protocole = protocol;
	}

	public String toString() {        
		return "[ServeurTCP] Port : " +  numeroPort + ", Contexte: " + contexte ;
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
			TraitementContext st = new TraitementContext( clientSocket , this );
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
	

	public IProtocole getProtocole() {
		return protocole;
	}

	public IContext getContexte() {
		return contexte;
	}
		

}
