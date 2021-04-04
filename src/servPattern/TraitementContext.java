package servPattern;

import Contexts.IContext;
import Protocoles.IProtocole;
import Protocoles.*;

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
			BufferedReader is = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			PrintStream os = new PrintStream(clientSocket.getOutputStream());

			InputStream inputStream = clientSocket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

			String inputReq;
			String outPutRes;

//			protocoleNumber = is.read();
//			inputReq = is.readLine();
			IContext context = (IContext) objectInputStream.readObject();
			protocoleNumber = context.getProtocole();
			System.out.println("[Traitement Conext] protocol Number : " + protocoleNumber);

			// 76 = Login
			if (protocoleNumber == 76) {
				protocole = new ProtocoleLoginClient();
			}
			// 67 = Creation de compte
			else if (protocoleNumber == 67) {
				protocole = new ProtocoleCreationCompte();
			}

			outPutRes = protocole.execute(context);
			System.out.println("[Traitement Context] Traitement Context fait");
			os.println(outPutRes);
			os.flush();

//		} catch (IOException e) {
//			System.err.println("[Traitement Context] Exception : " + e );
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
