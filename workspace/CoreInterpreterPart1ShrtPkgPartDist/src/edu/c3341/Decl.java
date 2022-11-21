package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Decl {
    /**
     * The declaration sequence.
     */
    private IdList idl;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseD() {
        this.idl = new IdList();
        this.idl.parseIdl();
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     * @param indent
     *            Number of spaces with which to indent Core program.
     */
    public void printD() {
        System.out.print("int ");
        this.idl.printIdl();
        System.out.print(";");
    }

    /**
     * Executes a Core program.
     */
    public void execD() {
        this.idl.execIdl();
    }

}
