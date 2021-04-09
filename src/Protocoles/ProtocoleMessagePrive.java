package Protocoles;

import Contexts.IContext;
import Contexts.MessagePriveContext;

public class ProtocoleMessagePrive implements IProtocole{
    @Override
    public String execute(IContext context, ProtocoleDemarrage protocoleDemarrage) {
        System.out.println("[ProtMessPrive] executing...");
        MessagePriveContext context1 = (MessagePriveContext) context;
        String outPut = "message envoyé";

        String name = context1.getCompte().getUserID();
        String mess = context1.getMessage();

        context1.getConversation().writeLogs(mess, name);
        return outPut;
    }
}
