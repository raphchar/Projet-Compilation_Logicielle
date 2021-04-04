package Protocoles;

import Outils.Tools;

public class ProtocoleCreationCompte implements IProtocole {

    private Tools tools = new Tools();

    @Override
    public String execute(String inputString) {
        System.out.println("[ProtCreationCompte] executing...");

        String outPut = "erreur";
        String[] values = inputString.split("'");
        String userID = values[1];
        String userPW = values[3];

        try {
            tools.addUser(userID, userPW);
            outPut = "Compte créé";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outPut;
    }
}
