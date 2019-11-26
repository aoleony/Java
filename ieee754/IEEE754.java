package ieee754;

import java.util.Scanner;

public class IEEE754 {

    private String bits;
    private float number;
    private float signBits; // true: 0(+), false: 1(-)
    private float exponent = 0; // -127 to 126 decimal form
    private float mantissa = 0;// 23 bits decimal form

    public IEEE754(String bits) {// bits is in IEEE754 form (no spaces)
        this.signBits = Float.parseFloat("" + bits.charAt(0));
        this.exponent = binaryToFloat(bits.substring(1, 9));
        this.mantissa = binaryToFloat(bits.substring(9, 32));
        double a = Math.pow(-1, signBits);
        double b = (1 + this.mantissa / Math.pow(2, 23));
        double c = (Math.pow(2, this.exponent - 127));
        float f = 0.1234553333333f;
        this.number = (float) (a * b * c);
        this.bits = bits;// 0 and 1
    }

    public IEEE754(float number) { // number is in decimal form
        this.number = number;// decimal
        this.exponent = findExponent(number);
        this.mantissa = findMantissa(number);
        this.signBits = number >= 0 ? 0 : 1;
        this.bits = (int) (signBits) + floatToBinary(exponent, true) + floatToBinary(mantissa, false);
    }

    public float getDecimalValue() {// returns decimal value
        return number;
    }

    public String getIEEE754Value() { // returns IEEE 754 value
        return bits;
    }

    public static float findMantissa(float number) {
        float exponent = findExponent(number);
        float placeHolder = (float) Math.abs(number / Math.pow(2, exponent - 127)); // 0.something
        String mantissa2 = "";
        
        for (int i = 1; i <= 23; i++) { // repeat 23 times to get the mantissa 
            placeHolder = (placeHolder - (placeHolder >= 1 ? 1 : 0)) * 2;
            mantissa2 = mantissa2 + (placeHolder >= 1 ? "1" : "0");
        }

        return binaryToFloat(mantissa2);
    }

    public static float findExponent(float number) { // find the exponent from number
        float exponent = 127; //173.7
        number = Math.abs(number);

        do {
            if (number >= 1) {// 1 - infinity
                number = number / 2;
                exponent++;
            } else if (number >= 0 && number < 1) {// 0 - 0.9999
                number = number * 2;
                exponent--;
            }
            if (exponent == 0 || exponent == 256) {
                break;
            }
        } while (!(number <= 2 && number >= 1));// quit if number is [1, 2]

        return exponent;
    }

    public static float binaryToFloat(String binary) { //converting binary to decimal
        char[] binarys = binary.toCharArray();
        float output = 0;

        for (int i = binarys.length - 1; i >= 0; i--) {
            if (binarys[i] == '1') {
                output += Math.pow(2, binarys.length - i - 1);
            }
        }
        return output;
    }

    public static String floatToBinary(float number, boolean expoOrManti) { // true: exponent false: mantissa
        String output = "";//10, 5
        
        while (number >= 1) {
            output = Integer.toString((int) (number % 2)) + output;
            number = (int) number / 2;
        }
        while (output.length() < (expoOrManti ? 8 : 23)) {
            output = "0" + output;
        }
        return output;
    }

    public static void main(String[] args) {
        IEEE754 a = new IEEE754(173.7f);
        String b = a.getIEEE754Value();
        System.out.println("input for a: " + a.getDecimalValue());
        System.out.println("result: " + b);
        IEEE754 c = new IEEE754(b);
        System.out.println("a after conversion: " + c.getDecimalValue());
        System.out.printf("%10.2s", "Hi there!");
    }
}
