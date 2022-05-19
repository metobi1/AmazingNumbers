package numbers;

/**
 In number theory, a happy number is a number that reaches 1
 after a sequence during which the number is replaced by the sum of each digit squares.
 For example, 13 is a happy number, as 12 + 32 = 10 which leads to 12 + 02 = 1.
 **/

public class Happy {

    static boolean isHappyNum(String strNum) {

        StringBuilder strBildr = new StringBuilder(strNum);

        do {
            long addNums = 0;
            for (int i = 0; i < strBildr.length(); i++) {
                long sqrNum = strBildr.charAt(i) - '0';
                addNums += (sqrNum * sqrNum);
            }
            strBildr = new StringBuilder();
            strBildr.append(addNums);
        } while (strBildr.length() > 1);

        return "1".equals(strBildr.toString());
    }

    static String isHappyToStr(String strNum) {
        if (isHappyNum(strNum)) {
            return "true";
        }
        return "false";
    }
}
