package Protocoles;

import Contexts.IContext;
import Contexts.NouvelleConvoContext;
import Outils.Conversation;

import java.io.File;
/**
 * Protocole de traitement de context de réception d'un message
 */
public class ProtocoleNouvelleConvo implements IProtocole{
    @Override
    public String execute(IContext context, ProtocoleDemarrage protocoleDemarrage) {
        System.out.println("[ProtNouvelleConvo] executing...");
        String outPut = "Nouvelle convo cree";

        String[] users = ((NouvelleConvoContext) context).getUserRaw().split(";");
        String convoName = ((NouvelleConvoContext) context).getNameConvo();

        String finDuPath = (protocoleDemarrage.countConvos()+1) + ".txt";
        String pathName = "src\\Conversations\\" + finDuPath;

        File file = new File(pathName);
        Conversation conversation = new Conversation(users, convoName, pathName);
        ((NouvelleConvoContext) context).setConversation(conversation);
        protocoleDemarrage.loadedConservations.add(conversation);

        return outPut;
    }
}
