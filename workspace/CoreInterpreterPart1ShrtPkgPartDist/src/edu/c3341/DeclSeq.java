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
    private TokenKind kind;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parseDS() {
        this.d = new Decl();
        this.d.parseD();
        this.kind = Tokenizer1.instance().getToken();
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     */
    public void printDS(int n) {
        System.out.print("\n");
        this.d.printD();
    }

    /**
     * Executes a Core program.
     */
    public void execDS() {
        this.d.execD();
        if (this.kind != TokenKind.BEGIN) {
            this.ds.execDS();
        }
    }

}
