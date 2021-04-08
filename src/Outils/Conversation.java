package Outils;

import java.io.Serializable;
import java.util.Arrays;

public class Conversation implements Serializable {
    private String logPath;
    private String name;
    private String[] users;
    private int id;

    public Conversation(String[] users, String name, String fileName) {
        this.users = users;
        this.name = name;
        this.logPath = "src/Conversation/" + fileName;
        this.id = Integer.parseInt(fileName.substring("src/Conversation/".length()+1, fileName.length()-4));
    }

    public String toString () {
        return "nomConvo : " + name + "users : "+ Arrays.toString(users) + "id : " + id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
