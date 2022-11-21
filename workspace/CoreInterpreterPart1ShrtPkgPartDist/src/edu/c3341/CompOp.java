package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class CompOp {
    /**
     * The declaration sequence.
     */
    private TokenKind kind;

    /**
     * The main statement sequence.
     */
    private String op;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseCompOp() {

    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printCompOp() {
        System.out.print(this.op);
    }

    /**
     * Executes a Core program.
     */
    public boolean execCompOp(int r1, int r2) {
        if (this.kind == TokenKind.NOT_EQUAL) {
            return r1 != r2;
        } else if (this.kind == TokenKind.LESS_THAN) {
            return r1 < r2;
        } else if (this.kind == TokenKind.LESS_THAN_OR_EQUAL_TO) {
            return r1 <= r2;
        } else if (this.kind == TokenKind.GREATER_THAN) {
            return r1 > r2;
        } else if (this.kind == TokenKind.EQUALITY_TEST) {
            return r1 == r2;
        } else {
            return r1 >= r2;
        }
    }

}
