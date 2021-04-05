package Outils;

public class Conversation {
    private String logPath;
    private String[] users;

    public Conversation(String[] users) {
        this.users = users;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public boolean isInConversation(String user) {
        boolean state = false;
        for (String us : this.users) {
            if (user.equals(us)) {
                state = true;
                break;
            }
        }
        return state;
    }
}
