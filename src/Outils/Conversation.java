package Outils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Instancie une conversation : son chemin, ses users, ses logs, etc
 */

public class Conversation implements Serializable {
    private String logPath;
    private String name;
    private String[] users;
    private int id;
    private ArrayList<String> logs = new ArrayList<String>();

    public Conversation(String[] users, String name, String fileName) {
        this.users = users;
        this.name = name;
        this.logPath = fileName;
        this.id = Integer.parseInt(fileName.substring("src/Conversation/".length() + 1, fileName.length() - 4));
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    public String toString() {
        return "nomConvo : " + name + "users : " + Arrays.toString(users) + "id : " + id;
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

    public void loadLogs() throws IOException {
        File file = new File(logPath);
        BufferedReader f = new BufferedReader(new FileReader(file));
        f.readLine();
        f.readLine();

        String line;

        while ((line = f.readLine()) != null) {
            logs.add(line);
        }
    }

    public void writeLogs(String message, String nom) {
        File file = new File(logPath);
        System.out.println("WRITELOGS : logpath = " + logPath + ", message = " + message + ", nom = " + nom);
        FileWriter fr;
        try {
            fr = new FileWriter(file, true);
            fr.write("\n" + nom + " : " + message);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.logs.add(nom + " : " + message + "\n");
        System.out.println("WRITELOGS : LOGS = " + logs);

    }
}
