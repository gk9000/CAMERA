package camera;

class Calculation {

    static private  Object Exception;

      static void calculate (Camera camera) throws Throwable {

            // "OVERANDUNDER" METHOD MAY THROW FORCED EXCEPTION THAT PROPAGATES DOWN TO THIS METHOD CALL
            // AND FURTHER DOWN TO "CAMERA" CLASS WHERE IT IS CAUGHT. IN THIS CASE ANY FURTHER CODE IN "CALCULATION"
            // CLASS IS NOT EXECUTED
            OverAndUnder.overAndUnder(camera);


        long a = camera.getLight()/camera.bestAperture;
        for(int i=camera.longestShutterSpeed; i<=camera.shutterSpeedTable[camera.shutterSpeedTable.length-1]; i=i*2){

            if (i==a){
                camera.shutterSpeed=i;
                camera.aperture=camera.bestAperture;
                // THROWING EXCEPTION IN ORDER TO EXIT THE METHOD FROM WITHIN "IF" STATEMENT
                // AND PASS CONTROL BACK TO "PROCESS" METHOD OF "CAMERA" CLASS:
                throw (Throwable) Exception;
            }//close if
        }//close for

        camera.counter++;


        //PRIMARY SWITCH (CAMERA MODE):
        int temp=camera.getCameraMode();
        switch(temp){

            //start case full program of primary switch:
            case 100:  //starts secondary switch (counter):
            {switch (camera.counter){
                case 1: for(int j= camera.thisLensApertureTable[3]; j<=camera.thisLensApertureTable[camera.thisLensApertureTable.length-1]; j*=2)
                {camera.bestAperture=j; calculate(camera);};//close "for" in "case 1" of secondary switch
                    break; // close case 1
                case 2: camera.bestAperture=camera.thisLensApertureTable[1]; calculate(camera); break;
                case 3: camera.bestAperture=camera.thisLensApertureTable[0]; calculate(camera); camera.counter=0; break;
            }//close secondary switch (counter)
            }//close case "full program" of primary switch


            //starts case landscape of primary switch:
            case 200: //starts secondary switch (counter):
            {switch (camera.counter){
                case 1: camera.bestAperture=camera.thisLensApertureTable[camera.thisLensApertureTable.length-3]; calculate(camera); break;
                case 2: camera.bestAperture=camera.thisLensApertureTable[camera.thisLensApertureTable.length-1]; calculate(camera);  break;
                case 3: for(int j= camera.thisLensApertureTable[camera.thisLensApertureTable.length-4]; j>=camera.thisLensApertureTable[0]; j/=2)
                {camera.bestAperture=j; calculate(camera);};//close "for" in "case 3" of secondary switch
                    camera.counter=0; break; // close case 3
            }//close secondary switch (counter)
            }//close case "landscape" of primary switch


            //start case action of primary switch:
            case 300: // starts secondary switch (counter):
            {switch (camera.counter){
                case 1: for(int j= camera.thisLensApertureTable[3]; j<=camera.thisLensApertureTable[camera.thisLensApertureTable.length-1]; j*=2)
                {camera.bestAperture=j; calculate(camera);};//close "for" in "case 1" of secondary switch
                    break; // close case 1
                case 2: camera.bestAperture=camera.thisLensApertureTable[1]; calculate(camera); break;
                case 3: camera.bestAperture=camera.thisLensApertureTable[0]; calculate(camera); camera.counter=0; break;
            }//close secondary switch (counter)
            }//close case "action" of primary switch


            //starts case portrait of primary switch:
            case 400: //starts secondary switch (counter):
            {switch (camera.counter){
                case 1: camera.bestAperture=camera.thisLensApertureTable[2]; calculate(camera);  break;
                case 2: camera.bestAperture=camera.thisLensApertureTable[0]; calculate(camera);  break;
                case 3: for(int j= camera.thisLensApertureTable[3]; j<=camera.thisLensApertureTable[camera.thisLensApertureTable.length-1]; j*=2)
                {camera.bestAperture=j; calculate(camera);};//close "for" in "case 3" of secondary switch
                    camera.counter=0; break; // close case 3
            }//close secondary switch (counter)
            }//close case "portrait" of primary switch

        }//close primary switch (cameraMode)

    }//close calculate
}//close class
