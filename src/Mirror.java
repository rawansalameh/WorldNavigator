public class Mirror extends Wall implements hasHiddenKey{

    private final String name = "Mirror";
    private Item hiddenKey ;

    @Override
    public String getName() {return name;}

    @Override
    public  void display() {
        System.out.println(name + " --> try \"CHECK\"  command");
    }

    @Override
    public Item getHiddenKey() {
        return hiddenKey;
    }

    @Override
    public void setHiddenKey(Item hiddenKey){
        this.hiddenKey = hiddenKey;
    }

    @Override
    public boolean hasHiddenKey() {
        if (hiddenKey == null) {
            return false;
        } else
            return true;
    }

    @Override
    public String toString() {
        return "Wall Type : " + this.name;
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
