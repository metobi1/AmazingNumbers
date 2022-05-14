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
import static numbers.SpyNumbers.*;

public class AmazingNumbers {
    private static final Scanner scanner =
            new Scanner(System.in);
    private static boolean exit = false;
    static final List<String> PROPERTIES =
            List.of("EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY"
                    .split(", "));

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

            if (i < 2) {
                isValid = isNaturalNumber(requests.get(i));
                if (!isValid) {
                    printErrorMessage(i);
                    return isValid;
                }
            } else {
                isValid = isProperty(requests.get(i));
                if(!isValid) {
                    printErrorMessage(requests.get(i).toUpperCase());
                    return isValid;
                }
            }
        }
        return isValid;
    }

    static void processMultReqFiltered(List<String> requests) {

        long startNum = Long.parseLong(requests.get(0));
        int consecNum = Integer.parseInt(requests.get(1));
        int count = 0;
        String propType = requests.get(2);

        for (long i = startNum;; i++) {
            if ("true".equals(hasProp(propType, Long.toString(i)))) {
                String properties = checkProperties(Long.toString(i));
                printProperties(properties);
                count++;
                if (count == consecNum) {
                    break;
                }
            }
        }
    }

    static void processMultReq(List<String> requests) {

        if (requests.size() == 3) {
            processMultReqFiltered(requests);
            return;
        }

        // get the string to be incremented
        String strStaNum = requests.get(0);

        long staNum = Long.parseLong(strStaNum);
        // get the loop max number
        int consNum = Integer.parseInt(requests.get(1));

        for (int i = 0; i < consNum; i++) {

            String strNum =  String.valueOf(staNum + i);
            String properties = checkProperties(strNum);
            printProperties(properties);
        }
    }

    static String hasProp(String propType, String strNum) {
        if ("buzz".equalsIgnoreCase(propType)) {
            return isBuzzNumber(strNum);
        } else if ("duck".equalsIgnoreCase(propType)) {
            return isDuckNum(strNum);
        } else if ("palindromic".equalsIgnoreCase(propType)) {
            return isPalindromic(strNum);
        } else if ("gapful".equalsIgnoreCase(propType)) {
            return isGapful(strNum);
        }else if ("even".equalsIgnoreCase(propType)) {
            return isEven(strNum);
        } else if ("odd".equalsIgnoreCase(propType)) {
            return isOdd(strNum);
        }
        return isSpyNumber(strNum);
    }

    static String checkProperties(String strNum) {

        StringBuilder properties =
                new StringBuilder(String.format("%s is", strNum));

        if (checkParity(strNum)) {
            String even = "even";
            properties.append(String.format(" %s", even));
        } else {
            String odd = "odd";
            properties.append(String.format(" %s", odd));
        }
        if ("true".equals(isBuzzNumber(strNum))) {
            String buzz = "buzz";
            properties.append(String.format(" %s", buzz));
        }
        if ("true".equals(isDuckNum(strNum))) {
            String duck = "duck";
            properties.append(String.format(" %s", duck));
        }
        if ("true".equals(isPalindromic(strNum))) {
            String palindromic = "palindromic";
            properties.append(String.format(" %s", palindromic));
        }
        if ("true".equals(isGapful(strNum))) {
            String gapful = "gapful";
            properties.append(String.format(" %s", gapful));
        }
        if ("true".equals(isSpyNumber(strNum))) {
            String spy = "spy";
            properties.append(String.format(" %s", spy));
        }
        return properties.toString();
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
                isDuckNum(strNum), isPalindromic(strNum),
                isGapful(strNum), isSpyNumber(strNum));
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

    static boolean isProperty(String property) {
        return PROPERTIES.contains(property.toUpperCase());
    }
}
