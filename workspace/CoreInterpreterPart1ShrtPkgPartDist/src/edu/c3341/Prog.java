package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class Prog {
    /**
     * The declaration sequence.
     */
    private DeclSeq ds;

    /**
     * The main statement sequence.
     */
    private StmtSeq ss;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseProg() {
        Tokenizer t = Tokenizer1.instance();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.PROGRAM,
                "Expected \"program\".");
        t.skipToken();
        this.ds = new DeclSeq();
        this.ds.parseDS();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.BEGIN,
                "Expected \"begin\".");
        t.skipToken();
        this.ss = new StmtSeq();
        this.ss.parseSS();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.END,
                "Expected \"end\".");
        t.skipToken();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.EOF,
                "Expected end of program file.");
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     * @param indent
     *            Number of spaces with which to indent Core program.
     */
    public void printProg() {
        System.out.print("program");
        this.ds.printDS(2);
        System.out.print("\nbegin");
        this.ss.printSS(2);
        System.out.println("\nend");
    }

    /**
     * Executes a Core program.
     */
    public void execProg() {
        this.ss.execSS();
    }

}
