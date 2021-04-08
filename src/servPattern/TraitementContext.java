package servPattern;

import Contexts.IContext;
import Protocoles.*;

import java.io.*;
import java.net.Socket;

/**
 * TraitementContext vient lire et écrire sur les socket du client, transmises par ServeurTCP
 * En interprétant la première lettre du message entrant, TraitementContext sait quel protocole il doit appeller
 */
class TraitementContext extends ServeurTCP {

	private final Socket clientSocket;

	public TraitementContext(Socket uneSocket) {
		super(0);
		clientSocket = uneSocket;
	}

	public void run() {
		int protocoleNumber = 0;
		IProtocole protocole = null;
		boolean listening = true;

		// Inclure un while pour rester en écoute
		while (listening) {

			try {
				
				System.out.println("[Traitement Context] Attente Contexte...");
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
				// 3 = Affichage conversation
				else if (protocoleNumber == 3) {
					protocole = new ProtocoleAffichConvo();
				}
				// 10 = Stop
				else if (protocoleNumber == 10) {
					listening = false;
					protocole = new ProtocoleQuitter();
				}

				outPutRes = protocole.execute(context, getProtocoleDemarrage());
				context.setEtat(outPutRes);
				objectOutputStream.writeObject(context);

				System.out.println("[Traitement Context] Traitement Context fait");

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
