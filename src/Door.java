import javafx.scene.chart.ScatterChart;

public class Door extends Wall implements lockable{

    private final String name = "Door";
    private boolean lockStatus = false;
    private Item doorKey;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void display() {
        System.out.println(name + " --> try \"CHECK - OPEN\" commands || try \"USE KEYNAME (KEY NAME)\" (to open/close the door)");
    }

    @Override
    public Item getKey() {

        return doorKey;
    }

    @Override
    public void setKey(Item doorKey){
        this.doorKey = doorKey;
    }

    @Override
    public void lock()
{
    lockStatus = true;
}

    @Override
    public boolean isLocked()
    {
       return lockStatus;
    }

    @Override
    public void open(){lockStatus = false;}

    @Override
    public void close(){lockStatus = true;}

    @Override
    public boolean isValidKey(Key key ) {
        try {
            if (key.getName().equals(doorKey.getName())) {
                return true;
            } else {
                return false;
            }
          } catch (Exception e) {
            System.out.println("Key value is null , setKey method isn't used yet");
            throw new NullPointerException();
        }
    }

    @Override
    public void UseKey(Key key) {
            if (isValidKey(key)) {
                if (isLocked()) {
                     open();
                    System.out.println("Door opened use FORWARD command to move to adjacent room");
                }
                else {
                     close();
                     System.out.println("Door closed");
                     }
            }
            else {
                System.out.println("Wrong key! required key is : " + getKey().getName());
                 }
          }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wall type : ").append(this.name).append("\n");
        sb.append("Key : ").append(this.doorKey.getName()).append("\n");
        sb.append("lock status : ").append(this.lockStatus).append("\n");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
