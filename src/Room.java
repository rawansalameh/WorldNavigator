
public class Room implements  Cloneable{

    private Wall northWall;
    private Wall westWall;
    private Wall southWall;
    private Wall eastWall;

    private boolean flashLightened = false;
    private boolean lights = false;
    private boolean lightButton = false;
    private boolean exitPoint = false;

    public Wall getNorthWall()
    {
        return northWall;
    }

    public void setNorthWall(Wall northWall) {
       this.northWall = northWall;
   }

    public Wall getWestWall()
    {
        return westWall;
    }

    public void setWestWall(Wall westWall)
    {
        this.westWall = westWall;
    }

    public Wall getSouthWall()
    {
        return southWall;
    }

    public void setSouthWall(Wall southWall)
    {
        this.southWall = southWall;
    }

    public Wall getEastWall()
    {
        return eastWall;
    }

    public void setEastWall(Wall eastWall)
    {
        this.eastWall = eastWall;
    }

    public void turnOnFlashlight()
    {
        flashLightened = true;
    }

    public boolean lightedByFlashLight()
    {
        return flashLightened;
    }

    public boolean isLit() {

            if (lightsOn() || lightedByFlashLight()){
                return true;
            }
           else
               return false;

    }

    public void switchLights() {
        if (hasLights())
        {
            if(lightsOn()){
                lightButton = false;
                System.out.println("Lights turned OFF");
            }
            else
            { lightButton = true;
                System.out.println("Lights turned ON");
            }
        }
        else
        {
            if (lightedByFlashLight())
            { System.out.println("Room is already lit by FLAHSLIGHT");}
            else
                System.out.println("No lights in the room, you should use a FLASHLIGHT");
        }

    }

    public void turnLightButton() {
        if (hasLights())
            lightButton = true;

    }

    public boolean lightsOn() {
        if (hasLights())
        return lightButton == true;
        else
            return flashLightened == true;
    }

    public boolean hasLights() {

        return lights;
    }

    public void addLights ()
    {
        lights = true;
    }

    public void addAsExitPoint(){
        exitPoint = true;
    }

    public boolean isExitPoint ()
    {
        return exitPoint;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("North Wall : ").append(this.northWall.getName()).append("\n");
        sb.append("West Wall : ").append(this.westWall.getName()).append("\n");
        sb.append("South Wall : ").append(this.southWall.getName()).append("\n");
        sb.append("East Wall : ").append(this.eastWall.getName()).append("\n");
        sb.append("Is Lit ? ").append(this.isLit()).append("\n");
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
