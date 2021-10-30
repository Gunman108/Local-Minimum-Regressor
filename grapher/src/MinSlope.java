public class MinSlope {

    //PARAMS
    int order = 2;
    double shiftHoriz = 0;
    double shiftVert = 0;
    double offset = 0.0000001;

    double zeroX;
    double zero;
    double slopey;

    boolean firstId = false;
    boolean upSlope;

    public static void main(String[] args) {
        MinSlope m = new MinSlope();
    }

    public MinSlope(){
        zero(100, -1000000000, 0);
        System.out.println("x = " + zeroX + ", y = " + zero);
    }

    public void zero(double step, double xIn, int layer){
        double x = xIn;
        double xPrime = xIn + offset;
        double y = math(x);
        double yPrime = math(xPrime);
        double slope = (y-yPrime)/(x-xPrime);
        System.out.println(x);
        System.out.println(slope);
        System.out.println(step);
        System.out.println();

        if(!firstId){
            firstId = true;
            slopey = slope;
            if(slope>0){
                upSlope = true;
            } else if(slope == 0){
                zero = y;
                zeroX = x;
                return;
            } else {
                upSlope = false;
            }
        }

        try{
            int counter=0;
            while(counter<100000000){
                xPrime = x+step+offset;
                y = math(x+step);
                yPrime = math(xPrime);
                slope = (y-yPrime)/(x+step-xPrime);
                if(upSlope){
                    if(slope > 0 && slope<slopey){
                        slopey = slope;
                    } else if(slope == 0){
                        zero = y;
                        zeroX = x;
                        return;
                    } else {
                        System.out.println("CALLING UP");
                        zero(step/10, x-step, layer+1);
                        return;
                    }
                } else {
                    if(slope < 0 && slope>slopey){
                        slopey = slope;
                    } else if(slope == 0){
                        zero = y;
                        zeroX = x;
                        return;
                    } else {
                        System.out.println("CALLING DOWN");
                        zero(step/10, x-step, layer+1);
                        return;
                    }
                }
                x += step;
                counter++;
            }
            System.out.println("Done loop");
        } catch (StackOverflowError e){
            e.printStackTrace();
        }
    }

    public double math(double x){
        double back = 0;
        for(int j=order; j>1; j--){
            back += Math.pow(x-shiftHoriz, j);
        }
        back += shiftVert;
        return back;
    }
}
