package Protocoles;


import Contexts.IContext;
import Contexts.LoginContext;
import Outils.*;

import java.util.ArrayList;


public class ProtocoleLoginClient extends ProtocoleDemarrage implements IProtocole {

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
                ArrayList<String> convos = getUserConvo(userID);
                ArrayList<Conversation> conversations = new ArrayList<Conversation>();
//                for (String filePath : convos) {
//                    Conversation conversation = loadConversation(filePath);
//                    conversations.add(conversation);
//                }
                Compte compte = new Compte(userID, conversations);
                loggedAccounts.add(compte);

                outPut = "Connexion validee";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return outPut;
    }
}
