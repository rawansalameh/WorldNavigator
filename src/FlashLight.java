public class FlashLight extends Item {

    private String name;
    private int price;
    private boolean light = false;

    public void switchLight() {
        if (isTurnedOn())
            light = false;
        else
            light = true;
    }

    public boolean isTurnedOn()
    {
        return light;
    }

    @Override
    public String getName() {return name;}

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        checkPrice(price);
        this.price = price;
    }

    @Override
    public void checkPrice(int price) {
        if (price <= 0 )
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item Name : ").append(this.name).append("\n");
        sb.append("Item Price : ").append(this.price).append("\n");
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
