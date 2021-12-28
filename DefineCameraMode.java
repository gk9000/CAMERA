package camera;

// SET PREFERRED BEST APERTURE ACCORDING TO SELECTED CAMERA MODE
class DefineCameraMode {

    static void defineCameraMode(Camera camera){

         int temp=camera.getCameraMode();
         switch (temp) {

             case 100: //FULL PROGRAM MODE
                 camera.bestAperture = camera.thisLensApertureTable[2];
                 break;

             case 200: // LANDSCAPE MODE
                 camera.bestAperture = camera.thisLensApertureTable[camera.thisLensApertureTable.length - 2];
                 camera.hyper=true; // TURN ON HYPERFOCAL DISTANCE CALCULATOR FOR THIS MODE
                 break;

             case 300: // ACTION MODE
                 camera.bestAperture = camera.thisLensApertureTable[2];
                 camera.longestShutterSpeed = camera.longestShutterSpeed * 4;

             // TWO "IF" BLOCKS THAT CANCEL LONGEST SHUTTER SPEED MODIFICATION MADE IN "ON A MONOPOD" AND "ON A TRIPOD" USAGES:
             if (camera.getCameraUsage()==20) {
                 camera.longestShutterSpeed = camera.longestShutterSpeed*4;
             } else if (camera.getCameraUsage()==30) {
                 for (int b : Camera.shutterSpeedRealLifeTable) {
                     if (camera.getFocalLength() <= b) {
                         camera.longestRealLifeShutterSpeed = b;
                         camera.longestShutterSpeed=RealLifeByValue.realLifeByValueSpeed(Camera.shutterSpeedRealLifeTable,camera.shutterSpeedTable, b)*4;
                         break;
                     }//close second if
                 }//close foreach
             }//close first if
                 break; //end of case 300

             case 400: // PORTRAIT MODE
                 camera.bestAperture = camera.thisLensApertureTable[1];
                 break;

         }//close switch
     }//close method
}//close class



