import java.util.ArrayList;

public class KillFeed {

    private ArrayList<String> messages;
    private int maxMessages;

    public KillFeed() {
        messages = new ArrayList<String>();
        maxMessages = 5;
    }
    public void addKill(String killer, String victim) {

    if (killer == null || victim == null ||
        killer.isBlank() || victim.isBlank()) {
        throw new IllegalArgumentException(
            "Killer and victim must have a name"
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