import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Seller extends Wall implements hasItems{

    private final String name = "Seller";
    private List<Item> sellerItems = new ArrayList<Item>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public  void display() {
        System.out.println(name + " --> try \"TRADE - LIST - BUY ITEMNAME - SELL ITEMNAME\"  commands");
    }

    @Override
    public List<Item> getItems() {
        return sellerItems;
    }

    @Override
    public void addItem(Item item) {
        sellerItems.add(item);
    }

    public void listItems() {
        if (sellerItems.size() > 0) {
            System.out.println(" Item         Price");
            for (Item item : sellerItems) {
                System.out.println( item.getName() + "   : " + item.getPrice());
            }
        }
        else {
            System.out.println("no items found");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wall type : ").append(this.name).append("\n");
        sb.append("Items : ");
        for (Item item : sellerItems)
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
