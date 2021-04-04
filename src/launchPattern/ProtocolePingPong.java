//package launchPattern;
//import CLIENT.Contexts.IContext;
//import SERVEUR.Protocoles.IProtocole;
//
//import java.io.*;
//
//
//public class ProtocolePingPong implements IProtocole {
//
//	public void execute( IContext c , InputStream unInput , OutputStream unOutput ) {
//
//		String inputReq;
//		BufferedReader is = new BufferedReader(new InputStreamReader(
//				unInput));
//		PrintStream os = new PrintStream(unOutput);
//		try {
//			String valeurExpediee = "...";
//
//			if ((inputReq = is.readLine()) != null) {
//				System.out.println(" Ordre Recu " + inputReq);
//				String chaines[] = inputReq.split(" ");
//
//				if (chaines[0].contentEquals("PING")) {
//					valeurExpediee = "PONG";
//					System.out.println(" Reponse serveur "	+ valeurExpediee);
//				}
//				os.println(valeurExpediee);
//			}
//		} catch ( Exception e) {
//			System.out.println(" Pb d'exception ");
//		}
//	}
//}
