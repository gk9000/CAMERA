package camera;

class SpeedFactor {
    static void findSpeedFactor(Camera camera, int filmSpeed) {
        // CALCULATE "SPEEDFACTOR" VARIABLE ACCORDING TO FILM SPEED INPUT:
        switch (filmSpeed) {
            case 100:
                camera.speedFactor=32;
                break;
            case 200:
                camera.speedFactor=16;
                break;
            case 400:
                camera.speedFactor=8;
                break;
            case 800:
                camera.speedFactor=4;
                break;
            case 1600:
                camera.speedFactor=2;
                break;
            case 3200:
                camera.speedFactor=1;
                break;
            default:
                System.out.println("Provide one of the following film speeds: 100, 200, 400, 800, 1600, 3200");
        }//close switch
    }//close method
}//close class
