package connexion;

import Contexts.IContext;

import java.io.*;
import java.net.*;

public class ClientTCP {

	private int numeroPort;
	private String nomServeur;
	private Socket socketServeur;

	private PrintStream socOut;
	private BufferedReader socIn;

	public PrintStream getSocOut() {
		return socOut;
	}

	public BufferedReader getSocIn() {
		return socIn;
	}

	/**
	 * Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port unNumero
	 */
	public ClientTCP(String unNomServeur, int unNumero) {
		numeroPort = unNumero;
		nomServeur = unNomServeur;
	}

	public boolean connecterAuServeur() {
		boolean ok = false;
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
			socketServeur = new Socket(nomServeur, numeroPort);
			socOut = new PrintStream(socketServeur.getOutputStream());
			socIn = new BufferedReader(
					new InputStreamReader(socketServeur.getInputStream()));
			ok = true;
		} catch (UnknownHostException e) {
			System.err.println("[CLIENT] Serveur inconnu : " + e);

		} catch (ConnectException e) {
			System.err.println("[CLIENT] Exception lors de la connexion:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("[CLIENT] Exception lors de l'echange de donnees:" + e);
		}
		System.out.println("[CLIENT] Connexion faite");
		return ok;
	}

	public void deconnecterDuServeur() {
		try {
			System.out.println("[CLIENT] Deconnexion : " + socketServeur);
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("[CLIENT] Exception lors de la deconnexion :  " + e);
		}
	}

	public String transmettreChaine(String uneChaine) {
		String msgServeur = null;
		try {
			System.out.println("[CLIENT] Requete client : " + uneChaine);
			socOut.println(uneChaine);
			socOut.flush();
			msgServeur = socIn.readLine();
			System.out.println("[CLIENT] Reponse serveur : " + msgServeur);

		} catch (UnknownHostException e) {
			System.err.println("[CLIENT] Serveur inconnu : " + e);
		} catch (IOException e) {
			System.err.println("[CLIENT] Exception entree/sortie:  " + e);
			e.printStackTrace();
		}
		return msgServeur;
	}

	public IContext transmettreContext(IContext context) throws IOException, ClassNotFoundException {

		OutputStream outputStream = socketServeur.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		System.out.println("[CLIENT] Requete context client : " + context);
		objectOutputStream.writeObject(context);

		InputStream inputStream = socketServeur.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		IContext contextReceived = (IContext) objectInputStream.readObject();

		System.out.println("[CLIENT] Reponse serveur : " + contextReceived.getEtat());
		return contextReceived;
	}


}
