package camera;

/*
 IF REQUIRED APERTURE IS OUTSIDE THIS LENS' RANGE, MIN/MAX POSSIBLE VALUES ARE ASSIGNED TO SHUTTER SPEED AND EXPOSURE,
 TOGETHER WITH ACTIVATING UNDER/OVEREXPOSURE WARNINGS.
 EXCEPTION MAKES POSSIBLE EXITING THIS METHOD FROM WITHIN "IF" BLOCK. IT PROPAGATES VIA "CALCULATE" METHOD OF "CALCULATION" CLASS
 TO "CAMERA" CLASS, WITHOUT EXECUTING CODE OF "CALCULATE" METHOD THAT IS LOCATED AFTER THIS METHOD CALL.
*/

class OverAndUnder {

    static private Object Exception;

    static void overAndUnder (Camera camera) throws Throwable {

        if(camera.getLight()/ camera.longestShutterSpeed<=camera.thisLensApertureTable[0]){
            camera.shutterSpeed=camera.longestShutterSpeed;
            camera.aperture=camera.thisLensApertureTable[0];
            camera.underexposureWarning=true;
            throw (Throwable) Exception;

        } else if (camera.getLight()/ camera.shutterSpeedTable[camera.shutterSpeedTable.length-1]>=
                camera.thisLensApertureTable[camera.thisLensApertureTable.length-1]){
            camera.shutterSpeed=camera.shutterSpeedTable[camera.shutterSpeedTable.length-1];
            camera.aperture=camera.thisLensApertureTable[camera.thisLensApertureTable.length-1];
            camera.overexposureWarning=true;
            throw (Throwable) Exception;

        }//close if
    }//close method
}//close class
