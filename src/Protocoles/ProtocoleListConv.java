//package Protocoles;
//
//import Contexts.IContext;
//import Contexts.ListConvContext;
//import Outils.Compte;
//import Outils.Conversation;
//
//import java.util.ArrayList;
//
//public class ProtocoleListConv extends ProtocoleDemarrage implements IProtocole{
//
//    @Override
//    public String execute(IContext context) {
//        System.out.println("[ProtListConv] executing...");
//        ListConvContext listConvContext = (ListConvContext) context;
//
//        String outPut = null;
//
//        // CHANGER ICI POUR RECUPERER l'ID DU COMPTE ET RECUPERER LES CONV ASSOCIEES VIA LOADCONV
//        String userID = compte.getId();
//
//        try {
//            // INSERER ICI
//            // setListConv = compte.convos;
//            outPut = "Liste conversations transmise";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return outPut;
//    }
//
//}
