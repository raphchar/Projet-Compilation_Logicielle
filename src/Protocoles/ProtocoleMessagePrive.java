package Protocoles;

import Contexts.IContext;
import Contexts.MessagePriveContext;
import Outils.Conversation;
/**
 * Protocole de traitement de context d'envoi de message
 */
public class ProtocoleMessagePrive implements IProtocole{
    @Override
    public String execute(IContext context, ProtocoleDemarrage protocoleDemarrage) {
        System.out.println("[ProtMessPrive] executing...");
        MessagePriveContext context1 = (MessagePriveContext) context;
        String outPut = null;

        String name = context1.getCompte().getUserID();
        String mess = context1.getMessage();

        for (Conversation conv : protocoleDemarrage.getLoadedConservations()) {
            if (conv.getId() == context1.getConversation().getId()) {
                conv.writeLogs(mess, name);
                outPut = "message envoyé";
            }
        }

        return outPut;
    }
}
