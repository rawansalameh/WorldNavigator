import java.util.ArrayList;
import java.util.List;

public class Chest extends Wall implements lockable , hasItems{

    private final String name = "Chest";
    private boolean lockStatus = false;
    private Item chestKey;
    private List<Item> chestItems = new ArrayList<Item>();

    @Override
    public String getName() {return name;}

    @Override
    public  void display() {
        System.out.println(name + " --> try \"CHECK\" command || try \"USE KEYNAME  (KEY NAME)\" (to open/close the chest)");

    }

    @Override
    public Item getKey() {
        return chestKey;
    }

    @Override
    public void setKey(Item chestKey ) {
        this.chestKey = chestKey;
    }

    @Override
    public List<Item> getItems() {return chestItems;}

    @Override
    public void addItem(Item item) { chestItems.add(item);}

    @Override
    public void lock( ){
        lockStatus = true;
    }

    @Override
    public boolean isLocked()
    {
        return lockStatus;
    }

    @Override
    public void open(){lockStatus = false; }

    @Override
    public void close(){lockStatus = true;}

    @Override
    public boolean isValidKey(Key key ) {
        try {
            if (key.getName().equals(chestKey.getName())) {
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
        if (isValidKey(key))
        {
            if (isLocked())
            {
                open();
                System.out.println("Chest opened");
            }
            else
            {
                close();
                System.out.println("Chest closed");
            }
        }
        else
        {System.out.println("Wrong key! required key is : "+ getKey().getName());}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wall type : ").append(this.name).append("\n");
        sb.append("Key : ").append(this.chestKey.getName()).append("\n");
        sb.append("lock status : ").append(this.lockStatus).append("\n");
        sb.append("Items : ");
        for (Item item : chestItems)
            sb.append(item).append("\n");
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
