package numbers;

import static java.lang.Math.*;
/**
 A number is a Jumping number if the adjacent digits inside the number differ by 1.
 The difference between 9 and 0 is not considered as 1.
 Single-digit numbers are considered Jumping numbers.
 For example, 78987, and 4343456 are Jumping numbers, but 796 and 89098 are not.
 **/
public class JumpingNumbers {

    static boolean isJumpingNum(String strNum) {

        int prevInt = 0;

        for (int i = 0; i < strNum.length(); i++) {

            if (i == 0) {
                prevInt = strNum.charAt(i) - '0';
                continue;
            }
            int diff = abs((strNum.charAt(i) - '0') - prevInt);
            if (diff == 1) {
                prevInt = strNum.charAt(i) - '0';
            } else {
                return false;
            }
        }
        return true;
    }

    static String isJumpingToStr(String strNum) {
        if (isJumpingNum(strNum)) {
            return "true";
        }
        return "false";
    }
}
