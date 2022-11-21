package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Op {
    /**
     * The declaration sequence.
     */
    private No n;

    /**
     * The main statement sequence.
     */
    private Id id;
    /**
     * The main statement sequence.
     */
    private Exp e;

    /**
     * The main statement sequence.
     */
    private TokenKind kind;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseOp() {

    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printOp() {
        if (this.kind == TokenKind.INTEGER_CONSTANT) {
            this.n.printNo();
        } else if (this.kind == TokenKind.IDENTIFIER) {
            this.id.printId();
        } else {
            System.out.print("(");
            this.e.printExp();
            System.out.print(")");
        }
    }

    /**
     * Executes a Core program.
     */
    public void execOp() {

    }

}
