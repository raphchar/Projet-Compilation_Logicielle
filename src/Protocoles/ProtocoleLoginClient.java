package Protocoles;

import servPattern.IContext;

import java.io.*;

public class ProtocoleLoginClient {

    private Tools Ids = new Tools();
    private String name = "";

    public void execute( IContext c , InputStream unInput , OutputStream unOutput ) {

        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
        PrintStream os = new PrintStream(unOutput);

        try {
            String valeurExpediee = "";

            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);
                String nom = inputReq;
                String mdp = is.readLine();
                System.out.println("connection token : " + nom + " " + mdp);

                if (Ids.checkMDP(nom, mdp)) {
                    valeurExpediee = "true";
                    this.name = nom;
                    System.out.println(valeurExpediee);
                }
                else {
                    valeurExpediee = "false";
                    System.out.println(valeurExpediee);
                }

                os.println(valeurExpediee);
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
