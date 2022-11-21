package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class No {
    /**
     * The main statement sequence.
     */
    private int num;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseNo() {
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printNo() {
        String x = Integer.toString(this.num);
        System.out.print(x);
    }

    /**
     * Executes a Core program.
     */
    public int execNo() {
        return this.num;
    }

}
