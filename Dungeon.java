import java.util.ArrayList;
import Entities.Enemy;
import Items.Weapon;

public class Dungeon {
    private ArrayList<Room> rooms;

    public Dungeon() {
        rooms = new ArrayList<>();
    }

    public void generate(int roomCount) {
        if (roomCount < 2) {
            throw new IllegalArgumentException(
                "A dungeon must contain at least a start and end room."
            );
        }

        rooms.clear();

        for (int i = 0; i < roomCount; i++) {
            RoomRole role;

            if (i == 0) {
                role = RoomRole.START;
            } else if (i == roomCount - 1) {
                role = RoomRole.END;
            } else {
                role = RoomRole.NORMAL;
            }

            Room room = new Room(i, role);
            room.rollRoomType();
            
            if (i == 1 && role == RoomRole.NORMAL) {
                room.addEnemy(new Enemy("eOne", 20, 5, 5, new Weapon("Iron Sword", 30, 5)));
                room.addEnemy(new Enemy("eTwo", 15, 5, 5, null));
                room.addEnemy(new Enemy("eThree", 20, 10, 5, null));
                room.addEnemy(new Enemy("eFour", 20, 10, 5, null));
            }

            rooms.add(room);
        }
    }
    
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room getRoom(int index) {
        return rooms.get(index);
    }
}
