package Outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Tools {

    private String filename = "src/servPattern/DatabaseID.txt";

    public void setFilename(String newFilname){
        this.filename = newFilname;
    }
    public String getFilename(){
        return this.filename;
    }

    private HashMap<String,String> dictLogin = new HashMap<String,String>();

    private void closeLogFile() throws Exception{
        File file = new File(filename);

        // clear the file
        PrintWriter pw = new PrintWriter(file);
        pw.close();

        // copy whiole hashmap into the file
        BufferedWriter f = new BufferedWriter(new FileWriter(file));
        String line = null;
        for (String key : this.dictLogin.keySet()) {
            f.write(key + " " + this.dictLogin.get(key) + "\n");
        }
        f.close();
    }

    private void openLogFile() throws Exception {
        if(Files.notExists(Paths.get(filename))){
            this.closeLogFile();
        }
        File file = new File(filename);
        BufferedReader f = new BufferedReader(new FileReader(file));

        // copy the whole file into the hashmap
        String line = null;
        String[] datas;
        while ((line = f.readLine()) != null) {
            datas = line.split(" ");
            this.dictLogin.put(datas[0], datas[1]);
        }
        f.close();
    }

    public boolean isUser(String userName) throws Exception{
        boolean state=false;
        this.openLogFile();
        if (dictLogin.get(userName)!=null){ state=true; }
        return state;
    }

    public void addUser(String userName, String passWord) throws Exception{
        this.openLogFile();
        this.dictLogin.put(userName, passWord);
        this.closeLogFile();
    }

    public boolean checkMDP(String userName, String passWord) throws Exception {
        this.openLogFile();
        boolean state = false;
        String MDP = this.dictLogin.get(userName);
        if (passWord.equals(MDP)) { state = true; }
        return state;
    }

    public void deleteUser(String userName, String passWord) throws Exception{
        if (this.checkMDP(userName,passWord)){
            this.openLogFile();
            this.dictLogin.remove(userName);
            this.closeLogFile();
        }
    }

    public String[] getUserList() throws Exception {
        this.openLogFile();
        String[] users = this.dictLogin.keySet().toArray(new String[0]);
        this.closeLogFile();
        return users;
    }
}
