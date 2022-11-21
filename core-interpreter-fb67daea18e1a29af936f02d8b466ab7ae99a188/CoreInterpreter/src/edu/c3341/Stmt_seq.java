package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Stmt_seq for Core.
 * @author Zhijian Yao
 *
 */

public class Stmt_seq {
	private Stmt s1;
	private Stmt_seq s2;
	private TokenKind kind;
	
	public Stmt_seq() {
		this.s1 = null;
		this.s2 = null;
		this.kind = null;
	}
	
	public void ParseStmt_seq(){
		this.s1 = new Stmt();
		this.s1.ParseStmt();
		this.kind = Tokenizer1.instance().getToken();
		if(this.kind != TokenKind.END && this.kind != TokenKind.ELSE) {
			this.s2 = new Stmt_seq();
			this.s2.ParseStmt_seq();			
		}
	}

	public void printStmt_seq(int n) {
		String spaces = String.format("%" + n +"s", "");
		System.out.print("\n" + spaces);
		this.s1.printStmt(n);
		if(this.kind != TokenKind.END && this.kind != TokenKind.ELSE) {
			this.s2.printStmt_seq(n);
		}	
	}

	public void execStmt_seq() {
		this.s1.execStmt();
		if(this.kind != TokenKind.END && this.kind != TokenKind.ELSE) {
			this.s2.execStmt_seq();
		}
	}
}
