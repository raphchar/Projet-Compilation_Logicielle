package Protocoles;

import Contexts.IContext;
import Contexts.QuitterContext;

public class ProtocoleQuitter implements IProtocole {

    @Override
    public String execute(IContext context, ProtocoleDemarrage protocoleDemarrage) {
        System.out.println("[ProtQuitter] executing...");
        QuitterContext quitterContext = (QuitterContext) context;

        String outPut = "fermeture reussie";
        return outPut;
    }
}
