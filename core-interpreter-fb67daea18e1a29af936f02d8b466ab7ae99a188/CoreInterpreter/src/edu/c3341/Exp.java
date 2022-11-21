package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Exp for Core.
 * @author Zhijian Yao
 *
 */

public class Exp {
	private Trm s1;
	private Exp s2;
	private TokenKind kind;
	
	public Exp() {
		this.s1 = null;
		this.s2 = null;
		this.kind = null;
	}
	
	public void ParseExp() {
		this.s1 = new Trm();
		this.s1.ParseTrm();
		this.kind = Tokenizer1.instance().getToken();
		if(this.kind == TokenKind.PLUS || this.kind == TokenKind.MINUS) {
			Tokenizer1.instance().skipToken();
			this.s2 = new Exp();
			this.s2.ParseExp();
		}	
	}

	public void printExp() {
		this.s1.printTrm();
		if(this.kind == TokenKind.PLUS) {
			System.out.print(" + ");
			this.s2.printExp();
		}else if(this.kind == TokenKind.MINUS) {
			System.out.print(" - ");
			this.s2.printExp();
		}
		
	}

	public int execExp() {
		int r = this.s1.execTrm();
		if(this.kind == TokenKind.PLUS) {
			r += s2.execExp();
		}else if(this.kind == TokenKind.MINUS) {
			r -= s2.execExp();
		}
		return r;
	}
}
