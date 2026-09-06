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
    int id;
    private RoomType type;
    private RoomRole role;
    private ArrayList<contents> contents;

    Room(int id, RoomRole role) {
        this.id = id;
        this.role = role;
        this.type = RoomType.EMPTY;
        this.contents = new ArrayList<>();
    }

    public void RollRoomType() {
        Random n = new Random();
        int roll = n.nextInt(51);
        
        if (roll % 2 == 0) {
            type = RoomType.ITEM;
            //IDEA: Add a specific number of random items from a list (TBD if this will happen)
        } else {
            type = RoomType.MONSTER;
            //SAME IDEA AS THE ITEM BUT WITH MONSTERS
        }
    }
}