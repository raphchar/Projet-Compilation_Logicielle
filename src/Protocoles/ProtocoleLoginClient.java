package Protocoles;

import servPattern.IContext;

import java.io.*;

public class ProtocoleLoginClient {


    public void execute( IContext c , InputStream unInput , OutputStream unOutput ) {

        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";

            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);
                String chaines[] = inputReq.split(" ");

                if (chaines[0].contentEquals("LOGIN")) {
                    // Todo : coder le traitement d'un login
                }
                os.println(valeurExpediee);
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
