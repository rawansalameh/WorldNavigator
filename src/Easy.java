import java.io.IOException;
import java.util.Scanner;

public class Easy {
    public static void main(String[] args) throws IOException {

        //creating key instances and flash light to be used during the game
        Key redKey = new Key();
        redKey.setName("redKey");
        redKey.setPrice(80);

        Key blueKey= new Key();
        blueKey.setName("blueKey");
        blueKey.setPrice(95);

        Key yellowKey = new Key();
        yellowKey.setName("yellowKey");
        yellowKey.setPrice(90);

        Key greenKey = new Key();
        greenKey.setName("greenKey");
        greenKey.setPrice(97);

        Key whiteKey = new Key();
        whiteKey.setName("whiteKey");
        whiteKey.setPrice(200);

        FlashLight superFlash = new FlashLight();
        superFlash.setName("SuperFlash");
        superFlash.setPrice(150);


        //creating walls of different types and giving them some attributes
        PlainWall plainWall1 = new PlainWall();
        PlainWall plainWall2 = new PlainWall();

        Mirror mirror1 = new Mirror();
        mirror1.setHiddenKey(greenKey);

        //painting without hidden key
        Painting painting1 = new Painting();

        Chest chest1 = new Chest();
        chest1.lock();
        chest1.setKey(greenKey);
        chest1.addItem(redKey);

        Chest chest2 = new Chest();
        chest2.setKey(yellowKey);

        Seller seller1 = new Seller();
        seller1.addItem(blueKey);
        seller1.addItem(whiteKey);
        seller1.addItem(superFlash);

        Door door1 = new Door();
        door1.setKey(blueKey);

        Door door2 = new Door();
        door2.lock();
        door2.setKey(redKey);

        Door door3 = new Door();
        door3.lock();
        door3.setKey(whiteKey);

       //creating game rooms
        Room room1 = new Room();
        room1.addLights();

        room1.turnLightButton();
        room1.setNorthWall(mirror1);
        room1.setWestWall(painting1);
        room1.setSouthWall(door2);
        room1.setEastWall(door1);

        Room room2 = new Room();
        room2.addLights();
        room2.setNorthWall(plainWall2);
        room2.setWestWall(door1);
        room2.setSouthWall(chest1);
        room2.setEastWall(plainWall1);

        Room room3 = new Room();
        room3.addLights();
        room3.turnLightButton();
        room3.setNorthWall(door2);
        room3.setWestWall(door3);
        room3.setSouthWall(seller1);
        room3.setEastWall(chest2);

        Room room4 = new Room();
        room4.addAsExitPoint();

        //crating game
        Game game = new Game();
        game.addItem(redKey);
        game.addItem(blueKey);
        game.addItem(greenKey);
        game.addItem(whiteKey);
        game.addItem(superFlash);
        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);
        game.addRoom(room4);
        game.addDoor(door1, room1, room2);
        game.addDoor(door2, room1, room3);
        game.addDoor(door3, room3, room4);

        //reading commands from console
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            game.readCommands(command);
        }
    }
    }

