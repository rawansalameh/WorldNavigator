import java.util.*;

public class Player  {

    private int gold;
    private List<Item> playerItems = new ArrayList<Item>();

    public Player ()
    {
        this.gold = 300;
    }

    public int getGold ()
    {
        return gold;
    }

    public void increaseGoldAmount(int amount)
    {
        gold = gold + amount;
    }

    public void decreaseGoldAmount(int amount)
    {
        gold = gold - amount;
    }

    public void addItem(Item item)
    {
        playerItems.add(item);
    }

    public void removeItem(Item item)
    {
        playerItems.remove(item);
    }

    public boolean hasItem(Item item) {
        if( playerItems.contains(item))
        { return true;}
        else
        {return false;}
    }

    public void printAllItems() {
       if (playerItems.size()< 1) {
           System.out.println("None");
       }
      else
      {
          for (int i =0; i<playerItems.size(); i++)
          {
              System.out.print(playerItems.get(i).getName() + " ");
          }
          System.out.println();
      }
  }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gold Amount ").append(this.gold).append("\n");
        sb.append("Items : ");
        for (Item item : playerItems)
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
