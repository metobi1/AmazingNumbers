package numbers;

import java.util.Scanner;

import static numbers.Print.*;

import static numbers.BuzzNumbers.*;

import static numbers.DuckNumbers.*;

public class AmazingNumbers {
    private static final Scanner scanner =
            new Scanner(System.in);
    static void runAmazingNumbers() {

        System.out.println("Enter a natural number:");
        String numStr = scanner.next();

        if (!isNaturalNumber(numStr)) {
            printErrorMessage();
            return;
        }

        displayProperties(numStr);
    }

    static void displayProperties(String numStr) {

        boolean evenOdd = checkParity(numStr);
        String even;
        String odd;

        if (evenOdd) {
            even = "true";
            odd = "false";
        } else {
            even = "false";
            odd = "true";
        }
        printAll(numStr, even, odd, checkBuzzNumber(numStr),
                isDuckNum(numStr));
    }
    static boolean isNaturalNumber(String numStr) {

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
