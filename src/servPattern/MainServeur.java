package servPattern;

public class MainServeur {
	/**
	 * A lancer pour l'exécution du sevreur
	 */
	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP(6666);
		myServ.start();
	}
}
