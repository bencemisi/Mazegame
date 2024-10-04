
/**
 * Korábbi leadásnál az indentálást megvalósító osztály
 */
public class IndentationHandler {
    public static int indentation;

    public static void indent(int x) {
        for(int i = 0; i < x; i++) {
            System.out.print("\t");
        }
    }

}
