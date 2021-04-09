package Protocoles;

import Contexts.AffichageConvoContext;
import Contexts.IContext;
import Outils.Conversation;

import java.io.IOException;
/**
 * Protocole de traitement de context d'affichage d'une conversation
 */
public class ProtocoleAffichConvo implements IProtocole{
    @Override
    public String execute(IContext context, ProtocoleDemarrage protocoleDemarrage) {
        System.out.println("[ProtAffichConvo] executing...");
        AffichageConvoContext affichageConvoContext = (AffichageConvoContext) context;

        String outPut = "erreur dans le chargement de l'affichage";
        for (Conversation convoLoaded : protocoleDemarrage.getLoadedConservations()) {
            if ( (convoLoaded.getName()).equals(affichageConvoContext.getNomConvo()) ) {
                try {
                    convoLoaded.loadLogs();

                    affichageConvoContext.setConversation(convoLoaded);
                    outPut = "chargement de la conversation fait";

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
//        System.out.println("[ProtAffichConvo] convo : " + (affichageConvoContext.getConversation().getLogs()).get(0) );
        return outPut;
    }
}
