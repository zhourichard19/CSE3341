package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Stmt for Core.
 * @author Zhijian Yao
 *
 */

public class Stmt {
	private Assign s1;
	private If s2;
	private Loop s3;
	private In s4;
	private Out s5;
	private TokenKind kind;
	
	public Stmt() {
		this.s1 = null;
		this.s2 = null;
		this.s3 = null;
		this.s4 = null;
		this.s5 = null;
	}
	
	public void ParseStmt(){
		this.kind = Tokenizer1.instance().getToken();
		if(this.kind == TokenKind.IDENTIFIER) {
			this.s1 = new Assign();
			this.s1.ParseAssign();
		}else if(this.kind == TokenKind.IF) {
			this.s2 = new If();
			this.s2.ParseIf();
		}else if(this.kind == TokenKind.WHILE) {
			this.s3 = new Loop();
			this.s3.ParseLoop();
		}else if(this.kind == TokenKind.READ) {
			this.s4 = new In();
			this.s4.ParseIn();
		}else if(this.kind == TokenKind.WRITE) {
			this.s5 = new Out();
			this.s5.ParseOut();
		}else {
			Tokenizer1.instance().errMsg("identifier or if or while or read or write");
		}
	}

	public void printStmt(int n) {
		if(this.kind == TokenKind.IDENTIFIER) {
			this.s1.printAssign();
		}else if(this.kind == TokenKind.IF) {
			this.s2.printIf(n);
		}else if(this.kind == TokenKind.WHILE) {
			this.s3.printLoop(n);
		}else if(this.kind == TokenKind.READ) {
			this.s4.printIn();
		}else {
			this.s5.printOut();
		}
	}

	public void execStmt() {
		if(this.kind == TokenKind.IDENTIFIER) {
			this.s1.execAssign();
		}else if(this.kind == TokenKind.IF) {
			this.s2.execIf();
		}else if(this.kind == TokenKind.WHILE) {
			this.s3.execLoop();
		}else if(this.kind == TokenKind.READ) {
			this.s4.execIn();
		}else {
			this.s5.execOut();
		}
		
	}
}
