import java.util.ArrayList;

public class Dungeon {
    private ArrayList<Room> rooms;
    private int currentRoom;

    public Dungeon() {
        rooms = new ArrayList<>();
        this.currentRoom = 0; // start from 1st room
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
    
    public Room getCurrentRoom(){
        return rooms.get(currentRoom);
    }

    public Room goNextRoom(){
        currentRoom++;
        if (currentRoom >= rooms.size()){
            return null;    
        }
        return rooms.get(currentRoom);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room getRoom(int index) {
        return rooms.get(index);
    }
}
