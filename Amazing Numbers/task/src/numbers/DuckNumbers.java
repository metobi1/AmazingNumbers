package numbers;

/**The next task is to determine whether a number is a Duck number.
 A Duck number is a positive number that contains zeroes.
 For example, 3210, 8050896, 70709 are Duck numbers.
 Note that a number with a leading 0 is not a Duck number.
 So, numbers like 035 or 0212 are not Duck numbers.
 Although, 01203 is a Duck, since it has a trailing 0.**/
public class DuckNumbers {
    static String isDuckNum(String numStr) {

        for (int i = 0; i < numStr.length(); i++) {

            if (i > 0 && numStr.charAt(i) == '0') {
                return "true";
            }
        }
       return "false";
    }
}
