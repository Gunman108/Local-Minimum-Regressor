import com.sun.deploy.net.proxy.RemoveCommentReader;

public class Parser {
    private String[] operators = new String[] {"^", "*", "/", "+", "-"};

    private final int POW = 0;
    private final int MULT = 1;
    private final int DIV = 2;
    private final int ADD = 3;
    private final int SUB = 4;

    public Parser(String input) {


            System.out.println(solve("3^4-4-12*8/2", false));

    }

    private int solve(String in, boolean containsNegatives) {
        System.out.println(in);

        boolean[] ops = new boolean[5];
        boolean noOps = true;

        String outString = in;

        // Check which operators are being used in the equation
        for (int i = 0; i < 5; i ++) {
            if (in.contains(operators[i])) {
                ops[i] = true;
                noOps = false;
            }
        }
        if (noOps) return (int)(Double.parseDouble(in)); // Return if there are no more operators

        // Check if the final answer is negative
        if (ops[4] && !ops[0] && !ops[1] && !ops[2] && !ops[3] && containsNegatives) {
            int minuses = 0;
            for (int i = 0; i < in.length(); i ++) {
                if (in.charAt(i) == '-') minuses++;
            }

            if (minuses == 1) return (int)(Double.parseDouble(in));
        }

        // Do operator checks
        if (ops[0]) { // If there is at least 1 exponent
            for (int i = in.length() - 1; i >= 0; i --) { // Check from right to left
                if (in.charAt(i) == '^') {
                    String[] stringValues = getStringValues(i, in);

                    // Cast values to doubles
                    double a = Double.parseDouble(stringValues[0]);
                    double b = Double.parseDouble(stringValues[1]);

                    // Do exponential operation
                    double out = Math.pow(a, b);

                    // Recreate string with new output value
                    outString = stringValues[2];
                    outString += Double.toString(out);
                    outString += stringValues[3];

                    // Check if the equation contained negatives
                    containsNegatives = out < 0;
                    break;
                }
            }
        } else if (ops[1] || ops[2]) {
            for (int i = 0; i < in.length(); i ++) { // Check from left to right
                if (in.charAt(i) == '*') {
                    String[] stringValues = getStringValues(i, in);

                    // Cast the values to doubles
                    double a = Double.parseDouble(stringValues[0]);
                    double b = Double.parseDouble(stringValues[1]);

                    // Do multiplication operation
                    double out = a*b;

                    // Recreate string with new output
                    outString = stringValues[2];
                    outString += Double.toString(out);
                    outString += stringValues[3];

                    // Check if the equation contained negatives
                    containsNegatives = out < 0;
                    break;
                } else if (in.charAt(i) == '/') {
                    String[] stringValues = getStringValues(i, in);

                    // Cast the values to doubles
                    double a = Double.parseDouble(stringValues[0]);
                    double b = Double.parseDouble(stringValues[1]);

                    // Do division operation
                    double out = a/b;

                    // Recreate string with new output
                    outString = stringValues[2];
                    outString += Double.toString(out);
                    outString += stringValues[3];

                    // Check if the equation contained negatives
                    containsNegatives = out < 0;
                    break;
                }
            }
        } else if (ops[3] || ops[4]) {
            for (int i = 0; i < in.length(); i ++) { // Check from left to right
                if (in.charAt(i) == '+') {
                    String[] stringValues = getStringValues(i, in);

                    // Cast the values to doubles
                    double a = Double.parseDouble(stringValues[0]);
                    double b = Double.parseDouble(stringValues[1]);

                    // Do addition operation
                    double out = a+b;

                    // Recreate the string with the new output
                    outString = stringValues[2];
                    outString += Double.toString(out);
                    outString += stringValues[3];

                    // Check if the equation contains negatives
                    containsNegatives = out < 0;
                    break;
                } else if (in.charAt(i) == '-' && i != 0) {
                    String[] stringValues = getStringValues(i, in);

                    // Cast the values to doubles
                    double a = Double.parseDouble(stringValues[0]);
                    double b = Double.parseDouble(stringValues[1]);

                    // Do subtraction operation
                    double out = a-b;

                    // Recreate the string with the new output
                    outString = stringValues[2];
                    outString += Double.toString(out);
                    outString += stringValues[3];

                    // Check if the equation contains negatives
                    containsNegatives = out < 0;
                    break;
                }
            }
        }
        // Return the new equation
        return solve(outString, containsNegatives);
    }

    private String[] getStringValues(int opIndex, String in) {
        // Do 'a' variable check
        int aStart = opIndex - 1;

        for (int j = opIndex - 1; j >= 0; j--) { // Check left value
            // Check for negative number
            if (in.charAt(j) == '-') {
                if (j == 0) {
                    aStart = 0;
                    break;
                }
                // Check for operators indicating that the number is negative
                else if (in.charAt(j - 1) == '^' || in.charAt(j - 1) == '*' || in.charAt(j - 1) == '/' ||
                         in.charAt(j - 1) == '+' || in.charAt(j - 1) == '-')
                {
                    aStart = j;
                    break;
                } else if ((in.charAt(j - 1) == '0' || in.charAt(j - 1) == '1' || in.charAt(j - 1) == '2' || in.charAt(j) == '3' ||
                            in.charAt(j - 1) == '4' || in.charAt(j - 1) == '5' || in.charAt(j - 1) == '6' || in.charAt(j) == '7' ||
                            in.charAt(j - 1) == '8' || in.charAt(j - 1) == '9' || in.charAt(j - 1) == '.')) {
                    aStart = j + 1;
                    break;
                }
            }
            // Check for non-number values
            else if ((in.charAt(j) != '0' && in.charAt(j) != '1' && in.charAt(j) != '2' && in.charAt(j) != '3' &&
                      in.charAt(j) != '4' && in.charAt(j) != '5' && in.charAt(j) != '6' && in.charAt(j) != '7' &&
                      in.charAt(j) != '8' && in.charAt(j) != '9' && in.charAt(j) != '.') || j == 0) {
                aStart = (j == 0) ? j : j + 1;
                break;
            }
        }

        String a = in.substring(aStart, opIndex);

        // Do 'b' variable check
        int bStart = opIndex + 1;
        int bEnd = opIndex + 1;

        for (int j = opIndex + 1; j < in.length(); j++) { // Check left value
           // Check what value b is
            if ((in.charAt(j) != '0' && in.charAt(j) != '1' && in.charAt(j) != '2' && in.charAt(j) != '3' &&
                 in.charAt(j) != '4' && in.charAt(j) != '5' && in.charAt(j) != '6' && in.charAt(j) != '7' &&
                 in.charAt(j) != '8' && in.charAt(j) != '9' && in.charAt(j) != '.' && in.charAt(j) != '-')||
                 j == in.length() - 1 || (in.charAt(j) == '-' && j != opIndex + 1)) {
                bEnd = (j == in.length() - 1) ? j + 1 : j;
                break;
            }
        }

        String b = in.substring(bStart, bEnd);

        // Get substrings surrounding the operation and its values
        String stringStart = in.substring(0, aStart);
        String stringEnd = in.substring(bEnd);

        return new String[] {a, b, stringStart, stringEnd};
    }

    public static void main(String[] args) {
        Parser parser = new Parser("y=2*x+3");
    }
}