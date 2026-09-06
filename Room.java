import java.util.ArrayList;

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
    RoomType type;
    RoomRole role;
    List<contents> contents;

    Room(int id, RoomRole role) {
        this.id = id;
        this.role = role;
        this.type = RoomType.EMPTY;
        this.contents = new ArrayList<>();
    }

    public void RollRoomType() {
        int roll = random(0,50);
        if (roll % 2 == 0) {
            type = RoomType.ITEM;
            //IDEA: Add a specific number of random items from a list (TBD if this will happen)
        } else {
            type = RoomType.MONSTER;
            //SAME IDEA AS THE ITEM BUT WITH MONSTERS
        }
    }
}