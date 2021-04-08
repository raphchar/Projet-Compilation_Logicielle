package servPattern;

public class MainServeur {

	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP(6666);
		myServ.start();
	}
}
