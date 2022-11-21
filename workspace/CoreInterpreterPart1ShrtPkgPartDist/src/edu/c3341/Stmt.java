package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Stmt {
    private Assign s1;

    private If s2;

    private Loop s3;

    private TokenKind kind;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseS() {
        this.kind = Tokenizer1.instance().getToken();
        if (this.kind == TokenKind.IDENTIFIER) {
            this.s1 = new Assign();
            this.s1.parseAssign();
        } else {
            Tokenizer1.instance()
                    .errMsg("Should be an if write assign or stmt");
        }
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     * @param indent
     *            Number of spaces with which to indent Core program.
     */
    public void printS(int n) {
        if (this.kind == TokenKind.IDENTIFIER) {
            this.s1.printAssign();
        } else if (this.kind == TokenKind.IF) {
            this.s2.printIf(n);
        } else {
            this.s3.printLoop(n);
        }
    }

    /**
     * Executes a Core program.
     */
    public void execS() {
        if (this.kind == TokenKind.IDENTIFIER) {
            this.s1.execAssign();
        } else if (this.kind == TokenKind.IF) {
            this.s2.execIf();
        } else {
            this.s3.execLoop();
        }
    }

}
