package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * If for Core.
 * @author Zhijian Yao
 *
 */

public class If {
	private Cond s1;
	private Stmt_seq s2;
	private Stmt_seq s3;
	private TokenKind kind;
	
	public If() {
		this.s1 = null;
		this.s2 = null;
		this.s3 = null;
		this.kind = null;
	}
	
	public void ParseIf() {
		this.s1 = new Cond();
		if(!Tokenizer1.instance().isKind(TokenKind.IF)) {
			Tokenizer1.instance().errMsg("if");
		}
		this.s1.ParseCond();
		if(!Tokenizer1.instance().isKind(TokenKind.THEN)) {
			Tokenizer1.instance().errMsg("then");
		}
		this.s2 = new Stmt_seq();
		this.s2.ParseStmt_seq();
		this.kind = Tokenizer1.instance().getToken();
		if (this.kind == TokenKind.ELSE) {
			Tokenizer1.instance().skipToken();
			this.s3 = new Stmt_seq();
			this.s3.ParseStmt_seq();
		}
		if(!Tokenizer1.instance().isKind(TokenKind.END)) {
			Tokenizer1.instance().errMsg("end");
		}
		if(!Tokenizer1.instance().isKind(TokenKind.SEMICOLON)) {
			Tokenizer1.instance().errMsg(";");
		}
	}

	public void printIf(int n) {
		System.out.print("if ");
		this.s1.printCond();
		System.out.print(" then");
		this.s2.printStmt_seq(n + 2);
		if(this.kind == TokenKind.ELSE) {
			String spaces = String.format("%" + n +"s", "");
			System.out.print("\n" + spaces);
			System.out.print("else");
			this.s3.printStmt_seq(n + 2);			
		}
		String spaces = String.format("%" + n +"s", "");
		System.out.print("\n" + spaces);
		System.out.print("end;");	
	}

	public void execIf() {
		if(this.s1.execCond()) {
			this.s2.execStmt_seq();
		}else if(this.kind == TokenKind.ELSE) {
			this.s3.execStmt_seq();
		}
		
	}
}
