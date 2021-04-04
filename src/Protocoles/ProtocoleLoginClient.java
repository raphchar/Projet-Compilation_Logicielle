package Protocoles;


import servPattern.IProtocole;


public class ProtocoleLoginClient implements IProtocole {

    private Tools tools = new Tools();

    @Override
    public String execute(String inputString) {
        System.out.println("[ProtLoginClient] executing...");

        String outPut = "";
        String[] values = inputString.split("'");
        String userID = values[1];
        String userPW = values[3];

        try {
            if (!tools.isUser(userID)) {
                outPut = "Unknown user : " + userID;
            }
            else if (!tools.checkMDP(userID, userPW)) {
                outPut = "Invalid password : " + userPW;
            }
            else {
                outPut = "connection successful";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPut;

    }
}
