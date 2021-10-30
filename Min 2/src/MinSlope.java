import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.XMLFormatter;

public class MinSlope {

    //PARAMS
    int order = 2; //EVERYTIME ORDER IS INCREASED, ANOTHER LAYER NEEDS TO BE ADDED TO REACH THE FLATLINE
    double shiftHoriz = 0;
    double shiftVert = 0;

    double offset = 0.1;

    double error = 0.0000000001;

    ArrayList<ArrayList> layers = new ArrayList<ArrayList>();

    double zeroX;
    double zero;

    boolean firstId = false;
    boolean upSlope;

    public static void main(String[] args) {
        MinSlope m = new MinSlope();
    }

    public MinSlope() {
//        plus(2.5, 2.50);
        zero(-1000000, 0);
        for (int x = 0; x < layers.size(); x++) {
            System.out.println(layers.get(x));
            System.out.println();
        }
        System.out.println(zeroX);
        touchUp(zeroX);
    }

    public void zero(double xIn, int level) {
        System.out.println(level);
        ArrayList<Double> nextLayer = new ArrayList<Double>();
        if (level == 0) {
            System.out.println(layers);
            ArrayList<ArrayList> layer = new ArrayList<ArrayList>();
            double x = xIn;
            for (int i = 0; i < 4 * order; i++) {
                ArrayList<Double> point = new ArrayList<Double>();
                point.add(x);
                point.add(math(x));
                x += offset;
                layer.add(point);
            }
            layers.add(layer);
            for (int i = 1; i < layer.size(); i++) {
                double y1 = (double) layer.get(i - 1).get(1);
                double y2 = (double) layer.get(i).get(1);
                double x1 = (double) layer.get(i - 1).get(0);
                double x2 = (double) layer.get(i).get(0);
                double slope = (y1 - y2) / (x1 - x2);
                nextLayer.add(slope);
            }
        } else {
            ArrayList<Double> layer = layers.get(layers.size() - 1);
            for (int i = 1; i < layer.size(); i++) {
                double term1 = layer.get(i - 1);
                double term2 = layer.get(i);
                double nextTerm = term1 - term2;
                nextLayer.add(nextTerm);
            }
        }
        layers.add(nextLayer);
        if (level == order - 1) {
            if (level == 0) {
                double slope = nextLayer.get(0);
                double centerSteps = xIn / slope;
                zeroX = xIn + (offset * centerSteps);
            } else {
                ArrayList<Double> previosLayer = layers.get(layers.size() - 2);
                double previousSlope1 = previosLayer.get(0);
                double dif = 0;
                for (int x = 0; x < nextLayer.size(); x++) {
                    dif += nextLayer.get(x);
                }
                dif /= nextLayer.size();
                double centerSteps = Math.abs(previousSlope1 / dif);
                zeroX = xIn + (offset * centerSteps);
            }
            return;
        } else {
            zero(xIn, level + 1);
        }
    }

    public boolean equals(double d1, double d2){
        if(d1 > d2-error && d1 < d2+error){
            return true;
        } else {
            return false;
        }
    }

//    public double plus(double d1, double d2) {
//        boolean subtract = false;
//        if (d1 < 0 && d2 >= 0 || d2 < 0 && d1 >= 0) {
//            subtract = true;
//        }
//        String added = Double.toString(d1);
//        String adder = Double.toString(d2);
//        System.out.println(added);
//        String[] addedarr = added.split(".");
//        String[] adderarr = adder.split(".");
//        String frontDec;
//        if (adderarr[0].length() > addedarr[0].length()) {
//            frontDec = adderarr[0];
//            for (int x = 1; x < addedarr[0].length() + 1; x--) {
//                int v1 = adderarr[0].charAt(adderarr[0].length() - x);
//                int v2 = addedarr[0].charAt(addedarr[0].length() - x);
//                int v3;
//                if (subtract) {
//                    v3 = v1 - v2;
//                } else {
//                    v3 = v1 + v2;
//                }
//                frontDec = frontDec.replace(frontDec.charAt(frontDec.length() - x), (char) v3);
//            }
//        } else {
//            frontDec = addedarr[0];
//            for (int x = 1; x < adderarr[0].length() + 1; x--) {
//                int v1 = addedarr[0].charAt(addedarr[0].length() - x);
//                int v2 = adderarr[0].charAt(adderarr[0].length() - x);
//                int v3 = v1 + v2;
//                frontDec = frontDec.replace(frontDec.charAt(frontDec.length() - x), (char) v3);
//            }
//        }
//        String backDec;
//        if (adderarr[1].length() > addedarr[1].length()) {
//            backDec = adderarr[1];
//            for (int x = 0; x < adderarr[1].length(); x++) {
//                int v1 = addedarr[1].charAt(x);
//                int v2;
//                int v3 = 0;
//                try {
//                    v2 = adderarr[1].charAt(x);
//                    if (subtract) {
//                        v3 = v1 - v2;
//                    } else {
//                        v3 = v1 + v2;
//                    }
//                } catch (Exception e) {
//                    if (subtract) {
//                        if (x == 0) {
//                            frontDec.replace(frontDec.charAt(frontDec.length() - 1), (char) (frontDec.charAt(frontDec.length() - 1) - 1));
//                            v2 = 10;
//                            v3 = v1 - v2;
//                        } else {
//                            backDec.replace(backDec.charAt(x - 1), (char) (backDec.charAt(x - 1) - 1));
//                            v2 = 10;
//                            v3 = v1 - v2;
//                        }
//                    } else {
//                        v3 = v1;
//                    }
//                }
//                backDec.replace(backDec.charAt(x), (char) v3);
//            }
//        } else {
//            backDec = addedarr[1];
//            for (int x = 0; x < addedarr[1].length(); x++) {
//                int v1 = adderarr[1].charAt(x);
//                int v2;
//                int v3 = 0;
//                try {
//                    v2 = addedarr[1].charAt(x);
//                    if (subtract) {
//                        v3 = v1 - v2;
//                    } else {
//                        v3 = v1 + v2;
//                    }
//                } catch (Exception e) {
//                    v3 = v1;
//                }
//                backDec.replace(backDec.charAt(x), (char) v3);
//            }
//        }
//
//        String backStr = frontDec + "." + backDec;
//        double back = Double.parseDouble(backStr);
//        return back;
//    }

        public double math (double x){
            double back = 0;
            for (int j = order; j > 1; j--) {
                back += Math.pow(x - shiftHoriz, j);
            }
            back += shiftVert;
            return back;
        }

        public void touchUp(double x){


    }
}
