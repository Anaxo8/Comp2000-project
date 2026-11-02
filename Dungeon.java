import java.util.ArrayList;

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
