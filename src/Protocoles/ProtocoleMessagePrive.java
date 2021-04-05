package Protocoles;

import Contexts.IContext;
import Contexts.MessagePriveContext;

public class ProtocoleMessagePrive extends ProtocoleDemarrage implements IProtocole{
    @Override
    public String execute(IContext context) {
        System.out.println("[ProtMessPrive] executing...");
        MessagePriveContext messagePriveContext = (MessagePriveContext) context;

        String outPut = null;
        String message = messagePriveContext.getMessage();
        String sender = messagePriveContext.getSender();
        String receiver = messagePriveContext.getReceiver();


        return null;
    }
}
