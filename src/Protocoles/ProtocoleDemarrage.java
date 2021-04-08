package Protocoles;

import Outils.Compte;
import Outils.Conversation;
import Outils.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProtocoleDemarrage {
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

    public HashMap<String, ArrayList<String>> getConversationsOfUsers() {
        return ConversationsOfUsers;
    }

    public ArrayList<Conversation> getLoadedConservations() {
        return loadedConservations;
    }

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

    public boolean isConnected(String userID) {
        for (Compte account : loggedAccounts) {
            if (account.getUserID().equals(userID))
                return true;
        }
        return false;
    }

    public void ajoutCompte(Compte compte) {
        loggedAccounts.add(compte);
    }
}
