package Protocoles;

import Contexts.IContext;
import Outils.Compte;
import Outils.Conversation;
import Outils.Tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProtocoleDemarrage implements IProtocole {
    Tools tools = new Tools();
    String convoPath = "src/Conversations/";

    public HashMap<String,ArrayList<String>> ConversationsOfUsers = new HashMap<String, ArrayList<String>>();
    public ArrayList<Conversation> loadedConservations = new ArrayList<Conversation>();
    public ArrayList<Compte> loggedAccounts = new ArrayList<Compte>();


    public ProtocoleDemarrage() {

        File dir = new File(this.convoPath);
        File[] fileList = dir.listFiles();

        try {
            String[] users = tools.getUserList();
            for (String user : users) {
                ArrayList<String> conversationList = new ArrayList<String>();

                for (File file : fileList) {
                    BufferedReader f = new BufferedReader(new FileReader(file));
                    String conversationUsers = f.readLine();
                    String[] listConversationUsers = conversationUsers.split(" ");
                    f.close();

                    for (String userInConvo : listConversationUsers) {
                        if (user.equals(userInConvo)) {
                            conversationList.add(file.getName());
                        }
                    }
                }
                ArrayList<String> cL;
                cL = (ArrayList<String>) conversationList.clone();
                this.getConversationsOfUsers().put(user, cL);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String execute(IContext context) {
        return null;
    }

    public HashMap<String, ArrayList<String>> getConversationsOfUsers() {
        return ConversationsOfUsers;
    }

        // TODO : si nouvelle convo ??? cf execute imo
    public void setConversationsOfUsers(HashMap<String, ArrayList<String>> conversationsOfUsers) {
        ConversationsOfUsers = conversationsOfUsers;
    }

    public ArrayList<String> getUserConvo (String user) {
        ArrayList<String> convos;
        convos = this.ConversationsOfUsers.get(user);
        return convos;
    }

    public Conversation loadConversation(String filePath) throws IOException {
        int id = Integer.parseInt(filePath.substring(0, filePath.length()-4));
        for (Conversation convo : this.loadedConservations) {
            if (id == convo.getId()) {
                return convo;
            }
        }

        String path = convoPath + filePath;
        BufferedReader f = new BufferedReader(new FileReader(path));

        String[] convoUsers = f.readLine().split(" ");
        String convoName = f.readLine();

        Conversation convo = new Conversation(convoUsers, convoName, path);
        this.loadedConservations.add(convo);

        return convo;
    }
}
