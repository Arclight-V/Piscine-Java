
public class Program {
    public static void main(String args[]) {
        int countChar = 0;
        int bufUnicode[] = new int[65535];
        int bufUnicode2[] = new int[65535];

//        String str = args[1];
        for (int i = 0; i < str.length(); ++i) {
            bufUnicode[str.charAt(i)] += 1;
        }
        for (int j = 0; j < 65535; ++j) {
            if (bufUnicode[j] > 0) {
                bufUnicode2[bufUnicode[j]] = j;
                ++countChar;
            }
        }
        for (int k = 65535; k > 0; --k) {
            if (bufUnicode[k] > 0) {
                bufUnicode2[bufUnicode[k]] = k;
            }
        }
    }
}