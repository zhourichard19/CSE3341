package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Assign {
    /**
     * The main statement sequence.
     */
    private Id id;
    /**
     * The main statement sequence.
     */
    private Exp e;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseAssign() {

    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printAssign() {
        this.id.printId();
        System.out.print(" = ");
        this.e.printExp();
        System.out.print(";");
    }

    /**
     * Executes a Core program.
     */
    public void execAssign() {
        this.id.setIdVal(this.e.execExp());
    }

}
