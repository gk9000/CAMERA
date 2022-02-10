package camera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

// THE MOST IMPORTANT CLASS THAT INITIALIZES SERVICE TABLES AND MANAGES THE WHOLE PROCESS BY CALLING METHODS IN OTHER CLASSES

public class Camera{

    // variables provided by user:
    private long light;
    private int focalLength;
    private double maxAperture;
    private double minAperture;
    private int cameraMode;
    private int cameraUsage;
    private int filmSpeed;

    // service tables:
    final int [] shutterSpeedTable = new int[19];
    final static int [] shutterSpeedRealLifeTable = new int[19];
    final int [] apertureTable = new int[8];
    final static double [] apertureRealLifeTable=new double[8];
    final long [] exposureTable = new long[26];
    final static String[] focalLengthTable= {"16","20","28","24","35","50","70","85","100","135","150","180","210","250","300","400","500"};

    // service variables:
    int speedFactor;
    int counter;
    int[] thisLensApertureTable;
    int bestAperture;
    int longestShutterSpeed;
    int longestRealLifeShutterSpeed;
    int shutterSpeed;
    int aperture;
    int realLifeShutterSpeed;
    boolean hyper;
    private ArrayList<Integer> initList = new <Integer>ArrayList (Arrays.asList(1,2,3,4,5,6,7)); // verification tool to check that when using "Test" class,
                                                                                        // all input variables have been properly initialized

    //output variables:
    String shutterSpeedHuman;
    double realLifeAperture;
    boolean underexposureWarning;
    boolean overexposureWarning;


    // CONSTRUCTOR FOR "TEST" CLASS
    public Camera(long light, int focalLength, double maxAperture, double minAperture, int cameraMode, int cameraUsage, int filmSpeed) {
        this.setLight(light);
        this.setFocalLength(focalLength);
        this.setMaxAperture(maxAperture);
        this.setMinAperture(minAperture);
        this.setCameraMode(cameraMode);
        this.setCameraUsage(cameraUsage);
        this.setFilmSpeed(filmSpeed);
        process(this);
    }//close constructor

     // DEFAULT CONSTRUCTOR FOR "USERINTERFACE" CLASS ( GUI )
    public Camera(){}


    @Override
    public String toString() {
        return "Camera { " +
                "light " + light +
                ", film " + filmSpeed +
                ", lens " + focalLength +
                ", max ap " + maxAperture +
                ", min ap " + minAperture +
                ", longest shutter " + longestRealLifeShutterSpeed +
                ", result ap " + realLifeAperture +
                ", result shutter " + shutterSpeedHuman +
                ", underexp " + underexposureWarning +
                ", overexp " + overexposureWarning +
                " }";
    }//close toString


