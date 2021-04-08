package Protocoles;

import Contexts.AffichageConvoContext;
import Contexts.IContext;
import Outils.Conversation;

import java.io.File;

public class ProtocoleNouvelleConvo implements IProtocole{
    @Override
    public String execute(IContext context, ProtocoleDemarrage protocoleDemarrage) {
        System.out.println("[ProtNouvelleConvo] executing...");
        String outPut = "Nouvelle convo cree";

        String[] users = ((AffichageConvoContext) context).getUserRaw().split(" ");
        String convoName = ((AffichageConvoContext) context).getNomConvo();

        String finDuPath = (protocoleDemarrage.countConvos()+1) + ".txt";
        String pathName = "src\\Conversations\\" + finDuPath;

        File file = new File(pathName);
        Conversation conversation = new Conversation(users, convoName, pathName);
        protocoleDemarrage.loadedConservations.add(conversation);

        return outPut;
    }
}
