package numbers;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static numbers.Print.*;
import static numbers.BuzzNumbers.*;
import static numbers.DuckNumbers.*;
import static numbers.Palindromic.*;
import static numbers.GapfulNumbers.*;

public class AmazingNumbers {
    private static final Scanner scanner =
            new Scanner(System.in);
    private static boolean exit = false;

    static void runAmazingNumbers() {

        printWelcomeMessage();

        while (!exit) {

            printGetRequest();

            List<String> requests = Arrays.asList(scanner.nextLine().split(" "));

            if (isMultParameters(requests)) {
                if (!isValidMultReq(requests)) {
                    continue;
                } else {
                    processMultReq(requests);
                }
            } else {
                singleRequest(requests.get(0));
            }
        }
    }

    static void singleRequest(String strNum) {

        if ("0".equals(strNum)) {
            exit = true;
            printGoodbye();
        } else if ("".equals(strNum)) {
            printWelcomeInstructions();
        } else if (!isNaturalNumber(strNum)) {
            printErrorMessage(0);
        } else {
            displayProperties(strNum);
        }
    }

    static boolean isValidMultReq(List<String> requests) {

        boolean isValid = true;
        for (int i = 0; i < requests.size(); i++) {
            boolean isNaturalNumber = isNaturalNumber(requests.get(i));

            if (!isNaturalNumber) {
                printErrorMessage(i);
                isValid = false;
            }
        }
        return isValid;
    }

    static void processMultReq(List<String> requests) {
        // get the string to be incremented
        String strStaNum = requests.get(0);

        long staNum = Long.parseLong(strStaNum);
        // get the loop max number
        int consNum = Integer.parseInt(requests.get(1));


        for (int i = 0; i < consNum; i++) {

            String strNum =  String.valueOf(staNum + i);
            String properties = String.format("%s is ", strNum);

            if (checkParity(strNum)) {
                String even = "even";
                properties += String.format("%s", even);
            } else {
                String odd = "odd";
                properties += String.format("%s", odd);
            }
            if ("true".equals(isBuzzNumber(strNum))) {
                String buzz = "buzz";
                properties += String.format(", %s", buzz);
            }
            if ("true".equals(isDuckNum(strNum))) {
                String duck = "duck";
                properties += String.format(", %s", duck);
            }
            if ("true".equals(isPalindromic(strNum))) {
                String palindromic = "palindromic";
                properties += String.format(", %s", palindromic);
            }
            if ("true".equals(isGapful(strNum))) {
                String gapful = "gapful";
                properties += String.format(", %s", gapful);
            }
            System.out.println(properties);
        }
    }

    static boolean isMultParameters(List<String> requests) {
        return requests.size() > 1;
    }

    static void displayProperties(String strNum) {

        String formatted = NumberFormat.getInstance()
                .format(Long.valueOf(strNum));

        boolean evenOdd = checkParity(strNum);
        String even;
        String odd;

        if (evenOdd) {
            even = "true";
            odd = "false";
        } else {
            even = "false";
            odd = "true";
        }
        printAll(formatted, even, odd, isBuzzNumber(strNum),
                isDuckNum(strNum), isPalindromic(strNum), isGapful(strNum));
    }
    static boolean isNaturalNumber(String strNum) {

        try {
            long naturalNum = Long.parseLong(strNum);
            if (naturalNum < 1) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
