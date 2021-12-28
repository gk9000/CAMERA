package camera;

class SetUsage {

    // CALCULATE LONGEST SAFE SHUTTER SPEED  FOR THE CONNECTED LENS
    // (BASED ON LENS' FOCAL LENGTH)
   static void longestShutterSpeed (Camera camera){

        for(int a : Camera.shutterSpeedRealLifeTable){
            if (camera.getFocalLength() <=a){
                camera.longestRealLifeShutterSpeed=a;
                break;
            }//close if
        }//close foreach

                int temp=camera.getCameraUsage();
                switch(temp) {
                    case 10: camera.longestShutterSpeed = RealLifeByValue.realLifeByValueSpeed(Camera.shutterSpeedRealLifeTable,
                            camera.shutterSpeedTable, camera.longestRealLifeShutterSpeed); break; //HANDHELD
                    case 20: camera.longestShutterSpeed = (RealLifeByValue.realLifeByValueSpeed(Camera.shutterSpeedRealLifeTable,
                            camera.shutterSpeedTable, camera.longestRealLifeShutterSpeed))/4; break; //ON A MONOPOD
                    case 30: camera.longestShutterSpeed = camera.shutterSpeedTable[0]; break; //ON A TRIPOD
                }//close switch
    }//close longestShutterSpeed
}//close class
