
import java.util.*;

public class Game {

    private Room currentRoom;
    private Wall currentWall;
    private List<Room> rooms= new ArrayList<Room>();
    private HashMap<Door,Room> nextRoom = new HashMap<Door,Room>();
    private HashMap<Door,Room> previousRoom = new HashMap<Door,Room>();
    private List<Item> gameItems = new ArrayList<Item>();
    private Player player = new Player();
    private int navigation;
    private String direction;
    private boolean tradeMode = false;
    private long startTime;
    private long endTime;

    public Game (){
        startTime = System.currentTimeMillis();
        endTime = startTime + 900000;
        System.out.println("Welcome To World Navigator Game");
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    private void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void addRoom (Room room) {
        rooms.add(room);
        setCurrentRoom(rooms.get(0));
        setCurrentWall(currentRoom.getNorthWall());
    }

    public Wall getCurrentWall() {

        if (getDirection() == "North")
        {
            currentWall = currentRoom.getNorthWall();
        }
        else if (getDirection() == "West")
        {
            currentWall = currentRoom.getWestWall();
        }
        else if (getDirection() == "South")
        {
            currentWall = currentRoom.getSouthWall();
        }
        else if (getDirection() == "East")
        {
            currentWall = currentRoom.getEastWall();
        }

        return currentWall;
    }

    private void setCurrentWall(Wall currentWall){
        this.currentWall = currentWall;
    }

    public int getNavigation() {
        return navigation;
    }

    public String getDirection() {
        if (getNavigation() == 0) {direction = "North";}
        else if (getNavigation() == 1) {direction = "West";}
        else if (getNavigation() == 2) {direction = "South";}
        else if (getNavigation() == 3) {direction = "East";}
        return direction;
    }

    public ArrayList<Item> getAllItems() {
        return (ArrayList<Item>) gameItems;
    }

    public void addItem(Item item){
        gameItems.add(item);
    }

    //to add a door between two rooms
    public void addDoor(Door door , Room room1 , Room room2) {
        previousRoom.put(door , room1);
        nextRoom.put(door , room2);
    }

    public boolean timeOut() {
        boolean flag;
        if(System.currentTimeMillis() > endTime)
        {flag = true;}
        else
            flag = false;
        return flag;
    }

    public void printTimeOutMessage()
    {
        System.out.println("Time out! Game Over");
    }

    public void getRemainingTime() {
            //to return remaining time in minutes as long integer (without floating point)
            long remainingMinutes = (endTime - System.currentTimeMillis()) / 60000;
            double minuteAfterPoint = (((double) endTime - (double) System.currentTimeMillis()) / 60000) - remainingMinutes;
            //to return value of converting minutes to seconds
            double secondsAfterPoint = minuteAfterPoint * 60;
            //to return remaining seconds (as integer)
            int remainingSeconds = (int) secondsAfterPoint;
            System.out.println("game ends within " + remainingMinutes + ":" + remainingSeconds + " minutes");
    }

    public void printDirection(){
        System.out.println(getDirection());
    }

    public void turnLeft() {
        if (getNavigation() == 3) {
            navigation = 0;
        } else {
            navigation++;
        }
    }

    public void turnRight() {
        if (getNavigation() == 0) {
            navigation = 3;
        } else {
            navigation--;
        }
    }

    public void moveForward() {
        if(getCurrentWall() instanceof  Door) {
            if (((Door) getCurrentWall()).isLocked()) {
                System.out.println("Door is locked, " + ((Door) getCurrentWall()).getKey().getName()
                        + " is needed to unlock");
            }
            else {
                if (getCurrentRoom() == nextRoom.get(getCurrentWall())) {
                    currentRoom = previousRoom.get(getCurrentWall());
                }
                else {
                    currentRoom = nextRoom.get(getCurrentWall());
                }
                System.out.println("Successfully moved to adjacent room");

                if (getCurrentRoom().isExitPoint()) {
                    System.out.println("Congratulations! you won");
                    System.exit(0);
                }
            }
        }
        else
        {
            System.out.println("No doors to go through");
        }
    }

    public void moveBackward() {

        turnLeft();
        turnLeft();
        if (getCurrentWall() instanceof Door) {
            moveForward();
        }
        else
        {
            turnRight();
            turnRight();
            System.out.println("No doors to go through");
        }
    }

    public void playerStatus() {
        System.out.print("Direction : " + getDirection() + "\n" + "Gold : "
                           + player.getGold() + "\n" + "Items : " );
        player.printAllItems();
    }

    public void look () {
        if (currentRoom.isLit())
       getCurrentWall().display();
        else
          System.out.println("Room is dark , try \"SWITCHLIGHTS\" command OR  \"USE FLASHLIGHT FLASHLIGHTNAME\" command");
    }

    public void check() {
        if (getCurrentRoom().isLit()) {
            if (getCurrentWall() instanceof Mirror) {
                if (((Mirror) currentWall).hasHiddenKey()) {
                    System.out.println(((Mirror) currentWall).getHiddenKey().getName() + " was acquired.");
                    player.addItem(((Mirror) currentWall).getHiddenKey());
                    ((Mirror) currentWall).setHiddenKey(null);
                } else {
                    System.out.println("No keys found");
                }
            } else if (getCurrentWall() instanceof Painting) {
                if (((Painting) currentWall).hasHiddenKey()) {
                    System.out.println (((Painting) currentWall).getHiddenKey().getName() + " was acquired.");
                    player.addItem(((Painting) currentWall).getHiddenKey());
                    ((Painting) currentWall).setHiddenKey(null);
                } else {
                    System.out.println ("No keys found");
                }
            } else if (getCurrentWall() instanceof Chest) {
                if (((Chest) currentWall).isLocked()) {
                    System.out.println("Chest is locked, " + ((Chest) currentWall).getKey().getName()
                        + " is needed to unlock");
                } else {
                    if (((Chest) currentWall).getItems().size() > 0) {
                        for (Item item : ((Chest) currentWall).getItems()) {
                            player.addItem(item);
                            System.out.print( item.getName() + ", ");
                        }
                        System.out.println(" was acquired!");
                        ((Chest) currentWall).getItems().clear();
                    } else {
                        System.out.println("No items in the Chest");
                    }
                }
            } else if (getCurrentWall() instanceof Door) {
                if (((Door) currentWall).isLocked()) {
                    System.out.println( "Door is locked, " + ((Door) currentWall).getKey().getName()
                            + " is needed to unlock");
                } else {
                    System.out.println( "Door is open");
                }
            }
            else {
                System.out.println("check command is not valid here!");}
        }
        else
        System.out.println("Room is dark , try \"SWITCHLIGHTS\" command  OR \"USE FLASHLIGHT FLASHLIGHTNAME\" command");

    }

    public void open() {
       if(getCurrentWall() instanceof Door) {
           if (((Door) currentWall).isLocked()) {
              System.out.println("Door is locked, " +((Door) currentWall).getKey().getName()
                      + " is needed to unlock");
           }
           else {
              System.out.println("door opened use FORWARD command to move to adjacent room");
           }
       }
       else {
           System.out.println("OPEN Command is not valid, you are facing a " + getCurrentWall().getName());}
    }

    public boolean isTradeMode () {return tradeMode;}

    public void trade(){
        if (currentWall instanceof Seller)
        {
            tradeMode = true;
            System.out.println("Trade Started");
            ((Seller) currentWall).listItems();
        }
        else
        {
            System.out.println("TRADE command is only valid if you were facing a Seller");
        }
    }

    public void list(){
        if (currentWall instanceof Seller)
        {
            if (tradeMode)
            ((Seller) currentWall).listItems();
            else
            {System.out.println("You are not in the trade mode");}
        }
        else
        {
            System.out.println("LIST command is only valid if you were facing a Seller");
        }
    }

    public void buy(Item item) {
        if (getAllItems().contains(item)) {
            if (getCurrentWall() instanceof Seller) {
                if (((Seller) currentWall).getItems().contains(item)) {
                    if (player.getGold() >= item.getPrice()) {
                        player.addItem(item);
                        player.decreaseGoldAmount(item.getPrice());
                        ((Seller) currentWall).getItems().remove(item);
                        System.out.println("Successfully bought : " + item.getName());
                    } else {
                        System.out.println("You don't have enough gold");
                    }
                } else {
                    System.out.println("Seller does not have " + item.getName());
                }
            } else {
                System.out.println("BUY command is only valid if you were facing a seller");
            }
        }
        else {System.out.println("No such item, please enter existing item name");}
        }

    public void sell(Item item){
        if (getAllItems().contains(item)) {
            if (getCurrentWall() instanceof Seller)
        {
            if(player.hasItem(item)) {
                player.removeItem(item);
                player.increaseGoldAmount(item.getPrice());
                ((Seller) currentWall).addItem(item);
                System.out.println("Successfully sold : " + item.getName());
            }
            else
            {
                System.out.println("Sorry! you don't have the item : " +item.getName());
            }
        }
        else
        {
            System.out.println("SELL command is only valid if you were facing a seller");
        }
        }
        else {System.out.println("No such item, please enter existing item name");}
    }

    public void finishTrade() {
        if (getCurrentWall() instanceof Seller) {
            if (isTradeMode()) {
                System.out.println("Trade Finished");
                tradeMode = false;
            }
            else
            {System.out.println("You are not in the trade mode");}
        }
        else
        {
            System.out.println("FINISH TRADE command is only valid if you were facing a seller");

        }
    }

    public void useFlashLight(FlashLight flashLight){

        if (getAllItems().contains(flashLight)) {
            if (player.hasItem(flashLight)) {
            flashLight.switchLight();
            if (flashLight.isTurnedOn()) {
                if (!currentRoom.isLit()) {
                    currentRoom.turnOnFlashlight();
                    System.out.println("Room is Lit now!");
                }
                else {System.out.println("Room is already lit");}
            }
            else
            {
                    System.out.println("FLASHLIGHT turned OFF!");
            }
        }
        else {
            System.out.println("You don't have the flash light " + flashLight.getName() + " try to find one");
        }
        }
        else {System.out.println("No such flashlight, please enter existing flashlight name");}
    }

    public void useKey(Key key) {
        if (getAllItems().contains(key)) {

            if (player.hasItem(key))
        {
        if (getCurrentWall() instanceof Door)
        {
            ((Door) currentWall).UseKey(key);
        }
        else if (getCurrentWall() instanceof Chest)
        {
            ((Chest) currentWall).UseKey(key);
        }
        else
          {
            System.out.println("No need to use key you are facing : " +currentWall.getName());
          }
        }
        else
        {
              System.out.println("You don't have " + key.getName() +" try to find it somewhere");
        }
        }
        else {System.out.println("No such key, please enter existing key name");}
    }

    public void switchLights() {
        getCurrentRoom().switchLights();
    }

    public void quit(){
        System.out.println("Game Over");
        System.exit(0);
    }

  public void readCommands(String command) {
      if (timeOut()) {
          printTimeOutMessage();
          System.exit(0);
      }
      String upperCasedCommand = command.toUpperCase();
      if (upperCasedCommand.startsWith("USE")) {
          if (upperCasedCommand.contains(" ")) {
              boolean found = false;
              for (Item item : getAllItems()) {
                  if ((upperCasedCommand.substring(upperCasedCommand.lastIndexOf(' '))
                  ).contains(item.getName().toUpperCase().replace(" ", ""))) {
                      found = true;
                      if (item instanceof Key) {
                          useKey((Key) item);
                      } else if (item instanceof FlashLight) {
                          useFlashLight((FlashLight) item);
                      }
                  }
              }
              if (found == false) {
                  System.out.println("wrong Item name please enter valid name");
              }
          } else {
              System.out.println("Please make a space before Item Name");
          }
      }
      else if (upperCasedCommand.startsWith("BUY") || upperCasedCommand.startsWith("SELL"))
      {
          if (upperCasedCommand.contains(" ")) {
              boolean found = false;
              for (Item item : getAllItems())
              {
                  if ((upperCasedCommand.substring(upperCasedCommand.lastIndexOf(' '))).contains(item.getName().toUpperCase().replace(" ", ""))) {
                      found = true;
                      if (upperCasedCommand.startsWith("BUY"))
                      {   buy(item);}
                      else   if (upperCasedCommand.startsWith("SELL"))
                      {sell(item);}
                  }
              }
              if (found == false) {
                  System.out.println("wrong Item name please enter valid name");
              }
          }
          else {
              System.out.println("Please make a space before Item Name");
          }
      }
      else {
          switch (upperCasedCommand.replace(" ", "")) {
              case "PLAYERSTATUS":
                  playerStatus();
                  break;

              case "LOOK":
                  look();
                  break;

              case "CHECK":
                  check();
                  break;

              case "SWITCHLIGHTS":
                  switchLights();
                  break;

              case "SWITCHLIGHT":
                  switchLights();
                  break;

              case "TIME":
                  getRemainingTime();
                  break;

              case "TURNLEFT":
                  turnLeft();
                  look();
                  break;

              case "TURNRIGHT":
                  turnRight();
                  look();
                  break;

              case "FORWARD":
                  moveForward();
                  break;

              case "BACKWARD":
                  moveBackward();
                  break;

              case "DIRECTION":
                  printDirection();
                  break;

              case "OPEN":
                  open();
                  break;

              case "TRADE":
                  trade();
                  break;

              case "LIST":
                  list();
                  break;

              case "FINISHTRADE":
                  finishTrade();
                  break;

              case "QUIT":
                  quit();
                  break;

              default:
                  System.out.println("Invalid command");
          }

      }
  }

}
