package nustracker.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_MAJOR = new Prefix("m/");
    public static final Prefix PREFIX_NUSNETID = new Prefix("id/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    // For create command
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    // For delete command
    public static final Prefix PREFIX_STUDENT = new Prefix("s/");
    public static final Prefix PREFIX_EVENT = new Prefix("ev/");

}
