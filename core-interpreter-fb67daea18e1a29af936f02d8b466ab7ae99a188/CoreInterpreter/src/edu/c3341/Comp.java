package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Comp for Core.
 * @author Zhijian Yao
 *
 */

public class Comp {
	private Op s1;
	private Comp_op s2;
	private Op s3;
	
	public Comp() {
		this.s1 = null;
		this.s2 = null;
		this.s3 = null;
	}
	
	public void ParseComp() {
		if(!Tokenizer1.instance().isKind(TokenKind.LEFT_PARENTHESIS)) {
			Tokenizer1.instance().errMsg("(");
		}
		this.s1 = new Op();
		this.s1.ParseOp();
		this.s2 = new Comp_op();
		this.s2.ParseComp_op();
		this.s3 = new Op();
		this.s3.ParseOp();
		if(!Tokenizer1.instance().isKind(TokenKind.RIGHT_PARENTHESIS)) {
			Tokenizer1.instance().errMsg(")");
		}
	}

	public void printComp() {
		System.out.print("(");
		this.s1.printOp();
		this.s2.printComp_op();
		this.s3.printOp();
		System.out.print(")");
	}

	public boolean execComp() {
		int r1 = this.s1.execOp();
		int r2 = this.s3.execOp();
		return this.s2.execComp_op(r1,r2);
	}
}