    private void initialize (Camera camera){
        // ARRAY OF ALL POSSIBLE SHUTTER SPEEDS IN LIGHT VALUES
        shutterSpeedTable[0] = 1*camera.speedFactor;
        for (int i = 1; i < 19; i++) {
            shutterSpeedTable[i] = (int) Math.pow(2, i)*camera.speedFactor;
        }//close for

        // ARRAY OF ALL POSSIBLE APERTURES IN LIGHT VALUES
        apertureTable[0] = 1;
        for (int i = 1; i < 8; i++) {
            apertureTable[i] = (int) Math.pow(2, i);
        }//close for

        // ARRAY OF ALL POSSIBLE EXPOSURES IN LIGHT VALUES
        exposureTable[0] = 1*camera.speedFactor;
        for (int i = 1; i < 26; i++) {
            exposureTable[i] = (long) Math.pow(2, i)*camera.speedFactor;
        }//close for


        // ARRAY OF ALL POSSIBLE SHUTTER SPEEDS IN REAL-LIFE NUMBERS (WHOLE SECONDS ARE NEGATIVE)
        shutterSpeedRealLifeTable[0]= -30;
        shutterSpeedRealLifeTable[1]= -15;
        shutterSpeedRealLifeTable[2]= -8;
        shutterSpeedRealLifeTable[3]= -4;
        shutterSpeedRealLifeTable[4]= -2;
        shutterSpeedRealLifeTable[5]= -1;
        shutterSpeedRealLifeTable[6]= 2;
        shutterSpeedRealLifeTable[7]= 4;
        shutterSpeedRealLifeTable[8]=8;
        shutterSpeedRealLifeTable[9]= 15;
        shutterSpeedRealLifeTable[10]= 30;
        shutterSpeedRealLifeTable[11]= 60;
        shutterSpeedRealLifeTable[12]= 125;
        shutterSpeedRealLifeTable[13]= 250;
        shutterSpeedRealLifeTable[14]= 500;
        shutterSpeedRealLifeTable[15]= 1000;
        shutterSpeedRealLifeTable[16]= 2000;
        shutterSpeedRealLifeTable[17]= 4000;
        shutterSpeedRealLifeTable[18]= 8000;

        // ARRAY OF ALL POSSIBLE APERTURES IN REAL-LIFE NUMBERS
        apertureRealLifeTable[0] = 2.8;
        apertureRealLifeTable[1] = 4.0;
        apertureRealLifeTable[2] = 5.6;
        apertureRealLifeTable[3] = 8.0;
        apertureRealLifeTable[4] = 11.0;
        apertureRealLifeTable[5] = 16.0;
        apertureRealLifeTable[6] = 22.0;
        apertureRealLifeTable[7] = 32.0;
    }//close initialize


    //GETTERS AND SETTERS (WITH INPUT VALIDATION)
    // FOR LONG "LIGHT"
    public long getLight() {
        return light;
    }
    public void setLight(long light) {
        if(light<=0) {
            System.out.println("Provide valid light value (from 1 to approx. 1 bln)");
        } else {
            this.light = light;
            this.initList.removeIf(s -> s==1);
        }//close else
    }//close setter

    //  FOR INT "FILMSPEED"
    public int getFilmSpeed() {
        return filmSpeed;
    }
    public void setFilmSpeed(int filmSpeed) {
        int[] temp={100,200,400,800,1600,3200};
        if(IntStream.of(temp).anyMatch(x -> x == filmSpeed)) {
            this.filmSpeed = filmSpeed;
            this.initList.removeIf(s -> s==2);
        } else {
            System.out.println("Provide valid film speed (100, 200, 400, 800, 1600, 3200)");
        }//close else
    }//close setter

    // FOR INT "FOCALLENGTH"
    public int getFocalLength() { return focalLength; }
    public void setFocalLength(int focalLength) {
        if(Arrays.asList(Camera.focalLengthTable).contains(Integer.toString(focalLength))) {
            this.focalLength = focalLength;
            this.initList.removeIf(s -> s==3);
        } else {
            System.out.println("Provide a valid focal length (16, 20, 24, 28, 35, 50, 70, 85, 100, 135, 150, 180, 210, 250, 300, 400, 500)");
        } // close else      
    }//close setter

    // FOR DOUBLE "MAXAPERTURE"
    public double getMaxAperture() {
        return maxAperture;
    }
    public void setMaxAperture(double maxAperture) {
        if(maxAperture==2.8 || maxAperture==4.0 || maxAperture==5.6) {
            this.maxAperture = maxAperture;
            this.initList.removeIf(s -> s==4);
        } else {
            System.out.println("Provide a valid biggest aperture (2.8 or 4.0 or 5.6)");
        } // close else
    }//close setter

    // FOR DOUBLE "MINAPERTURE"
    public double getMinAperture() {
        return minAperture;
    }
    public void setMinAperture(double minAperture) {
        if (minAperture == 16.0 || minAperture == 22.0 || minAperture == 32.0) {
            this.minAperture = minAperture;
            this.initList.removeIf(s -> s==5);

        } else {
            System.out.println("Provide a valid smallest aperture (16.0 or 22.0 or 32.0)");
        } // close else
    }//close setter

