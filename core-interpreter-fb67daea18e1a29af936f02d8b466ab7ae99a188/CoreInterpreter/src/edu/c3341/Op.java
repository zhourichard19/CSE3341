package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Op for Core.
 * @author Zhijian Yao
 *
 */

public class Op {
	private No s1;
	private Id s2;
	private Exp s3;
	private TokenKind kind;
	
	public Op() {
		this.s1 = null;
		this.s2 = null;
		this.s3 = null;
		this.kind = null;
	}
	
	public void ParseOp() {
		this.kind = Tokenizer1.instance().getToken();	
		if(this.kind == TokenKind.INTEGER_CONSTANT) {
			this.s1 = new No();
			this.s1.ParseNo();
		}else if (kind == TokenKind.IDENTIFIER) {
			this.s2 = Id.ParseId();
		}else if(kind == TokenKind.LEFT_PARENTHESIS){
			Tokenizer1.instance().skipToken();
			this.s3 = new Exp();
			this.s3.ParseExp();
			if(!Tokenizer1.instance().isKind(TokenKind.RIGHT_PARENTHESIS)) {
				Tokenizer1.instance().errMsg(")");
			}
		}else {
			Tokenizer1.instance().errMsg("number or identifier or (");
		}
	}

	public void printOp() {
		if(this.kind == TokenKind.INTEGER_CONSTANT) {
			this.s1.printNo();
		}else if(this.kind == TokenKind.IDENTIFIER) {
			this.s2.printId();
		}else {
			System.out.print("(");
			this.s3.printExp();
			System.out.print(")");
		}
		
		
		
	}

	public int execOp() {
		if(this.kind == TokenKind.INTEGER_CONSTANT) {
			return this.s1.execNo();
		}else if(this.kind == TokenKind.IDENTIFIER) {
			return this.s2.getIdVal();
		}else {
			return this.s3.execExp();
		}
	}		
}
