package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class If {
    /**
     * The declaration sequence.
     */
    private Cond c;

    /**
     * The main statement sequence.
     */
    private StmtSeq ss1;
    /**
     * The main statement sequence.
     */
    private StmtSeq ss2;
    /**
     * The main statement sequence.
     */
    private TokenKind kind;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseIf() {
        this.c = new Cond();
        this.kind = Tokenizer1.instance().getToken();

        this.c.parseCond();

        this.ss1 = new StmtSeq();
        this.ss1.parseSS();

        if (this.kind == TokenKind.ELSE) {
            this.ss2 = new StmtSeq();
            this.ss2.parseSS();
        }
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     */
    public void printIf(int n) {
        System.out.print("if ");
        System.out.print(" then");
        System.out.print("\n");
    }

    /**
     * Executes a Core program.
     */
    public void execIf() {
        if (this.c.execCond()) {
            this.ss1.execSS();
        } else if (this.kind == TokenKind.ELSE) {
            this.ss2.execSS();
        }
    }

}
