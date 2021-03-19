package servPattern;
import java.io.IOException;
import java.net.Socket;

/**
 * Processus de Transaction (anciennement ServeurSpecifique)
 */
class TraitementContext extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;

	public TraitementContext(Socket uneSocket, ServeurTCP unServeur) {
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("[Traitement Context] CLIENT : " + clientSocket);
		monServeurTCP = unServeur;
	} 

	public void run() {        
		try {
			monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream() );
			System.out.println("[Traitement Context] Traitement Context fait");
		} catch (IOException e) {
			System.err.println("[Traitement Context] Exception : " + e );
			e.printStackTrace();
		}
	} 
}
