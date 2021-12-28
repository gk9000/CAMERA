package camera;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Scanner;

// THIS CLASS PROVIDES A GUI

public class UserInterface extends Application {

    Stage window;
    Scene scene;

    //SERVICE VARIABLES:
    String s;


    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;
        window.setTitle("YOUR CAMERA SETTINGS");
        window.setOnCloseRequest(e-> window.close());

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);


        // CAMERA MODE
        // camera mode label
        Label modeLabel=new Label("SELECT CAMERA MODE:");
        HBox modeLabelBox = new HBox(modeLabel);
        GridPane.setConstraints(modeLabelBox, 0,0);

        // camera mode radio buttons
        ToggleGroup modes = new ToggleGroup();

        RadioButton programMode=new RadioButton ("FULL PROGRAM");
        programMode.setToggleGroup(modes);

        RadioButton landscapeMode=new RadioButton ("LANDSCAPE");
        landscapeMode.setToggleGroup(modes);

        RadioButton actionMode=new RadioButton ("ACTION");
        actionMode.setToggleGroup(modes);

        RadioButton portraitMode=new RadioButton ("PORTRAIT");
        portraitMode.setToggleGroup(modes);

        programMode.setSelected(true);

        // camera mode HBox
        HBox modeBox = new HBox (22,programMode,landscapeMode,actionMode,portraitMode);
        GridPane.setConstraints(modeBox, 0,2);


        // LENS INFO
        // lens info label
        Label lensLabel=new Label("PROVIDE LARGEST AND SMALLEST APERTURE OF THE CONNECTED LENS AND IT'S FOCAL LENGTH IN MM:");
        HBox lensLabelBox = new HBox(lensLabel);
        GridPane.setConstraints(lensLabelBox, 0,6);

        // LENS INFO - LARGEST  AND SMALLEST APERTURES
        // largest aperture menu choice box
        ChoiceBox<String> maxApMenuBox = new ChoiceBox<>();
        maxApMenuBox.getItems().add("2.8");
        maxApMenuBox.getItems().add("4");
        maxApMenuBox.getItems().add("5.6");

        maxApMenuBox.setValue("2.8");

        // smallest aperture menu choice box
        ChoiceBox<String> minApMenuBox = new ChoiceBox<String>();
        minApMenuBox.getItems().add("16");
        minApMenuBox.getItems().add("22");
        minApMenuBox.getItems().add("32");

        minApMenuBox.setValue("32");


        // LENS INFO - FOCAL LENGTH
        ChoiceBox<String> focalLengthBox = new ChoiceBox<String>();

        for(String s : Camera.focalLengthTable){
            focalLengthBox.getItems().add(s);
        }//close foreach

        focalLengthBox.setValue("50");


        // LENS INFO - HBox for max aperture, min aperture and film speed choice boxes
        HBox lensBox = new HBox(70,maxApMenuBox,minApMenuBox,focalLengthBox);
        GridPane.setConstraints(lensBox, 0,8);


        // FILM SPEED
        // film speed label
        Label filmSpeedLabelBox=new Label("SELECT FILM SPEED:");
        GridPane.setConstraints(filmSpeedLabelBox, 0,12);

        // film speed menu choice box
        ChoiceBox<String> filmSpeedBox = new ChoiceBox<String>();
        filmSpeedBox.getItems().add("100");
        filmSpeedBox.getItems().add("200");
        filmSpeedBox.getItems().add("400");
        filmSpeedBox.getItems().add("800");
        filmSpeedBox.getItems().add("1600");
        filmSpeedBox.getItems().add("3200");

        filmSpeedBox.setValue("100");


        GridPane.setConstraints(filmSpeedBox, 0,14);


        // USAGE
        // usage label
        Label usageLabel=new Label("HOW THE CAMERA WILL BE USED:");
        HBox usageLabelBox=new HBox(usageLabel);
        GridPane.setConstraints(usageLabelBox, 0,18);

        // usage radio buttons
        ToggleGroup usages = new ToggleGroup();

        RadioButton handheldUsage=new RadioButton ("HANDHELD");
        handheldUsage.setToggleGroup(usages);

        RadioButton onMonopodUsage=new RadioButton ("ON A MONOPOD");
        onMonopodUsage.setToggleGroup(usages);

        RadioButton onTripodUsage=new RadioButton ("ON A TRIPOD");
        onTripodUsage.setToggleGroup(usages);

        handheldUsage.setSelected(true);

        // usage HBox
        HBox usageBox = new HBox(25,handheldUsage,onMonopodUsage,onTripodUsage);
        GridPane.setConstraints(usageBox, 0,20);


        // SET LIGHT INTENSITY

        Label lightLabel=new Label("PROVIDE LIGHT INTENSITY (A WHOLE NUMBER ABOVE ZERO) :");
        HBox lightLabelBox = new HBox(lightLabel);
        GridPane.setConstraints(lightLabelBox, 0,24);

        TextField lightField = new TextField();
        HBox lightFieldBox=new HBox(lightField);
        GridPane.setConstraints(lightFieldBox, 0,26);


        // RESULTS
        Label resultLabel=new Label();
        HBox resultBox = new HBox(25, resultLabel);
        GridPane.setConstraints(resultBox, 0,34);

        // UNDER/OVEREXPOSURE WARNINGS
        Label warningLabel=new Label();
        HBox warningLabelBox = new HBox( warningLabel);
        GridPane.setConstraints(warningLabelBox, 0,35);

        // HYPERFOCAL DISTANCE
        Label hyperfocalDistance=new Label();
        HBox hyperfocalDistanceBox = new HBox( hyperfocalDistance);
        GridPane.setConstraints(hyperfocalDistanceBox, 0,37);


        // THE "RUN" BUTTON
        Button button=new Button("Run!");
        HBox buttonBox = new HBox(button);
        button.setOnAction(ele->{

            Camera camera=new Camera();

            // READ LIGHT VALUE INPUT AND VALIDATE IT
            s = lightField.getText();
            Scanner scanner=new Scanner(s);
            if (scanner.hasNextLong()) {
                camera.setLight(scanner.nextLong());
                if(camera.getLight()<=0) {
                    lightField.setText("PROVIDE VALID NUMBER");
                    resultLabel.setText("");
                    warningLabel.setText("");
                    return;
                }//close if
            } else {
                lightField.setText("PROVIDE VALID NUMBER");
                resultLabel.setText("");
                warningLabel.setText("");
                return;
            }//close else


            // COLLECT INFO FROM ALL BUTTONS AND CHOICE BOXES:
            RadioButton a = (RadioButton) modes.getSelectedToggle();
            String temp=a.getText();
            switch(temp){
                case "FULL PROGRAM": camera.setCameraMode(100); break;
                case "LANDSCAPE": camera.setCameraMode(200); break;
                case "ACTION": camera.setCameraMode(300); break;
                case "PORTRAIT": camera.setCameraMode(400); break;
            }//close switch


            s = maxApMenuBox.getValue();
            camera.setMaxAperture(Double.parseDouble(s));
            maxApMenuBox.setOnAction(e-> {s= maxApMenuBox.getValue(); camera.setMaxAperture(Double.parseDouble(s));});

            s = minApMenuBox.getValue();
            camera.setMinAperture(Double.parseDouble(s));
            minApMenuBox.setOnAction(e-> {s= minApMenuBox.getValue(); camera.setMinAperture(Double.parseDouble(s));});

            s = focalLengthBox.getValue();
            camera.setFocalLength(Integer.parseInt(s));
            focalLengthBox.setOnAction(e-> {s= focalLengthBox.getValue(); camera.setFocalLength(Integer.parseInt(s));});

            s = filmSpeedBox.getValue();
            camera.setFilmSpeed(Integer.parseInt(s));
            filmSpeedBox.setOnAction(e-> {s= filmSpeedBox.getValue(); camera.setFilmSpeed(Integer.parseInt(s));});

            RadioButton b = (RadioButton) usages.getSelectedToggle();
            String temp1=b.getText();
            switch(temp1){
                case "HANDHELD": camera.setCameraUsage(10); break;
                case "ON A MONOPOD": camera.setCameraUsage(20); break;
                case "ON A TRIPOD": camera.setCameraUsage(30); break;
            }//close switch


            camera.process(camera);


            resultLabel.setText("APERTURE:   " + camera.realLifeAperture + "            " +
                    "SHUTTER SPEED:   " + camera.shutterSpeedHuman);

            if(camera.underexposureWarning){warningLabel.setText("WARNING: UNDEREXPOSURE !!!");}
            else if(camera.overexposureWarning){warningLabel.setText("WARNING: OVEREXPOSURE !!!");}
            else {warningLabel.setText("");}

            if(camera.hyper){hyperfocalDistance.setText("For this combination of focal length and aperture hyperfocal distance is " +
                    HyperfocalDistance.calculateHD(camera) + " meters");}
            else {hyperfocalDistance.setText("");}

        }); // close lambda

        GridPane.setConstraints(buttonBox, 0,30);


        // ARRANGE ELEMENTS ON THE GRID
        grid.getChildren().addAll(usageLabelBox, usageBox,modeLabelBox, modeBox,lensLabelBox,lensBox,
                filmSpeedLabelBox,filmSpeedBox, buttonBox,resultBox,warningLabelBox,lightLabelBox,lightFieldBox,hyperfocalDistanceBox);

        scene= new Scene(grid, 700,700);
        window.setScene(scene);
        window.show();
    }//close start

    public static void main(String[] args) {
        launch(args);
    }//close main
}//close class



