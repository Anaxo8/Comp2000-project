import java.util.ArrayList;
import java.util.Random;

enum RoomType {
    EMPTY,
    ITEM,
    MONSTER
}
enum RoomRole{ //Saw enum in item class and thought it'd make sense here
    START,
    NORMAL,
    END
}
public class Room{
    private int id;
    private RoomType type;
    private RoomRole role;
    private ArrayList<Object> contents;

    Room(int id, RoomRole role) {
        this.id = id;
        this.role = role;
        this.type = RoomType.EMPTY;
        this.contents = new ArrayList<>();
    }

    public void rollRoomType() {
        Random n = new Random();
        int roll = n.nextInt(51);
        
        if (role == RoomRole.START) {
            type = RoomType.EMPTY;
            return;
        }

        if (role == RoomRole.END)  {
            type = RoomType.ITEM;
            return;
        }

        if (role == RoomRole.NORMAL && roll % 2 == 0) {
            type = RoomType.ITEM;
            //IDEA: Add a specific number of random items from a list (TBD if this will happen)
        } else {
            type = RoomType.MONSTER;
            //SAME IDEA AS THE ITEM BUT WITH MONSTERS
        }
    }

    public void addContent(Object content) {
        if (content != null) {
            contents.add(content);
        }
    }

    public void clearContents() {
        contents.clear();
    }
}