package edu.c3341;

import javax.swing.text.Utilities;

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
        Tokenizer t = Tokenizer1.instance();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.INT,
                "Expected \"int\".");
        t.skipToken();
        this.idl = new IdList();
        this.idl.parseidl();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.SEMICOLON,
                "Expected \";\".");
    }

    /**
     * Pretty prints a Core program indented by indent spaces.
     *
     * @param indent
     *            Number of spaces with which to indent Core program.
     */
    public void printD(int indent) {
        Utilities.printSpaces(indent);
        System.out.println("int");
        this.idl.printD(indent + Utilities.indentationFactor());
        Utilities.printSpaces(indent);
        System.out.println(";");
    }

    /**
     * Executes a Core program.
     */
    public void execD() {
        this.idl.execidl();
    }

}
