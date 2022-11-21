package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class StmtSeq {
    /**
     * The declaration sequence.
     */
    private StmtSeq ss;

    /**
     * The main statement sequence.
     */
    private Stmt s;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseSS() {
        this.s = new Stmt();
        this.s.parseS();
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     */
    public void printSS(int n) {
        System.out.print("\n");
        this.s.printS(n);
    }

    /**
     * Executes a Core program.
     */
    public void execSS() {
        this.s.execS();
    }

}
