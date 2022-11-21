package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Loop for Core.
 * @author Zhijian Yao
 *
 */

public class Loop {
	private Cond s1;
	private Stmt_seq s2;
	
	public Loop() {
		this.s1 = null;
		this.s2 = null;
	}
	
	public void ParseLoop() {
		if(!Tokenizer1.instance().isKind(TokenKind.WHILE)) {
			Tokenizer1.instance().errMsg("while");
		}
		this.s1 = new Cond();
		this.s1.ParseCond();
		if(!Tokenizer1.instance().isKind(TokenKind.LOOP)) {
			Tokenizer1.instance().errMsg("loop");
		}
		this.s2 = new Stmt_seq();
		this.s2.ParseStmt_seq();
		if(!Tokenizer1.instance().isKind(TokenKind.END)) {
			Tokenizer1.instance().errMsg("end");
		}
		if(!Tokenizer1.instance().isKind(TokenKind.SEMICOLON)) {
			Tokenizer1.instance().errMsg(";");
		}
	}

	public void printLoop(int n) {
		System.out.print("while ");
		this.s1.printCond();
		System.out.print("loop");
		this.s2.printStmt_seq(n + 2);
		String spaces = String.format("%" + n +"s", "");
		System.out.print("\n" + spaces);
		System.out.print("end;");			
	}
	
	public void execLoop() {
		Boolean c = this.s1.execCond();
		while(c) {
			this.s2.execStmt_seq();
			c = this.s1.execCond();
		}
	}
}
