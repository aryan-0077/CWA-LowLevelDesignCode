package Composition;

public class House {
    // Composition: A House is composed of Rooms.
    private Room livingRoom;
    private Room kitchen;
    House() {
        // Rooms are created and owned by the House.
        livingRoom = new Room("Living Room");
        kitchen = new Room("Kitchen");
    }
    void showHouse() {
        System.out.println(
                "House contains: " + livingRoom.name + " and " + kitchen.name);
    }
}
