package servPattern;

import Contexts.IContext;
import Protocoles.IProtocole;
import Protocoles.ProtocoleCreationCompte;
import Protocoles.ProtocoleLoginClient;
import Protocoles.ProtocoleMessagePrive;

import java.io.*;
import java.net.Socket;

/**
 * TraitementContext vient lire et écrire sur les socket du client, transmises par ServeurTCP
 * En interprétant la première lettre du message entrant, TraitementContext sait quel protocole il doit appeller
 */
class TraitementContext extends Thread {

	private final Socket clientSocket;
	private ServeurTCP monServeurTCP;

	public TraitementContext(Socket uneSocket, ServeurTCP unServeur) {
		super("ServeurThread");
		clientSocket = uneSocket;
		monServeurTCP = unServeur;

	}

	public void run() {
		int protocoleNumber = 0;
		IProtocole protocole = null;

		try {

			InputStream inputStream = clientSocket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

			OutputStream outputStream = clientSocket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

			String outPutRes;

			IContext context = (IContext) objectInputStream.readObject();
			protocoleNumber = context.getProtocole();

			System.out.println("[Traitement Conext] protocol Number : " + protocoleNumber);
			// 1 = Login
			if (protocoleNumber == 1) {
				protocole = new ProtocoleLoginClient();
			}
			// 2 = Creation de compte
			else if (protocoleNumber == 2) {
				protocole = new ProtocoleCreationCompte();
			}
			else if (protocoleNumber == 3){
				protocole = new ProtocoleMessagePrive();
			}

			outPutRes = protocole.execute(context);
			context.setEtat(outPutRes);
			objectOutputStream.writeObject(context);

			System.out.println("[Traitement Context] Traitement Context fait");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
