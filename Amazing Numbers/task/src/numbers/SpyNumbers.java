package numbers;
/**
 The objective is to get a property that is called a Spy number.
 A number is said to be Spy if the sum of all digits is equal to the product of all digits.**/

public class SpyNumbers {

    static String isSpyNumber(String strNum) {

        long digProd = 1;
        long digSum = 0;

        for (char charValue : strNum.toCharArray()) {
            digProd *= (charValue - '0');
            digSum += (charValue - '0');
        }
        if (digProd == digSum) {
            return "true";
        }
        return "false";
    }
}
