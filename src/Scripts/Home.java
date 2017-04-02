//Daniel Izadnegahdar S00840086 IT2650 Lab#4

//Imported libraries
    package Scripts;
    import java.util.ArrayList;
    import javafx.application.Application;
    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.Label;
    import javafx.scene.control.RadioButton;
    import javafx.scene.control.TextField;
    import javafx.scene.control.ToggleGroup;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.text.Font;
    import javafx.stage.Stage; 
    import javax.swing.JOptionPane;
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.logging.Level;
    import java.util.logging.Logger;

//Main program    
public class Home extends Application
    {    
        //Declare instance of array list
            ArrayList<Location> locationList = new ArrayList();
             
        //Create instance of Truck
            Truck userTruck = new Truck(0.0, 0.0, 0.0, new Location());
             
        //This variable is to avoid re-generating the default locations on the list
            int swapCount = 0;
            double capacityEntry;
            double efficiencyEntry;
            
        //Stage
            Stage swapStage;

//************************************startScene********************************             
        //Start main function   
        @Override
        public void start(Stage primaryStage)
            {   
                swapStage = primaryStage;
                  
            //Read the Locations.csv file, parse it, and insert it inside an internal ArrayList
                BufferedReader locationExternalFile; //Define the variable name for storing the .csv
                try
                    {
                    //Define variables for the parsing process
                        String singleLine; //This will store one line of the .csv file
                        String[] singleLineSplit; //This array will store the split items of singleLine
                        locationExternalFile = new BufferedReader(new FileReader("src/dataSets/Locations.csv")); //Define the .csv 
                    //Parse the file line by line and store it inside the ArrayList
                    //Assign line to singleLine, and read until there are no more lines to read
                        while ((singleLine = locationExternalFile.readLine()) != null) 
                            {
                            //Variable declarations for storing the split items
                                String name;
                                double xCoor, yCoor;
                                boolean hasFuel;
                            //Split the singleLine per each comma
                                singleLineSplit = singleLine.split(",");
                            //Assign the location variables
                                name = singleLineSplit[0]; //Assign the name
                                xCoor = Double.parseDouble(singleLineSplit[1]); //Assign the x-coordinate
                                yCoor = Double.parseDouble(singleLineSplit[2]); //Assign the y-coordinate
                                hasFuel = Boolean.parseBoolean(singleLineSplit[3]); //Assign the hasFuel
                            //Add the location object with these new variables to the ArrayList
                                if (swapCount < 1)//This if statement is just to avoid rebuilding the list after a scene swap
                                    {locationList.add(new Location(name, xCoor, yCoor, hasFuel));}
                            }
                    //After elements have been extracted, close the external file
                        locationExternalFile.close();
                    }
                catch(IOException ex)
                    {
                         Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                //PART#1: Instantiate the GUI objects
                    //Panes
                        BorderPane borderPane1 = new BorderPane(); //Border pane
                        AnchorPane anchorPaneLeft = new AnchorPane(); //Left anchor pane
                        AnchorPane anchorPaneCenter = new AnchorPane(); //Center anchor pane
                        AnchorPane anchorPaneRight = new AnchorPane(); //Right anchor pane
                        HBox hBoxPane1 = new HBox(); //Upper banner pane   
                    //Labels
                        Label labelTitle = new Label();
                        Label labelDefineTruck = new Label();
                        Label labelFuelCapacity = new Label();
                        Label labelFuelEfficiency = new Label();
                        Label labelCurrentTruckStatus = new Label();
                        Label labelCurrentFuelAvailable = new Label();
                        Label labelCurrentLocation = new Label();
                        Label labelSelectDestination = new Label();
                        Label labelModifyLocationListing = new Label();
                        Label labelDestinationHasFuel = new Label();
                        Label labelDistanceToLocation = new Label();
                        Label labelFuelRequiredToLocation = new Label();
                        Label labelDestinationAssessment = new Label();
                    //Buttons
                        Button buttonDefineTruck = new Button();
                        Button assessDestination = new Button();
                        Button buttonAddLocation = new Button();
                        Button buttonGoToLocation = new Button();
                        Button buttonRemoveLocation = new Button();
                        Button buttonExportList = new Button();
                    //Text fields
                        TextField txtBoxFuelCapacity = new TextField();
                        TextField txtBoxFuelEfficiency = new TextField();
                        TextField txtBoxCurrentFuelAvailable = new TextField();
                        TextField txtBoxCurrentLocation = new TextField();
                        TextField txtBoxDestinationHasFuel = new TextField();
                        TextField txtBoxDistanceToLocation = new TextField();
                        TextField txtBoxFuelRequiredToLocation = new TextField();
                        TextField txtBoxDestinationAssessment = new TextField();
                    //Combo boxes
                        ComboBox comboBox1 = new ComboBox();

                //PART#2: Attach the objects to their pane
                    //Panes
                        borderPane1.setTop(hBoxPane1);
                        borderPane1.setCenter(anchorPaneCenter);
                        borderPane1.setRight(anchorPaneRight); 
                        borderPane1.setLeft(anchorPaneLeft);
                    //Labels
                        //Hbox
                            hBoxPane1.getChildren().add(labelTitle);
                        //LeftAnchorPane
                            anchorPaneLeft.getChildren().add(labelDefineTruck);
                            anchorPaneLeft.getChildren().add(labelFuelCapacity);
                            anchorPaneLeft.getChildren().add(labelFuelEfficiency);
                            anchorPaneLeft.getChildren().add(labelCurrentTruckStatus);
                            anchorPaneLeft.getChildren().add(labelCurrentFuelAvailable);
                            anchorPaneLeft.getChildren().add(labelCurrentLocation);
                        //CenterAnchorPane
                            anchorPaneCenter.getChildren().add(labelSelectDestination);
                            anchorPaneCenter.getChildren().add(labelModifyLocationListing);
                        //RightAnchorPane
                           anchorPaneRight.getChildren().add(labelDestinationHasFuel);
                           anchorPaneRight.getChildren().add(labelDistanceToLocation);
                           anchorPaneRight.getChildren().add(labelFuelRequiredToLocation);
                           anchorPaneRight.getChildren().add(labelDestinationAssessment);  
                    //Buttons
                        //CenterAnchorPane 
                            anchorPaneCenter.getChildren().add(buttonAddLocation);
                            anchorPaneCenter.getChildren().add(buttonRemoveLocation);
                            anchorPaneCenter.getChildren().add(assessDestination);
                            anchorPaneCenter.getChildren().add(buttonExportList);
                        //RightAnchorPane
                            anchorPaneRight.getChildren().add(buttonGoToLocation);
                        //LeftAnchorPane    
                            anchorPaneLeft.getChildren().add(buttonDefineTruck);
                    //text fields
                        //LeftAnchorPane
                           anchorPaneLeft.getChildren().add(txtBoxFuelCapacity);
                           anchorPaneLeft.getChildren().add(txtBoxFuelEfficiency);
                           anchorPaneLeft.getChildren().add(txtBoxCurrentFuelAvailable);
                           anchorPaneLeft.getChildren().add(txtBoxCurrentLocation);
                        //RightAnchorPane
                           anchorPaneRight.getChildren().add(txtBoxDestinationHasFuel);
                           anchorPaneRight.getChildren().add(txtBoxDistanceToLocation);
                           anchorPaneRight.getChildren().add(txtBoxFuelRequiredToLocation);
                           anchorPaneRight.getChildren().add(txtBoxDestinationAssessment);
                    //Combo boxes
                        //CenterAnchorPane   
                            anchorPaneCenter.getChildren().add(comboBox1);
   
                //PART#3: Define the location/style attributes
                    //Define styles
                        //Panes
                            anchorPaneCenter.setStyle("-fx-border-color: black");
                            anchorPaneRight.setStyle("-fx-border-color: black");
                            anchorPaneLeft.setStyle("-fx-border-color: black");
                            hBoxPane1.setStyle("-fx-border-color: black");
                        //Labels                  
                            labelTitle.setText("LAB#2: Shipping Program (DIZAD 03/31/17)");
                            labelDefineTruck.setText("Define Truck:");
                            labelFuelCapacity.setText("Set Fuel Capacity\n(Gallons):");
                            labelFuelEfficiency.setText("Set Fuel Efficiency\n(GPM):");
                            labelCurrentTruckStatus.setText("Current Truck Status:");
                            labelCurrentFuelAvailable.setText("Current Fuel Available(Gallons):");
                            labelCurrentLocation.setText("Current Location(City):");
                            labelSelectDestination.setText("Select Destination(City):");
                            labelModifyLocationListing.setText("Modify Location Listing:");
                            labelDistanceToLocation.setText("Distance to Location(Miles):");
                            labelFuelRequiredToLocation.setText("Fuel Required(Gallons):");
                            labelDestinationAssessment.setText("Destination Assessment:");                     
                            labelDestinationHasFuel.setText("Does destination\n have a gas station?");
                        //Fonts       
                            labelTitle.setFont(Font.font ("Tahoma", 15));

                        //Buttons
                            //Text
                                buttonDefineTruck.setText("Define Truck"); 
                                buttonAddLocation.setText("Add Location Settings:");
                                buttonRemoveLocation.setText("Remove Location"); 
                                buttonGoToLocation.setText("Go to Location & Refuel"); 
                                assessDestination.setText("Assess Destination");
                                buttonExportList.setText("Export List to Excel File");
                            
                            //Set width
                                buttonDefineTruck.setPrefWidth(150);
                                buttonAddLocation.setPrefWidth(150);
                                buttonRemoveLocation.setPrefWidth(150);
                                buttonGoToLocation.setPrefWidth(150);
                                assessDestination.setPrefWidth(150);
                                buttonExportList.setPrefWidth(150);
                                
                            //Colors
                               buttonExportList.setStyle(" -fx-text-fill: green;  -fx-font-weight: bold;");
                      
                        //Text fields
                            //Disable the text fields that are used for read-only
                                txtBoxCurrentFuelAvailable.setDisable(true);
                                txtBoxCurrentLocation.setDisable(true);
                                txtBoxDestinationHasFuel.setDisable(true);
                                txtBoxDistanceToLocation.setDisable(true);
                                txtBoxFuelRequiredToLocation.setDisable(true);
                                txtBoxDestinationAssessment.setDisable(true);
                                
                            //Set width
                                txtBoxFuelCapacity.setPrefWidth(150);
                                txtBoxFuelEfficiency.setPrefWidth(150);
                                txtBoxCurrentFuelAvailable.setPrefWidth(150);
                                txtBoxCurrentLocation.setPrefWidth(150);
                                txtBoxDestinationHasFuel.setPrefWidth(150);
                                txtBoxDistanceToLocation.setPrefWidth(150);
                                txtBoxFuelRequiredToLocation.setPrefWidth(150);
                                txtBoxDestinationAssessment.setPrefWidth(150);

                        //Combo boxes
                            //Set Width
                                comboBox1.setPrefWidth(150);
                            //Fill in the comboBox
                                for (int i01 = 0; i01 < locationList.size(); i01++)
                                 { comboBox1.getItems().addAll(locationList.get(i01).getName()); } 
                                  
                    //Define locations
                        //LeftAnchorPane
                            AnchorPane.setTopAnchor(labelDefineTruck, 10.0); //Label
                                AnchorPane.setLeftAnchor(labelDefineTruck, 10.0); 
                                AnchorPane.setRightAnchor(labelDefineTruck, 10.0); 
                            AnchorPane.setTopAnchor(labelFuelCapacity, 30.0); //Label
                                AnchorPane.setLeftAnchor(labelFuelCapacity, 10.0);
                                AnchorPane.setRightAnchor(labelFuelCapacity, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxFuelCapacity, 50.0);  //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxFuelCapacity, 10.0); 
                                AnchorPane.setRightAnchor(txtBoxFuelCapacity, 10.0); 
                            AnchorPane.setTopAnchor(labelFuelEfficiency, 85.0); //Label
                                AnchorPane.setLeftAnchor(labelFuelEfficiency, 10.0); 
                                AnchorPane.setRightAnchor(labelFuelEfficiency, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxFuelEfficiency, 105.0); //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxFuelEfficiency, 10.0);
                                AnchorPane.setRightAnchor(txtBoxFuelEfficiency, 10.0); 
                            AnchorPane.setTopAnchor(buttonDefineTruck, 140.0); //Button
                                AnchorPane.setLeftAnchor(buttonDefineTruck, 10.0);
                                AnchorPane.setRightAnchor(buttonDefineTruck, 10.0); 
                            AnchorPane.setTopAnchor(labelCurrentTruckStatus, 175.0); //Label
                                AnchorPane.setLeftAnchor(labelCurrentTruckStatus, 10.0); 
                                AnchorPane.setRightAnchor(labelCurrentTruckStatus, 10.0); 
                            AnchorPane.setTopAnchor(labelCurrentFuelAvailable, 195.0); //Label
                                AnchorPane.setLeftAnchor(labelCurrentFuelAvailable, 10.0);
                                AnchorPane.setRightAnchor(labelCurrentFuelAvailable, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxCurrentFuelAvailable, 215.0); //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxCurrentFuelAvailable, 10.0); 
                                AnchorPane.setRightAnchor(txtBoxCurrentFuelAvailable, 10.0); 
                            AnchorPane.setTopAnchor(labelCurrentLocation, 250.0); //Label
                                AnchorPane.setLeftAnchor(labelCurrentLocation, 10.0);
                                AnchorPane.setRightAnchor(labelCurrentLocation, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxCurrentLocation, 270.0); //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxCurrentLocation, 10.0); 
                                AnchorPane.setRightAnchor(txtBoxCurrentLocation, 10.0); 
                                AnchorPane.setBottomAnchor(buttonRemoveLocation, 10.0);

                        //CenterAnchorPane
                            AnchorPane.setTopAnchor(labelSelectDestination, 10.0); //Label
                                AnchorPane.setLeftAnchor(labelSelectDestination, 10.0);
                                AnchorPane.setRightAnchor(labelSelectDestination, 10.0);
                            AnchorPane.setTopAnchor(comboBox1, 50.0);  //ComboBox
                                AnchorPane.setLeftAnchor(comboBox1, 10.0);
                                AnchorPane.setRightAnchor(comboBox1, 10.0);
                            AnchorPane.setTopAnchor(assessDestination, 105.0); //Button
                                AnchorPane.setLeftAnchor(assessDestination, 10.0);
                                AnchorPane.setRightAnchor(assessDestination, 10.0);
                            AnchorPane.setTopAnchor(labelModifyLocationListing, 195.0); //Label
                                AnchorPane.setLeftAnchor(labelModifyLocationListing, 10.0);
                                AnchorPane.setRightAnchor(labelModifyLocationListing, 10.0);
                            AnchorPane.setTopAnchor(buttonExportList, 140.0); //Button
                                AnchorPane.setLeftAnchor(buttonExportList, 10.0);
                                AnchorPane.setRightAnchor(buttonExportList, 10.0); 
                            AnchorPane.setTopAnchor(buttonAddLocation, 215.0); //Button
                                AnchorPane.setLeftAnchor(buttonAddLocation, 10.0);
                                AnchorPane.setRightAnchor(buttonAddLocation, 10.0);
                            AnchorPane.setTopAnchor(buttonRemoveLocation, 270.0); //Button
                                AnchorPane.setLeftAnchor(buttonRemoveLocation, 10.0);
                                AnchorPane.setRightAnchor(buttonRemoveLocation, 10.0);
                                AnchorPane.setBottomAnchor(buttonRemoveLocation, 10.0);

                        //RightAnchorPane
                            AnchorPane.setTopAnchor(labelDestinationHasFuel, 10.0); //Label
                                AnchorPane.setLeftAnchor(labelDestinationHasFuel, 10.0); 
                                AnchorPane.setRightAnchor(labelDestinationHasFuel, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxDestinationHasFuel, 50.0);  //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxDestinationHasFuel, 10.0); 
                                AnchorPane.setRightAnchor(txtBoxDestinationHasFuel, 10.0); 
                            AnchorPane.setTopAnchor(labelDistanceToLocation, 85.0); //Label
                                AnchorPane.setLeftAnchor(labelDistanceToLocation, 10.0);
                                AnchorPane.setRightAnchor(labelDistanceToLocation, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxDistanceToLocation, 105.0);  //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxDistanceToLocation, 10.0);
                                AnchorPane.setRightAnchor(txtBoxDistanceToLocation, 10.0); 
                            AnchorPane.setTopAnchor(labelFuelRequiredToLocation, 140.0); //Label
                                AnchorPane.setLeftAnchor(labelFuelRequiredToLocation, 10.0);
                                AnchorPane.setRightAnchor(labelFuelRequiredToLocation, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxFuelRequiredToLocation, 160.0);  //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxFuelRequiredToLocation, 10.0);
                                AnchorPane.setRightAnchor(txtBoxFuelRequiredToLocation, 10.0); 
                            AnchorPane.setTopAnchor(labelDestinationAssessment, 195.0); //Label
                                AnchorPane.setLeftAnchor(labelDestinationAssessment, 10.0); 
                                AnchorPane.setRightAnchor(labelDestinationAssessment, 10.0); 
                            AnchorPane.setTopAnchor(txtBoxDestinationAssessment, 215.0);  //TxtBox
                                AnchorPane.setLeftAnchor(txtBoxDestinationAssessment, 10.0); 
                                AnchorPane.setRightAnchor(txtBoxDestinationAssessment, 10.0); 
                            AnchorPane.setTopAnchor(buttonGoToLocation, 270.0); //Button 
                                AnchorPane.setLeftAnchor(buttonGoToLocation, 10.0); 
                                AnchorPane.setRightAnchor(buttonGoToLocation, 10.0);
                                AnchorPane.setBottomAnchor(buttonGoToLocation, 10.0);
                           
    //Button functionality 
        //Press the assessDestination button
            assessDestination.setOnAction(new EventHandler<ActionEvent>()
                {
                    //Variable declarations
                        int locationIndex = 0;
                        double requiredDistance = 0.0;
                        double requiredFuel = 0.0;
                        boolean destinationHasFuel = false;
                        
                    @Override
                    public void handle(ActionEvent event)
                        {  
                        //Get index location of locationList object that was selected
                            for (int i02 = 0; i02 < locationList.size(); i02++)
                                { 
                                    if (comboBox1.getValue() == locationList.get(i02).getName())
                                    { locationIndex = i02; }
                                }            
                        //The following variables are used to help improve code readibility and shorten code lines
                            requiredDistance = userTruck.getDistance(locationList.get(locationIndex));
                            requiredFuel = userTruck.getFuelRequired(requiredDistance);
                            destinationHasFuel = locationList.get(locationIndex).getHasFuel();
                            
                        //Exception handle for a null selection
                            if (comboBox1.getValue() == null )
                                { JOptionPane.showMessageDialog(null, "Please select a destination from the comboBox."); }

                        //Exception handle for not defining a truck
                            else if (txtBoxFuelCapacity.getText().trim().isEmpty() || txtBoxFuelEfficiency.getText().trim().isEmpty())
                               { JOptionPane.showMessageDialog(null, "Please first define a truck on the left pane."); }
                            
                        //For everything else...
                            else
                                {   
                                    txtBoxDestinationHasFuel.setText(String.valueOf(destinationHasFuel)); //Show if the destination has a gas station
                                    txtBoxDistanceToLocation.setText(String.valueOf(requiredDistance)); //Show the calculated distance to destination
                                    txtBoxFuelRequiredToLocation.setText(String.valueOf(requiredFuel)); //Show the required gallons
                                    txtBoxDestinationAssessment.setText(userTruck.getDestinationAssessment( requiredFuel, destinationHasFuel)); //Show the assessment
                                    JOptionPane.showMessageDialog(null, userTruck.getDestinationAssessment( requiredFuel, destinationHasFuel));//Display a popup box of assessment   
                                }
                        }
                }); 
            
        //Press the buttonGoToLocation button
            buttonGoToLocation.setOnAction(new EventHandler<ActionEvent>()
                {
                    //Variable declarations
                        int locationIndex = 0;
                        double requiredDistance = 0.0;
                        double requiredFuel = 0.0;

                    @Override
                    public void handle(ActionEvent event)
                        { 
                        //Get index location of locationList object that was selected
                            for (int i02 = 0; i02 < locationList.size(); i02++)
                                { 
                                    if (comboBox1.getValue() == locationList.get(i02).getName())
                                    { locationIndex = i02; }
                                }
                        //Error proof for a truck that is not defined    
                          if (txtBoxFuelCapacity.getText().trim().isEmpty() || txtBoxFuelEfficiency.getText().trim().isEmpty())
                               { JOptionPane.showMessageDialog(null, "Please first define a truck on the left pane."); }
                        //If it is defined...
                          else
                            {     //...Move the truck and refuel
                                    requiredDistance = userTruck.getDistance(locationList.get(locationIndex));
                                    requiredFuel = userTruck.getFuelRequired(requiredDistance);
                                    userTruck.moveAndRefuel(requiredFuel, locationList.get(locationIndex));   
                                  //Display the current status of the truck
                                      txtBoxCurrentFuelAvailable.setText(String.valueOf(userTruck.getFuelCurrent()));
                                      txtBoxCurrentLocation.setText(userTruck.getTruckLocation().getName());   
                            }
                        }
                });  
                        
        //Press the buttonDefineTruck button
            buttonDefineTruck.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                        { 
                            try
                                {
                                   capacityEntry = Double.parseDouble(txtBoxFuelCapacity.getText());
                                   efficiencyEntry = Double.parseDouble(txtBoxFuelEfficiency.getText());  
                                   userTruck.setFuelCapacity(capacityEntry); //Set the fuel capacity
                                   userTruck.setFuelEfficiency(efficiencyEntry); //Set the fuel efficiency
                                   userTruck.setFuelCurrent(capacityEntry); //The current fuel will be set to max capacity at default 
                                   userTruck.setTruckLocation(locationList.get(0)); //Will set Cleveland as the default
                                   //Display the current status of the truck
                                    txtBoxCurrentFuelAvailable.setText(String.valueOf(userTruck.getFuelCurrent()));
                                    txtBoxCurrentLocation.setText(userTruck.getTruckLocation().getName()); 
                                }
                            catch(NumberFormatException e) //Error-proof for an integer input 
                                { JOptionPane.showMessageDialog(null, "Please fill in BOTH the 'Fuel Capacity' box AND 'Fuel Efficiency' box with DOUBLE or INTEGER type numbers only.");}   
                        }
                }); 
               
        //Press the buttonRemoveLocation button
            buttonRemoveLocation.setOnAction(new EventHandler<ActionEvent>()
               {
                   int locationIndex = 0;
                   @Override
                   public void handle(ActionEvent event)
                       { 
                            //Delete it internally
                                for (int i02 = 0; i02 < locationList.size(); i02++)
                                    { 
                                        if (comboBox1.getValue() == locationList.get(i02).getName())
                                            { locationIndex = i02; }
                                    } 
                            //Delete it from the combobox
                                JOptionPane.showMessageDialog(null, comboBox1.getValue() + " will be removed from the list.");//Display a popup box for confirmation
                                comboBox1.getItems().remove(comboBox1.getValue());
                                locationList.remove(locationIndex);
                       }  
               }); 
           
        //Press the buttonAddLocation
            buttonAddLocation.setOnAction(new EventHandler<ActionEvent>()
               {
                   @Override
                   public void handle(ActionEvent event)
                       { addLocationScene();}  
               }); 
            
        //Press the buttonExportFile
            buttonExportList.setOnAction(new EventHandler<ActionEvent>()
               {
                   @Override
                   public void handle(ActionEvent event)
                       { 
                        try
                            {
                                FileWriter locationExternalFile = new FileWriter("src/dataSets/Locations.csv" , false); //Define the .csv , false included for a complete update
                                for (int i03 = 0; i03 < locationList.size(); i03++)
                                    {
                                        locationExternalFile.write(locationList.get(i03).getName() + "," +
                                        locationList.get(i03).getXCoor() + "," + locationList.get(i03).getYCoor() + "," +
                                        locationList.get(i03).getHasFuel() + "\n");
                                    }
                                JOptionPane.showMessageDialog(null, "Location list saved to:\n src/dataSets/Locations.csv");//Display a popup box for confirmation
                                locationExternalFile.close(); //Close the file
                            }
                        catch (IOException ex)
                            { Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex); }  
                       }  
               });     

                Scene scene = new Scene(borderPane1, 525, 325); //Main scene, width, height
                swapStage.setTitle("Shipping on a GUI! Trial version ONLY, real license only $99k per month!");
                swapStage.setScene(scene);
                swapStage.setResizable(false);
                swapStage.show();
            }
        
