public class Main {

    double min = 123;
    double minX = 456;

    //PARAMS
    int order = 5;
    double shiftHoriz = 0;
    double shiftVert = 4;

    public static void main(String[] args) {
        Main m = new Main();
    }

    public Main(){
        double step = .1;
        if(order % 2 == 0){
            double xIn = -10000000;
            min(step, xIn, 0);
            System.out.print("MINIMUM " + min + " @ ");
            System.out.printf("%f\n", minX);
        } else {
            double xIn = -10000000;
            max(step, xIn, 0);
            System.out.print("MAXIMUM " + min + " @ ");
            System.out.printf("%f\n", minX);
            xIn = minX;
            step = 0.1;
            min(step, xIn, 0);
            System.out.print("MINIMUM " + min + " @ ");
            System.out.printf("%f\n", minX);
        }
    }

    public double min(double step, double xIn, int layer){
        double x=xIn;
        minX = x;
        min = math(x);
        try {
            while (true) {
                double value = math(x+step);
                if (value < min) {
                    min = value;
                } else {
                    layer++;
                    minX = x-step;
                    min(step / 10, x - step, layer);
                    break;
                }
                x = x + step;
            }
        } catch(StackOverflowError e){}
        return step;
    }

    public double max(double step, double xIn, int layer){
        double x=xIn;
        min = math(x);
        minX = xIn;
        try {
            while (true) {
                double value = math(x+step);
                if (value > min) {
                    min = value;
                } else {
                    layer++;
//                    minX = x-step;
                    max(step / 10, x - step, layer);
                    break;
                }
                x = x + step;
            }
        } catch(StackOverflowError e){}
        return step;
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
