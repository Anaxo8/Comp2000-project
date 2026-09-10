import java.util.ArrayList;

public class KillFeed {

    private ArrayList<String> messages; //generic:only allows for type string in the arrayList
    private int maxMessages; //no more than 5 messages

    public KillFeed() {
        messages = new ArrayList<String>();
        maxMessages = 5;
    }
    public void addKill(String killer, String victim) {

    if (killer == null || victim == null ||
        killer.isBlank() || victim.isBlank()) {
        throw new IllegalArgumentException(   
            "Killer and victim must have a name"   //The program will not run 
        );
    }

    messages.add(killer + " killed " + victim);

    if (messages.size() > maxMessages) {
        messages.remove(0);
    }
}

    public ArrayList<String> getMessages() {
        return messages;
    }
}