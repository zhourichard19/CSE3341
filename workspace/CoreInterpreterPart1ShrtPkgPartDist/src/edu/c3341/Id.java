package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Id {
    /**
     * The declaration sequence.
     */
    private Id idl;

    /**
     * The main statement sequence.
     */
    private Id id;
    /**
     * The main statement sequence.
     */
    private int altNo;

    /**
     * Parses a Core program into this object.
     *
     */
    public static Id parseforDeclSeq() {
        Tokenizer t = Tokenizer1.instance();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.IDENTIFIER,
                "Expected an identifier.");
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printidl() {
        if (this.altNo == 1) {
            this.id.printId();
            return;
        } else {
            this.idl.printidl();
            return;
        }
    }

    /**
     * Executes a Core program.
     */
    public void execidl() {
        if (this.altNo == 1) {
            this.id.execId();
            return;
        } else {
            this.idl.execidl();
            return;
        }
    }

}
