package Protocoles;

import Contexts.IContext;
import Contexts.QuitterContext;

public class ProtocoleQuitter extends ProtocoleDemarrage implements IProtocole {

    @Override
    public String execute(IContext context) {
        System.out.println("[ProtQuitter] executing...");
        QuitterContext quitterContext = (QuitterContext) context;

        String outPut = "fermeture reussie";
        return outPut;
    }
}
