package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Exp {
    /**
     * Parses a Core program into this object.
     *
     */
    public void parseExp() {

    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printExp() {
        this.t.printTerm();
    }

    /**
     * Executes a Core program.
     */
    public int execExp() {
        int r = this.v.execTerm();
        if (this.v == TokenKind.ADDITION) {
            r += this.h.execExp();
        }
        return r;
    }

}
