package numbers;

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

    static String isBuzzNumber(String strNum) {

        boolean isDivBySeven = isDivBySeven(strNum);
        boolean isEndSeven = isEndSeven(strNum);

        if (isDivBySeven || isEndSeven) {
            return "true";
        }
        return "false";
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
    static boolean isDivBySeven(String strNum) {

        if (Long.parseLong(strNum) == 7) {
            return true;
        }

        String naturalNumPro = strNum;
        // numbers from 11 to infinity
        while (Math.abs(Long.parseLong(naturalNumPro)) > 10) {

            int len = naturalNumPro.length();
            // get last digit
            int lastDigit = Integer.parseInt(naturalNumPro.substring(len - 1, len));
            // double the int
            lastDigit *= 2;
            // get the other digits
            String remStr = naturalNumPro.substring(0, len - 1);
            // subtract the doubled int from the separated digits
            Long subProd = Math.abs(Long.parseLong(remStr)) - lastDigit;
            // conditions for div by seven
            if (subProd == 0 || subProd == 7 || subProd == -7) {
                return true;
            } else if (Math.abs(subProd) < 10) {
                break;
            }
            // get the remainder int as a string and recurse.
            naturalNumPro = String.valueOf(subProd);
        }

        return false;
    }

    static boolean isEndSeven(String strNum) {
        return strNum.endsWith("7");
    }

    static boolean checkParity( String strNum) {
        return Long.parseLong(strNum) % 2 == 0;
    }


}
