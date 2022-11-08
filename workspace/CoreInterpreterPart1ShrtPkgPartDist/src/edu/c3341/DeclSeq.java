package edu.c3341;

/**
 * Responsible for the <prog> non-terminal symbol of the context-free grammar
 * for the Core programming language.
 *
 * @author Wayne Heym
 *
 */
final class DeclSeq {
    /**
     * The declaration sequence.
     */
    private DeclSeq ds;

    /**
     * The main statement sequence.
     */
    private Decl d;
    /**
     * The main statement sequence.
     */
    private int altNo;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseDS() {
        Tokenizer t = Tokenizer1.instance();
        this.altNo = 1;
        this.d = new Decl();
        this.d.parseD();

        if (t.getToken() == TokenKind.INT) {
            this.altNo = 2;
            this.ds = new DeclSeq();
            this.ds.parseDS();
        }
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     */
    public void printDS() {
        if (this.altNo == 1) {
            this.d.printD();
            return;
        } else {
            this.ds.printDS();
            return;
        }
    }

    /**
     * Executes a Core program.
     */
    public void execDS() {
        if (this.altNo == 1) {
            this.d.execD();
            return;
        } else {
            this.ds.execDS();
            return;
        }
    }

}
