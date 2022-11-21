package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Comp_op for Core.
 * @author Zhijian Yao
 *
 */

public class Comp_op {
	private TokenKind kind;
	private String op;
	
	public Comp_op() {
		this.kind = null;
		this.op = null;
	}
	public void ParseComp_op() {
		this.kind = Tokenizer1.instance().getToken();
		if(this.kind == TokenKind.NOT_EQUAL) {
			this.op = " != ";
		}else if (this.kind == TokenKind.EQUALITY_TEST) {
			this.op = " == ";
		}else if(this.kind == TokenKind.SMALLER_THAN) {
			this.op = " < ";
		}else if(this.kind == TokenKind.SMALLER_OR_EQUAL) {
			this.op = " <= ";
		}else if(this.kind == TokenKind.GREATER_THAN) {
			this.op = " > ";
		}else if(this.kind == TokenKind.GREATER_OR_EQUAL) {
			this.op = " >= ";
		}else {
			Tokenizer1.instance().errMsg("valid comp operator");
		}
		Tokenizer1.instance().skipToken();		
	}
	public void printComp_op() {
		System.out.print(this.op);		
	}
	public boolean execComp_op(int r1, int r2) {
		if(this.kind == TokenKind.NOT_EQUAL) {
			return r1 != r2;
		}else if (this.kind == TokenKind.EQUALITY_TEST) {
			return r1 == r2;
		}else if(this.kind == TokenKind.SMALLER_THAN) {
			return r1 < r2;
		}else if(this.kind == TokenKind.SMALLER_OR_EQUAL) {
			return r1 <= r2;
		}else if(this.kind == TokenKind.GREATER_THAN) {
			return r1 > r2;
		}else {
			return r1 >= r2;		
		}
	}
		
}
