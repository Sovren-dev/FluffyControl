package fluffycontrol;

import java.util.ArrayList;
import java.util.List;



/**
 * date: 2026-02-25
 * @author Sovren
 */
public class UserData {
    private int credits;
    private int tokens;
    private List<String> tasks = new ArrayList<>();
    
    public int getCredits(){return credits;}
    public void setCredits(int credits) {this.credits = credits;}
    
    public int getTokens(){return tokens;}
    public void setTokens(int tokens) {this.tokens = tokens;}
    
    public List<String> getTasks() {return tasks;}
    public void setTasks(List<String> tasks) {this.tasks = tasks;}
}
