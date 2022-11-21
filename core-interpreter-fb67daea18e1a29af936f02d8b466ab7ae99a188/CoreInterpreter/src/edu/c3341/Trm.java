package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Trm for Core.
 * @author Zhijian Yao
 *
 */

public class Trm {
	private Op s1;
	private Trm s2;
	private TokenKind kind;
	
	public Trm() {
		this.s1 = null;
		this.s2 = null;
		this.kind = null;
	}
	
	public void ParseTrm() {
		this.s1 = new Op();
		this.s1.ParseOp();
		this.kind = Tokenizer1.instance().getToken();
		if(this.kind == TokenKind.MULTIPLY) {
			Tokenizer1.instance().skipToken();
			this.s2 = new Trm();
			this.s2.ParseTrm();
		}
	}

	public void printTrm() {
		this.s1.printOp();
		if(this.kind == TokenKind.MULTIPLY) {
			System.out.print(" * ");
			this.s2.printTrm();
		}	
	}

	public int execTrm() {
		int r = s1.execOp();
		if(this.kind == TokenKind.MULTIPLY) {
			r += s2.execTrm();
		}
		return r;
	}		
}