    // FOR INT "CAMERAMODE"
    public int getCameraMode() { return cameraMode; }
    public void setCameraMode(int cameraMode) {
        int[] temp={100,200,300,400};
        if(IntStream.of(temp).anyMatch(x -> x == cameraMode)) {
            this.cameraMode = cameraMode;
            this.initList.removeIf(s -> s==6);

        } else {
            System.out.println("Provide valid camera mode (100 for full program, 200 for landscape, 300 for action, 400 for portrait)");
        }//close else
    }//close setter

    // FOR INT "CAMERAUSAGE"
    public int getCameraUsage() {
        return cameraUsage;
    }
    public void setCameraUsage(int cameraUsage) {
        int[] temp={10,20,30};
        if(IntStream.of(temp).anyMatch(x -> x == cameraUsage)) {
            this.cameraUsage = cameraUsage;
            this.initList.removeIf(s -> s==7);
        } else {
            System.out.println("Provide valid camera usage (10 for handheld, 20 for on a monopod, 30 for on a tripod)");
        }//close else
    }//close setter


    // MAKE ARRAYLIST OF ALL POSSIBLE APERTURES OF THE CONNECTED LENS (IN LIGHT VALUES)
    private void createThisLensApertureTable(Camera camera){
        int start=0;
        int end=0;

        for(int i=0; i<apertureRealLifeTable.length; i++){
              if(apertureRealLifeTable[i] == maxAperture){
                  start=i;
                  break;
              }//close if
            }//close for
        for(int i=apertureRealLifeTable.length-1; i>=0; i--){
            if(apertureRealLifeTable[i]==minAperture){
                end=i;
                break;
            }//close if
        }//close for

        int[] temp=new int[end-start+1];

            for (int i = 0; i < apertureTable.length && start<=end; i++) {
                temp[i] = apertureTable[start];
                start++;
                if (apertureRealLifeTable[i] == minAperture) {
                    break;
                }//close if
            }//close for
        thisLensApertureTable=temp;
    }//close createThisLensApertureValues


    // THIS METHOD RUNS ALL THE CALCULATIONS BY SUBSEQUENTLY CALLING OTHER METHODS:
    void process (Camera camera){

        if(camera.initList.isEmpty()) {

            SpeedFactor.findSpeedFactor(camera, camera.getFilmSpeed());

            camera.initialize(camera);

            camera.createThisLensApertureTable(camera);

            RoundingLightValue.roundingLightValue(camera);

            SetUsage.longestShutterSpeed(camera);

            DefineCameraMode.defineCameraMode(camera);

            // CATCHING EXCEPTIONS THROWN IN "CALCULATION" CLASS AND "OVERANDUNDER" CLASS; CALCULATING RESULTS
            try {
                Calculation.calculate(camera);
            } catch (Throwable throwable) {
            }//close catch

            // PROVIDE OUTPUT IN A HUMAN-UNDERSTANDABLE FORM:
            camera.realLifeShutterSpeed = RealLifeByValue.realLifeByValueSpeed(camera.shutterSpeedTable, Camera.shutterSpeedRealLifeTable, camera.shutterSpeed);
            camera.realLifeAperture = RealLifeByValue.realLifeByValueAperture(camera.apertureTable, Camera.apertureRealLifeTable, camera.aperture);

            camera.shutterSpeedHuman = ShutterSpeedConvertion.shutterSpeedConvertion(camera, camera.realLifeShutterSpeed);

            System.out.println();
            System.out.println(camera);
            System.out.println();

            System.out.println("THE RESULT APERTURE IS " + camera.realLifeAperture);
            System.out.println("THE RESULT SHUTTER SPEED IS " + camera.shutterSpeedHuman);
            System.out.println();

            if(camera.underexposureWarning){System.out.println("WARNING: UNDEREXPOSURE !!!");}
            else if(camera.overexposureWarning){System.out.println("WARNING: OVEREXPOSURE !!!");}

            if(camera.hyper){System.out.println("For this combination of focal length and aperture hyperfocal distance is " +
                    HyperfocalDistance.calculateHD(camera) + " meters");}
        }//close if based on "initList.isEmpty"
        else {
            System.out.println("Provide all required variables");
        }//close else

    }//close process

}//close class
