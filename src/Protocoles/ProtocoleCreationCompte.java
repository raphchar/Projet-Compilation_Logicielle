package Protocoles;

import Contexts.CreationCompteContext;
import Contexts.IContext;
import Outils.Compte;
import Outils.Conversation;
import Outils.Tools;

import java.util.ArrayList;

public class ProtocoleCreationCompte implements IProtocole {

    private Tools tools = new Tools();

    @Override
    public String execute(IContext context, ProtocoleDemarrage protocoleDemarrage) {
        System.out.println("[ProtCreationCompte] executing...");
        CreationCompteContext creationCompteContext = (CreationCompteContext) context;

        String outPut = null;
        String userID = creationCompteContext.getId();
        String userPW = creationCompteContext.getPw();
        String userVerifPW = creationCompteContext.getVerifPw();

        try {
            if (tools.isUser(userID)) {
                outPut = "Nom d'utilisateur deja pris";
            }
            else if (!userPW.equals(userVerifPW)) {
                outPut = "Mots de passe differents";
            }
            else {
                tools.addUser(userID,userPW);
                Compte compte = new Compte(userID, new ArrayList<Conversation>());
                protocoleDemarrage.ajoutCompte(compte);

                outPut = "Compte cree";
                creationCompteContext.compte = compte;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPut;
    }
}
