import java.io.IOException;
import java.util.Scanner;

public class Medium {
    public static void main(String[] args) throws IOException {

        //creating key instances and flash light to be used during the game
        Key greyKey = new Key();
        greyKey.setName("greyKey");
        greyKey.setPrice(120);

        Key greenKey= new Key();
        greenKey.setName("greenKey");
        greenKey.setPrice(75);

        Key orangeKey = new Key();
        orangeKey.setName("orangeKey");
        orangeKey.setPrice(200);

        Key blackKey = new Key();
        blackKey.setName("blackKey");
        blackKey.setPrice(97);

        Key pinkKey = new Key();
        pinkKey.setName("pinkKey");
        pinkKey.setPrice(105);

        Key purpleKey = new Key();
        purpleKey.setName("purpleKey");
        purpleKey.setPrice(137);

        Key whiteKey = new Key();
        whiteKey.setName("whiteKey");
        whiteKey.setPrice(113);

        Key yellowKey = new Key();
        yellowKey.setName("yellowKey");
        yellowKey.setPrice(95);

        FlashLight ledFlash = new FlashLight();
        ledFlash.setName("ledFlash");
        ledFlash.setPrice(120);


        //creating walls of different types and giving them some attributes
        PlainWall plainWall1 = new PlainWall();
        PlainWall plainWall2 = new PlainWall();

        Mirror mirror1 = new Mirror();
        mirror1.setHiddenKey(greyKey);

        Mirror mirror2 = new Mirror();

        Painting painting1 = new Painting();
        painting1.setHiddenKey(purpleKey);

        Chest chest1 = new Chest();
        chest1.lock();
        chest1.setKey(pinkKey);
        chest1.addItem(orangeKey);

        Chest chest2 = new Chest();
        chest2.setKey(whiteKey);
        chest2.addItem(yellowKey);
        chest2.addItem(pinkKey);

        Seller seller1 = new Seller();
        seller1.addItem(ledFlash);
        seller1.addItem(blackKey);

        Door door1 = new Door();
        door1.lock();
        door1.setKey(greyKey);

        Door door2 = new Door();
        door2.setKey(greenKey);

        Door door3 = new Door();
        door3.lock();
        door3.setKey(orangeKey);

        Door door4 = new Door();
        door4.lock();
        door4.setKey(blackKey);

        //creating game rooms
        Room room1 = new Room();
        room1.addLights();
        room1.setNorthWall(plainWall1);
        room1.setWestWall(chest1);
        room1.setSouthWall(door1);
        room1.setEastWall(mirror1);

        Room room2 = new Room();
        room2.addLights();
        room2.turnLightButton();
        room2.setNorthWall(door1);
        room2.setWestWall(seller1);
        room2.setSouthWall(plainWall2);
        room2.setEastWall(door2);

        Room room3 = new Room();
        room3.setNorthWall(mirror2);
        room3.setWestWall(door2);
        room3.setSouthWall(door4);
        room3.setEastWall(door3);

        Room room4 = new Room();
        room4.setWestWall(door3);
        room4.addAsExitPoint();

        Room room5 = new Room();
        room5.addLights();
        room5.turnLightButton();
        room5.setNorthWall(door4);
        room5.setWestWall(chest2);
        room5.setSouthWall(painting1);
        room5.setEastWall(plainWall2);

        //crating game
        Game game = new Game();
        game.addItem(greyKey);
        game.addItem(greenKey);
        game.addItem(orangeKey);
        game.addItem(blackKey);
        game.addItem(pinkKey);
        game.addItem(purpleKey);
        game.addItem(whiteKey);
        game.addItem(yellowKey);
        game.addItem(ledFlash);
        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);
        game.addRoom(room4);
        game.addRoom(room5);
        game.addDoor(door1, room1, room2);
        game.addDoor(door2, room2, room3);
        game.addDoor(door3, room3, room4);
        game.addDoor(door4, room3,room5);

        //reading commands from console
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            game.readCommands(command);
        }
    }
}