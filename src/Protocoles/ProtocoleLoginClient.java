package Protocoles;


import Contexts.IContext;
import Contexts.LoginContext;
import Outils.Tools;


public class ProtocoleLoginClient implements IProtocole {

    private Tools tools = new Tools();

    @Override
    public String execute(IContext context) {
        System.out.println("[ProtLoginClient] executing...");
        LoginContext loginContext = (LoginContext) context;

        String outPut = null;
        String userID = loginContext.getId();
        String userPW = loginContext.getPw();

        try {
            if (!tools.isUser(userID)) {
                outPut = "Nom d utilisateur inconnu";
            }
            else if (!tools.checkMDP(userID, userPW)) {
                outPut = "Mot de passe incorrect";
            }
            else {
                outPut = "Connexion validee";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return outPut;
    }
}
