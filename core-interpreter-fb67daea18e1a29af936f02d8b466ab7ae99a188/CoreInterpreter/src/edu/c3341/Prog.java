package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Prog for Core.
 * @author Zhijian Yao
 *
 */

public class Prog {
	private Decl_seq s1;
	private Stmt_seq s2;
	
	public Prog() {
		this.s1 = null;
		this.s2 = null;
	}
	
	public void ParseProg(){
		if(!Tokenizer1.instance().isKind(TokenKind.PROGRAM)) {
			Tokenizer1.instance().errMsg("program");
		}
		s1 = new Decl_seq();
		s1.ParseDecl_seq();
		if(!Tokenizer1.instance().isKind(TokenKind.BEGIN)) {
			Tokenizer1.instance().errMsg("begin");
		}
		s2 = new Stmt_seq();
		s2.ParseStmt_seq();
		if(!Tokenizer1.instance().isKind(TokenKind.END)) {
			Tokenizer1.instance().errMsg("end");
		}
	}
	
	public void printProg() {
		System.out.print("program");
		this.s1.printDecl_seq(2);
		System.out.print("\nbegin");
		this.s2.printStmt_seq(2);
		System.out.println("\nend");
	}
	
	public void execProg() {
		this.s1.execDecl_seq();
		this.s2.execStmt_seq();
	}
}
