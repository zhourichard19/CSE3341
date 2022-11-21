package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Comp {
    /**
     * The declaration sequence.
     */
    private Op o1;

    /**
     * The main statement sequence.
     */
    private CompOp co;
    /**
     * The main statement sequence.
     */
    private Op o2;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseComp() {

    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printComp() {
        System.out.print("(");
        this.o1.printOp();
        this.co.printCompOp();
        this.o2.printOp();
        System.out.print(")");
    }

    /**
     * Executes a Core program.
     */
    public boolean execComp() {
        return this.co.execCompOp(this.o1.execOp(), this.o2.execOp());
    }

}
