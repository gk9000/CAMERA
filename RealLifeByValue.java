package camera;

class RealLifeByValue {

    // SERVICE METHOD TO GET CORRESPONDING VALUES BETWEEN 2 ARRAYS
    // ( PROVIDE arr1[i] VALUE, GET arr2[i] VALUE )

    // FOR SHUTTER SPEEDS:
   static  int realLifeByValueSpeed(int [] source, int[] dest, int a) {
        int i;
        for(i=0; i<source.length-1; i++){
            if(source[i]==a){
                break;
            }//close if
        }//close for
        return dest[i];
    }//close method

    // FOR APERTURES:
    static  double realLifeByValueAperture(int[] source, double[] dest, int a) {
        int i;
        for(i=0; i<source.length-1; i++){
            if(source[i]==a){
                break;
            }//close if
        }//close for
        return dest[i];
    }//close method
}//close class
