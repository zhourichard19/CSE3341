package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Cond {
    /**
     * The declaration sequence.
     */
    private Comp comp;

    /**
     * The main statement sequence.
     */
    private Cond c1;

    /**
     * The main statement sequence.
     */
    private Cond c2;

    /**
     * The main statement sequence.
     */
    private TokenKind k1;

    /**
     * The main statement sequence.
     */
    private TokenKind k2;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseCond() {

    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printCond() {
        if (this.k1 == TokenKind.FORWARD_PARENTHESIS) {
            this.comp.printComp();
        } else if (this.k1 == TokenKind.FORWARD_BRACKET) {
            System.out.print("[");
            this.c1.printCond();
            if (this.k2 == TokenKind.AND_OPERATOR) {
                System.out.print(" && ");
            } else {
                System.out.print(" or ");
            }
            this.c2.printCond();
            System.out.print("]");
        } else {
            System.out.print("!");
            this.c1.printCond();
        }
    }

    /**
     * Executes a Core program.
     */
    public boolean execCond() {
        if (this.kind1 == TokenKind.FORWARD_PARENTHESIS) {
            return this.comp.execComp();
        } else if (this.kind1 == TokenKind.FORWARD_BRACKET) {
            boolean c = this.c1.execCond();
            if (this.kind2 == TokenKind.AND_OPERATOR) {
                return c && this.c2.execCond();
            } else {
                return c || this.c2.execCond();
            }
        } else {
            return !this.c1.execCond();
        }
    }

}
