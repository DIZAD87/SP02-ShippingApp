//Daniel Izadnegahdar S00840086 IT2650 Lab#4

package Scripts;
import javax.swing.JOptionPane; //Imported for pop-up windows

public class Truck
    {
            //Class properties
                private double fuelCurrent; //Fuel left in gallons
                private double fuelCapacity; //Fuel tank capacity in gallons
                private double fuelEfficiency; //Truck efficiency in miles per gallon
                private Location truckLocation; //Current location of truck
 
            //Constructor with assignable default values
                Truck(double fuelCurrent, double fuelCapacity, double mpg, Location currentLocation) 
                    {
                        this.fuelCurrent = fuelCurrent;
                        this.fuelCapacity = fuelCapacity; 
                        this.fuelEfficiency = mpg;
                        this.truckLocation = currentLocation;
                    }  

            //Methods
                //Set methods
                    public void setFuelCurrent(double numEntry)
                        { fuelCurrent = numEntry; }
                    public void setFuelCapacity(double numEntry)
                        { fuelCapacity = numEntry; }                     
                    public void setFuelEfficiency(double numEntry)
                        { fuelEfficiency = numEntry; }
                    public void setTruckLocation(Location cityEntry)
                        { truckLocation = cityEntry; }    

                //Get methods
                    public double getFuelCurrent()
                        { return fuelCurrent; }
                    public double getFuelCapacity()
                        { return fuelCapacity; }                     
                    public double getFuelEfficiency()
                        { return fuelEfficiency; }
                    public Location getTruckLocation()
                        { return truckLocation; }

                //Calculate distance to destination
                    public double getDistance(Location destination)
                        {                     
                            double distance = Math.sqrt((Math.pow((destination.getXCoor() - truckLocation.getXCoor()),2) + Math.pow((destination.getYCoor() - truckLocation.getYCoor()),2)));  
                            return distance; 
                        }

                //Calculate fuel required to destination
                    public double getFuelRequired(double distance)
                        {
                            double fuelRequired =  distance / fuelEfficiency;
                            return fuelRequired;
                        }

                //Get assessment
                    public String getDestinationAssessment(double requiredFuelEntry, boolean destinationHasFuelEntry)
                        {
                            if (requiredFuelEntry < fuelCurrent && requiredFuelEntry * 2 > fuelCurrent && destinationHasFuelEntry == true)
                                { return ("Truck has enough fuel to reach destination for a ONE-WAY TRIP ONLY but destination DOES have a gas station, Ok to move to location."); }     
                            else if (requiredFuelEntry < fuelCurrent && requiredFuelEntry * 2 > fuelCurrent && destinationHasFuelEntry == false)
                                { return ("Truck has enough fuel to reach destination for a ONE-WAY TRIP only and Destination DOES NOT have a gas station.\n Moving to location is possible but not recommended."); }
                            else if (requiredFuelEntry < fuelCurrent && requiredFuelEntry * 2 < fuelCurrent)
                                { return ("Truck has enough fuel to reach destination for a ROUND trip, OK to move to location."); }    
                            else
                                { return ("Truck does not have enough fuel to reach either a ONE-WAY or ROUND trip and will not be able to move to location."); }   
                        }

                //MoveAndRefuel
                    public void moveAndRefuel(double requiredFuelEntry, Location destination)
                        {
                            if (requiredFuelEntry < fuelCurrent)
                                {
                                    //Move to destination
                                        truckLocation = destination; //Update the destination
                                        fuelCurrent -= requiredFuelEntry; //Deduct amount of gas consumed

                                    //Refuel
                                        if (destination.getHasFuel() == true)
                                            { fuelCurrent = fuelCapacity; }
                                        else
                                            { JOptionPane.showMessageDialog(null, "FYI destination does not have gas station so did not refuel."); }   
                                }
                            else
                                { JOptionPane.showMessageDialog(null, "There is not enough fuel to reach the destination so will not move to destination."); }    
                        }  
    }
