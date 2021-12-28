package camera;

class ShutterSpeedConvertion {


    static String shutterSpeedConvertion(Camera camera, int a){

        switch (a){
            case -30: camera.shutterSpeedHuman = "30 sec"; break;
            case -15: camera.shutterSpeedHuman = "15 sec"; break;
            case -8: camera.shutterSpeedHuman = "8 sec"; break;
            case -4: camera.shutterSpeedHuman = "4 sec"; break;
            case -2: camera.shutterSpeedHuman = "2 sec"; break;
            case -1: camera.shutterSpeedHuman = "1 sec"; break;
            case 2: camera.shutterSpeedHuman = "1/2 sec"; break;
            case 4: camera.shutterSpeedHuman = "1/4 sec"; break;
            case 8: camera.shutterSpeedHuman = "1/8 sec"; break;
            case 15: camera.shutterSpeedHuman = "1/15 sec"; break;
            case 30: camera.shutterSpeedHuman = "1/30 sec"; break;
            case 60: camera.shutterSpeedHuman = "1/60 sec"; break;
            case 125: camera.shutterSpeedHuman = "1/125 sec"; break;
            case 250: camera.shutterSpeedHuman = "1/250 sec"; break;
            case 500: camera.shutterSpeedHuman = "1/500 sec"; break;
            case 1000: camera.shutterSpeedHuman = "1/1000 sec"; break;
            case 2000: camera.shutterSpeedHuman = "1/2000 sec"; break;
            case 4000: camera.shutterSpeedHuman = "1/4000 sec"; break;
            case 8000: camera.shutterSpeedHuman = "1/8000 sec"; break;
        }//close switch
        return camera.shutterSpeedHuman;
    }//close method
}//close class
