import java.io.IOException;
import java.util.Scanner;

public class Hard {
    public static void main(String[] args) throws IOException {

        //creating key instances and flash light to be used during the game
        Key blackKey = new Key();
        blackKey.setName("blackKey");
        blackKey.setPrice(99);

        Key whiteKey = new Key();
        whiteKey.setName("whiteKey");
        whiteKey.setPrice(115);

        Key silverKey = new Key();
        silverKey.setName("silverKey");
        silverKey.setPrice(200);

        Key bronzeKey = new Key();
        bronzeKey.setName("bronzeKey");
        bronzeKey.setPrice(150);

        Key goldKey = new Key();
        goldKey.setName("goldKey");
        goldKey.setPrice(300);

        Key woodKey = new Key();
        woodKey.setName("woodKey");
        woodKey.setPrice(170);

        Key redKey = new Key();
        redKey.setName("redKey");
        redKey.setPrice(153);

        FlashLight flash = new FlashLight();
        flash.setName("flash");
        flash.setPrice(120);


        //creating walls of different types and giving them some attributes
        PlainWall plainWall1 = new PlainWall();
        PlainWall plainWall2 = new PlainWall();
        PlainWall plainWall3 = new PlainWall();
        PlainWall plainWall4 = new PlainWall();

        Mirror mirror1 = new Mirror();
        mirror1.setHiddenKey(woodKey);

        Mirror mirror2 = new Mirror();

        Painting painting1 = new Painting();
        painting1.setHiddenKey(whiteKey);

        Painting painting2 = new Painting();

        Chest chest1 = new Chest();
        chest1.lock();
        chest1.setKey(goldKey);

        Chest chest2 = new Chest();
        chest2.setKey(whiteKey);
        chest2.addItem(bronzeKey);

        Seller seller1 = new Seller();
        seller1.addItem(flash);
        seller1.addItem(silverKey);
        seller1.addItem(goldKey);

        Door door1 = new Door();
        door1.setKey(blackKey);

        Door door2 = new Door();
        door2.lock();
        door2.setKey(woodKey);

        Door door3 = new Door();
        door3.setKey(redKey);

        Door door4 = new Door();
        door4.lock();
        door4.setKey(silverKey);

        Door door5 = new Door();
        door5.lock();
        door5.setKey(bronzeKey);

        //creating game rooms
        Room room1 = new Room();
        room1.addLights();
        room1.setNorthWall(painting1);
        room1.setWestWall(plainWall1);
        room1.setSouthWall(door1);
        room1.setEastWall(plainWall2);

        Room room2 = new Room();
        room2.addLights();
        room2.turnLightButton();
        room2.setNorthWall(door1);
        room2.setWestWall(mirror1);
        room2.setSouthWall(seller1);
        room2.setEastWall(door2);

        Room room3 = new Room();
        room3.addLights();
        room3.setNorthWall(door3);
        room3.setWestWall(door2);
        room3.setSouthWall(plainWall3);
        room3.setEastWall(mirror2);

        Room room4 = new Room();
        room4.addLights();
        room4.turnLightButton();
        room4.setNorthWall(door5);
        room4.setWestWall(chest1);
        room4.setSouthWall(door3);
        room4.setEastWall(door4);

        Room room5 = new Room();
        room5.setNorthWall(painting2);
        room5.setWestWall(door4);
        room5.setSouthWall(chest2);
        room5.setEastWall(plainWall4);

        Room room6 = new Room();
        room6.addAsExitPoint();
        room6.setSouthWall(door5);

        //crating game
        Game game = new Game();
        game.addItem(blackKey);
        game.addItem(whiteKey);
        game.addItem(silverKey);
        game.addItem(bronzeKey);
        game.addItem(goldKey);
        game.addItem(woodKey);
        game.addItem(redKey);
        game.addItem(flash);
        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);
        game.addRoom(room4);
        game.addRoom(room5);
        game.addRoom(room6);
        game.addDoor(door1, room1, room2);
        game.addDoor(door2, room2, room3);
        game.addDoor(door3, room3, room4);
        game.addDoor(door4, room4,room5);
        game.addDoor(door5, room4, room6);
        //reading commands from console
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            game.readCommands(command);
        }
    }
}
