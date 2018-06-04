package controller;

public class Utils {
    public static String encloseInQuotes (String string) {
        return ("\"" + string + "\"");
    }
    public static String encloseInLikeFormat (String string) {
        return ("'%" + string + "%'");
    }
}