//*********************************addLocationScene***************************** 
        public void addLocationScene()
            {
            //PART#1: Instantiate the objects  
                //Panes
                    AnchorPane root = new AnchorPane();
                //Labels
                    Label labelSceneTitle = new Label();
                    Label labelSetLocationName = new Label();
                    Label labelSetXCoor = new Label();
                    Label labelSetYCoor = new Label();
                    Label labelGasStationPresence = new Label();
                //Push buttons
                    Button buttonAddLocation = new Button();
                    Button buttonReturnToMainMenu = new Button();
                //Radio buttons
                    final ToggleGroup radioGroup = new ToggleGroup();
                    RadioButton radioGasStationIsTrue = new RadioButton("Yes");
                    radioGasStationIsTrue.setToggleGroup(radioGroup);
                    RadioButton radioGasStationIsFalse = new RadioButton("No"); 
                    radioGasStationIsFalse.setToggleGroup(radioGroup);
                //Text fields
                    TextField txtBoxSetLocationName = new TextField();
                    TextField txtBoxSetXCoor = new TextField();
                    TextField txtBoxSetYCoor = new TextField();
                
            //PART#2: Attach the objects to their pane 
                //Labels
                    root.getChildren().add(labelSceneTitle);
                    root.getChildren().add(labelSetLocationName);
                    root.getChildren().add(labelSetXCoor);
                    root.getChildren().add(labelSetYCoor);
                    root.getChildren().add(labelGasStationPresence);
                //Push buttons
                    root.getChildren().add(buttonAddLocation);
                    root.getChildren().add(buttonReturnToMainMenu);
                //Radio buttons
                    root.getChildren().add(radioGasStationIsTrue);
                    root.getChildren().add(radioGasStationIsFalse);                   
                //Text fields
                    root.getChildren().add(txtBoxSetLocationName);
                    root.getChildren().add(txtBoxSetXCoor);
                    root.getChildren().add(txtBoxSetYCoor);                

            //PART#3: Define the location/style attributes
                //Define labels
                    labelSceneTitle.setText("Add Location Settings");
                    labelSetLocationName.setText("Set Location Name:");
                    labelSetXCoor.setText("Set X-coordinate:");
                    labelSetYCoor.setText("Set Y-coordinate:");
                    labelGasStationPresence.setText("Does location have gas?");
                    
                //Define buttons
                    //Set text
                        buttonAddLocation.setText("Add location"); 
                        buttonReturnToMainMenu.setText("Return to main menu"); 
                    //Set Width
                        buttonAddLocation.setPrefWidth(150);
                        buttonReturnToMainMenu.setPrefWidth(150);
                //Locations
                    AnchorPane.setTopAnchor(labelSceneTitle, 10.0); //Label
                        AnchorPane.setLeftAnchor(labelSceneTitle, 10.0); 
                        AnchorPane.setRightAnchor(labelSceneTitle, 10.0); 
                    AnchorPane.setTopAnchor(labelSetLocationName, 30.0); //Label
                        AnchorPane.setLeftAnchor(labelSetLocationName, 10.0); 
                        AnchorPane.setRightAnchor(labelSetLocationName, 10.0); 
                    AnchorPane.setTopAnchor(txtBoxSetLocationName, 50.0);  //TxtBox
                        AnchorPane.setLeftAnchor(txtBoxSetLocationName, 10.0); 
                        AnchorPane.setRightAnchor(txtBoxSetLocationName, 10.0); 
                    AnchorPane.setTopAnchor(labelSetXCoor, 85.0); //Label
                        AnchorPane.setLeftAnchor(labelSetXCoor, 10.0); 
                         AnchorPane.setRightAnchor(labelSetXCoor, 10.0); 
                    AnchorPane.setTopAnchor(txtBoxSetXCoor, 105.0); //TxtBox 
                        AnchorPane.setLeftAnchor(txtBoxSetXCoor, 10.0); 
                        AnchorPane.setRightAnchor(txtBoxSetXCoor, 10.0); 
                    AnchorPane.setTopAnchor(labelSetYCoor, 140.0); //Label
                        AnchorPane.setLeftAnchor(labelSetYCoor, 10.0); 
                         AnchorPane.setRightAnchor(labelSetYCoor, 10.0); 
                    AnchorPane.setTopAnchor(txtBoxSetYCoor, 160.0); //TxtBox
                        AnchorPane.setLeftAnchor(txtBoxSetYCoor, 10.0);
                        AnchorPane.setRightAnchor(txtBoxSetYCoor, 10.0);
                    AnchorPane.setTopAnchor(labelGasStationPresence, 195.0); //Label
                        AnchorPane.setLeftAnchor(labelGasStationPresence, 10.0);
                        AnchorPane.setRightAnchor(labelGasStationPresence, 10.0);
                    AnchorPane.setTopAnchor(radioGasStationIsTrue, 215.0); //Radio button
                        AnchorPane.setLeftAnchor(radioGasStationIsTrue, 20.0);
                        AnchorPane.setRightAnchor(radioGasStationIsTrue, 20.0);
                    AnchorPane.setTopAnchor(radioGasStationIsFalse, 215.0); //Radio button
                        AnchorPane.setLeftAnchor(radioGasStationIsFalse, 110.0);
                    AnchorPane.setTopAnchor(buttonAddLocation, 250.0); //Push button 
                        AnchorPane.setLeftAnchor(buttonAddLocation, 10.0);
                        AnchorPane.setRightAnchor(buttonAddLocation, 10.0);
                    AnchorPane.setTopAnchor(buttonReturnToMainMenu, 290.0); //Push button 
                        AnchorPane.setLeftAnchor(buttonReturnToMainMenu, 10.0);
                        AnchorPane.setRightAnchor(buttonReturnToMainMenu, 10.0);
                        AnchorPane.setBottomAnchor(buttonReturnToMainMenu, 10.0);
                        
              //Press the buttonReturnToMainMenu
                buttonReturnToMainMenu.setOnAction(new EventHandler<ActionEvent>()
                   {
                       @Override
                       public void handle(ActionEvent event)
                           { 
                               swapCount++;
                               start(swapStage);
                           }  
                   }); 
                
              //Press the buttonAddLocation
                buttonAddLocation.setOnAction(new EventHandler<ActionEvent>()
                   {
                       @Override
                       public void handle(ActionEvent event)
                           { 
                               //Initializing exception variables
                                    boolean exceptionLocationName = true;
                                    boolean exceptionXCoor = true;
                                    boolean exceptionYCoor = true;
                                    boolean exceptionHasFuel = true;
                               
                                //Create an instance of the location
                                    Location newLocation = new Location(); //Creating a new instance using the no-parameter constructor
                                    
                                //Collect the location name
                                    try
                                        { newLocation.setName(txtBoxSetLocationName.getText()); } 
                                    catch(NumberFormatException e)
                                        { 
                                          JOptionPane.showMessageDialog(null, "For the location name, please enter strings only...");
                                          exceptionLocationName = false;
                                        }

                                //Collect the x-coordinate
                                    try
                                        { newLocation.setXCoor(Double.parseDouble(txtBoxSetXCoor.getText())); } 
                                    catch(NumberFormatException e)
                                        { 
                                            JOptionPane.showMessageDialog(null, "Dude, for the x-coordinate, do you even 'double' or 'integer'?");
                                            exceptionXCoor = false;
                                        }                                    

                                //Collect the y-coordinate
                                    try
                                        { newLocation.setYCoor(Double.parseDouble(txtBoxSetYCoor.getText())); } 
                                    catch(NumberFormatException e)
                                        { 
                                            JOptionPane.showMessageDialog(null, "For the y-coordinate, Obi-Wan never told you of the 'double' or 'integer'...");
                                            exceptionYCoor = false;
                                        }

                                //Collect the hasFuel boolean  
                                   if (radioGasStationIsTrue.isSelected() == true)
                                        { newLocation.setHasFuel(true); } 
                                   else if (radioGasStationIsFalse.isSelected() == true)
                                        { newLocation.setHasFuel(false); }
                                   else
                                        { 
                                            JOptionPane.showMessageDialog(null, "For the gas station question, please select a true or false radio button.");
                                            exceptionHasFuel = false;
                                        } 
                                     
                                //Add the location object to the list
                                    if ( exceptionLocationName == true && exceptionXCoor == true && exceptionYCoor == true && exceptionHasFuel == true)
                                        {
                                            //locationList.add(new Location("Cleveland", 0, 0, true));
                                                locationList.add(newLocation.deepCopy());
                                                JOptionPane.showMessageDialog(null, newLocation.getName() + " has been added to the location list.");  
                                        } 
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null, newLocation.getName() + " has NOT been added.");   
                                        }
                           }  
                   }); 

                Scene addLocationScene = new Scene(root, 175, 325); //Main scene, width, height
                swapStage.setTitle("Add Location");
                swapStage.setScene(addLocationScene);
                swapStage.setResizable(false);
                swapStage.show();
            }
        
    //Main program
        public static void main(String[] args)
        { 
            launch(args); 
        }
    }