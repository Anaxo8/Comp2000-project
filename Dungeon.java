public class Dungeon {
    private ArrayList<Room> rooms = new ArrayList<>();

    public void generate(int roomCount) {
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
}
