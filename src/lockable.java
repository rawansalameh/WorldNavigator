public interface lockable {

    public Item getKey();

    public void setKey(Item doorKey);

    public void lock();

    public boolean isLocked();

    public void open();

    public void close();

    public boolean isValidKey(Key key);

    public void UseKey(Key key);

}
