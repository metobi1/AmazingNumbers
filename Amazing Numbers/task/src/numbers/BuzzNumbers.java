package numbers;

import java.util.Scanner;

import static numbers.Print.*;
/** Objectives
 The program is to check whether the given natural number is a buzz number.

 Steps required:
 Ask a user to enter a natural number.
 If the number is not natural, print an error message.
 Calculate and print the parity of the number.
 Check whether is the number is a Buzz number and print the explanation.
 Finish the program after printing the message.
 Natural numbers are whole numbers starting from 1. **/
public class BuzzNumbers {
    private static String numStr;
    private static final Scanner scanner =
            new Scanner(System.in);
    private static boolean isBuzzNumber = true;


    public static boolean isBuzzNumber() {
        return isBuzzNumber;
    }

    public static String getNumStr() {
        return numStr;
    }

    static void runBuzzNumbers() {

        if (!isNaturalNumber()) {
            printErrorMessage();
            return;
        }
        printParity();
        checkBuzzNumber();
    }

    static void checkBuzzNumber() {

        boolean isDivBySeven = isDivBySeven();
        boolean isEndSeven = isEndSeven();

        if (isDivBySeven && isEndSeven) {
            printAll("Div/End");

        } else if (isEndSeven) {
            printAll("EndSeven");

        } else if (isDivBySeven) {
            printAll("divSeven");
        }
        isBuzzNumber = false;
        printAll("NotDiv/End");
    }
    // implementing div by 7 algorithm to meet maximum requirement
        /* First Remove the last digit of the number;
        Next Multiply the removed digit by 2 and subtract it from the remaining number.
        If the result of the subtraction can be divided by 7,
        then the initial number is divisible by 7.
        You can apply the whole sequence multiple times until you get
        a relatively small/easy-to-interpret subtraction result.
        For example, take 196. We remove the last digit and get 19.
        We subtract 12 (the removed digit multiplied by 2) to get 7.
        Since the last left digit is 7, the number is multiple of 7. So 196 is a Buzz number!*/
    static boolean isDivBySeven() {

        if (Integer.parseInt(numStr) == 7) {
            return true;
        }

        String naturalNumPro = numStr;
        // numbers from 11 to infinity
        while (Integer.parseInt(naturalNumPro) > 10) {

            int len = naturalNumPro.length();
            // get last digit
            int lastDigit = Integer.parseInt(naturalNumPro.substring(len - 1, len));
            // double the int
            lastDigit *= 2;
            // get the other digits
            String remStr = naturalNumPro.substring(0, len - 1);
            // subtract the doubled int from the separated digits
            int subProd = Integer.parseInt(remStr) - lastDigit;
            // conditions for div by seven
            if (subProd == 0 || subProd == 7 || subProd == -7) {
                return true;
            } else if (subProd < 10) {
                break;
            }
            // get the remainder int as a string and recurse.
            naturalNumPro = String.valueOf(subProd);
        }

        return false;
    }

    static boolean isEndSeven() {
        return numStr.endsWith("7");
    }

    static boolean checkParity() {
        return Integer.parseInt(numStr) % 2 == 0;
    }

    static boolean isNaturalNumber() {

        System.out.println("Enter a natural number:");
        numStr = scanner.next();

        try {
            int naturalNum = Integer.parseInt(numStr);
            if (naturalNum < 1) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
