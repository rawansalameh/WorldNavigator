public class PlainWall extends Wall{

    private final String name = "Plain Wall";

    @Override
    public String getName() {return name;}

    @Override
    public  void display() {
        System.out.println(name + " --> try \"TURNLEFT - TURNRIGHT\" commands");
    }

    @Override
    public String toString() {
        return "Wall Type :  " + this.name;
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
