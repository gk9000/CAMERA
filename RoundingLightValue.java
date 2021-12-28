package camera;

class RoundingLightValue {

    static void roundingLightValue (Camera camera) {
        // IF LIGHT VALUE IS OUTSIDE MIN/MAX POSSIBLE EXPOSURE VALUES, ASSIGN IT MIN/MAX POSSIBLE EXPOSURE VALUE
        // AND ACTIVATE UNDER/OVEREXPOSURE WARNING FOR THE USER
        if (camera.getLight() < camera.exposureTable[0]) {
            camera.setLight(camera.exposureTable[0]);
            camera.underexposureWarning = true;
            return;
        }//close if
        else if (camera.getLight()  >= camera.exposureTable[25]) {
            camera.setLight(camera.exposureTable[25]);
            camera.overexposureWarning = true;
        }//close if

        // ROUND LIGHT VALUE TO THE NEAREST POSSIBLE EXPOSURE VALUE
        for (int i = 0; i < 25; i++) {
            if (camera.getLight()>= camera.exposureTable[i] && camera.getLight() < camera.exposureTable[i + 1]) {
                camera.setLight( (camera.getLight() < camera.exposureTable[i] + (camera.exposureTable[i + 1] - camera.exposureTable[i]) / 2) ? camera.exposureTable[i] : camera.exposureTable[i + 1]);
                break;
            }//close if
        }//close for

    }// close roundingLightValue

}//close class
