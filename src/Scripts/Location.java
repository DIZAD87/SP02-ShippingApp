//Daniel Izadnegahdar S00840086 IT2650 Lab#4

package Scripts;
public class Location
    {
        //Class properties
               private String name; //Name of city location
               private double xCoor; //X coordinate of location
               private double yCoor; //Y coordinate of location
               private boolean hasFuel; //Whether tank has fuel or not
               
        //Constructors
           //No parameter constructor, used for simplicity when instantiating
               Location()
                    {
                        this.name = "";
                        this.xCoor = 0.0;
                        this.yCoor = 0.0;
                        this.hasFuel = false;
                    }
               
           //Constructor with assignable values
               Location(String name, double xCoor, double yCoor, boolean hasFuel)
                   {
                       this.name = name;
                       this.xCoor = xCoor;
                       this.yCoor = yCoor;
                       this.hasFuel = hasFuel;
                   }

           //Methods
               //Get methods
                   public String getName()
                       { return name; }
                   public double getXCoor()
                       { return xCoor; }
                   public double getYCoor()
                       {  return yCoor; }
                   public boolean getHasFuel()
                       { return hasFuel; } 

               //Set methods
                   public void setName(String numEntry)
                       { name = numEntry; }
                   public void setXCoor(double numEntry)
                       { xCoor = numEntry; }                     
                   public void setYCoor(double numEntry)
                       { yCoor = numEntry; }
                   public void setHasFuel(boolean boolEntry)
                       { hasFuel = boolEntry; }

               //Deep copy method
                   public Location deepCopy()
                        {
                            Location clone = new Location(name, xCoor, yCoor, hasFuel);
                            return clone;
                        }
    }
