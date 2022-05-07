package numbers;
/** The next task is to check whether a number is a Palindromic one.
 A Palindromic number is symmetrical; in other words,
 it stays the same regardless of whether we read it from left or right.
 For example, 17371 is a palindromic number. 5 is also a palindromic number.
 1234 is not. If read it from right, it becomes 4321. Add this new property to our program.**/
public class Palindromic {

    static String isPalindromic(String strNum) {

        if (strNum.length() == 1) {
            return "true";
        }
        int halfLen = strNum.length() / 2;
        int len = strNum.length();
        int count = 1;

        for(int i = 0; i < strNum.length(); i++) {
            // compare left side with right side till they meet at the
            // middle
            if (strNum.charAt(i) == strNum.charAt(len - count++)) {
                if (halfLen == count) {
                    break;
                }
            } else {
                return "false";
            }
        }
        return "true";
    }
}
