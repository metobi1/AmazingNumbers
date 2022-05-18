package numbers;

import java.text.NumberFormat;
import java.util.*;

import static numbers.Print.*;
import static numbers.BuzzNumbers.*;
import static numbers.DuckNumbers.*;
import static numbers.Palindromic.*;
import static numbers.GapfulNumbers.*;
import static numbers.SpyNumbers.*;
import static numbers.Sunny.*;
import static numbers.Square.*;
import static numbers.JumpingNumbers.*;

/**
 In this stage, we will also remove the limitation on pending properties in a request.
 The program knows how to calculate ten properties of numbers,
 and it would be strange to limit the query to just two properties.
 Let's remove this limitation. Let the program indicate all properties for all numbers in the request.
 **/

public class AmazingNumbers {
    private static final Scanner scanner =
            new Scanner(System.in);
    private static boolean exit = false;
    static final List<String> PROPERTIES =
            List.of(("EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, " +
                    "SPY, SUNNY, SQUARE, JUMPING").split(", "));

    static List<String> mutExclList = null;
    static List<String> errorList = new ArrayList<>();

    static void runAmazingNumbers() {

        printWelcomeMessage();

        while (!exit) {

            printGetRequest();

            List<String> requests = Arrays.asList(scanner.nextLine().split(" "));

            if (isMultParameters(requests)) {
                if (!isValidMultReq(requests)) {
                    continue;
                } else {
                    processMultReqSeries(requests);
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

    static boolean allValid(List<String> requests) {
        List<String> multProps = requests.subList(2, requests.size());
        boolean isProp = true;
        for (String prop : multProps) {
            if (!isProperty(prop)) {
                errorList.add(prop);
                isProp = false;
            }
        }
        return isProp;
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
            } else if (!allValid(requests)) {
                if (errorList.size() > 1) {
                    printErrorMessage(errorList);
                    errorList.clear();
                    return false;
                } else {
                    printErrorMessage(errorList.toString());
                    errorList.clear();
                    return false;
                }
            }
            /*else {
                isValid = isProperty(requests.get(i));
                if(!isValid) {
                    printErrorMessage(requests.get(i).toUpperCase());
                    return isValid;
                }
            }*/
        }
        if (requests.size() >= 4) {
            if (mutExclusive(requests)) {
                printMutExErrorMessage(mutExclList);
                isValid = false;
            }
        }
        return isValid;
    }

    static boolean mutExclusive(List<String> unSortedStr) {

        List<String> sortedStr = new ArrayList<>(unSortedStr);

        int mut1 = 0, mut2 = 0, mut3 = 0;
        StringBuilder mutStr1 = new StringBuilder();
        StringBuilder mutStr2 = new StringBuilder();
        StringBuilder mutStr3 = new StringBuilder();

        for (int i = 0; i < sortedStr.size(); i++) {

            if ("even".equalsIgnoreCase(sortedStr.get(i))) {
                mutStr1.append(sortedStr.get(i) + " ");
                mut1++;
            } else if ("odd".equalsIgnoreCase(sortedStr.get(i))) {
                mutStr1.append(sortedStr.get(i) + " ");
                mut1++;
            } else if ("duck".equalsIgnoreCase(sortedStr.get(i))) {
                mutStr2.append(sortedStr.get(i) + " ");
                mut2++;
            } else if ("spy".equalsIgnoreCase(sortedStr.get(i))) {
                mutStr2.append(sortedStr.get(i) + " ");
                mut2++;
            } else if ("square".equalsIgnoreCase(sortedStr.get(i))) {
                mutStr3.append(sortedStr.get(i) + " ");
                mut3++;
            } else if ("sunny".equalsIgnoreCase(sortedStr.get(i))) {
                mutStr3.append(sortedStr.get(i) + " ");
                mut3++;
            }
        }
        loadErrorList(mut1, mut2, mut3, mutStr1.toString(),
                mutStr2.toString(), mutStr3.toString());
        return  mut1 == 2 || mut2 == 2 || mut3 == 2;
    }

    static void loadErrorList(int mut1, int mut2, int mut3,
                              String mutStr1, String mutStr2, String mutStr3) {
        if (mut1 == 2) {
            String[] str = mutStr1.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut2 == 2) {
            String[] str = mutStr2.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut3 == 2) {
            String[] str = mutStr3.split(" ");
            mutExclList = Arrays.asList(str);
        }
    }

    static void processMultReqFiltered(List<String> requests, boolean propOfProp) {

        long startNum = Long.parseLong(requests.get(0));
        int consecNum = Integer.parseInt(requests.get(1));
        int count = 0;
        String propType = requests.get(2);

        for (long i = startNum;; i++) {

            if (propOfProp) {
                if ("true".equals(hasProp(propType, Long.toString(i))) &&
                        allHaveProp(requests, Long.toString(i))) {
                    String properties = checkProperties(Long.toString(i));
                    printProperties(properties);
                    count++;
                    if (count == consecNum) {
                        break;
                    }
                }
            } else {
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
    }

    static boolean allHaveProp(List<String> requests, String strNum ) {

        List<String> multProps = requests.subList(3, requests.size());

        for (String prop : multProps) {
            if (!"true".equals(hasProp(prop, strNum))) {
                return false;
            }
        }
        return true;
    }

    static void processTwoReq(List<String> requests) {

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

    static void processMultReqSeries(List<String> requests) {

        if (requests.size() == 2) {
            processTwoReq(requests);
        }
        else if (requests.size() == 3) {
            processMultReqFiltered(requests, false);
        }
        else if (requests.size() >= 4) {
            processMultReqFiltered(requests, true);
        }
    }

    static boolean compareTo(String firStr, String secStr) {
        return firStr.compareTo(secStr) == 0;
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
        } else if ("spy".equalsIgnoreCase(propType)) {
            return isSpyNumber(strNum);
        } else if ("sunny".equalsIgnoreCase(propType)) {
            return isSunnyToStr(strNum);
        } else if ("square".equalsIgnoreCase(propType)) {
            return isPerfSquareToStr(strNum);
        }
        return isJumpingToStr(strNum);
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
        if ("true".equals(isSunnyToStr(strNum))) {
            String sunny = "sunny";
            properties.append(String.format(" %s", sunny));
        }
        if ("true".equals(isPerfSquareToStr(strNum))) {
            String square = "square";
            properties.append(String.format(" %s", square));
        }
        if ("true".equals(isJumpingToStr(strNum))) {
            String jumping = "jumping";
            properties.append(String.format(" %s", jumping));
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
                isGapful(strNum), isSpyNumber(strNum),
                isSunnyToStr(strNum), isPerfSquareToStr(strNum),
                isJumpingToStr(strNum));
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
