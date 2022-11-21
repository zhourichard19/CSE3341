package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Cond for Core.
 * @author Zhijian Yao
 *
 */

public class Cond {
	private Comp s1;
	private Cond s2;
	private Cond s3;
	private TokenKind k1;
	private TokenKind k2;
	
	public Cond() {
		this.s1 = null;
		this.s2 = null;
		this.s3 = null;
		this.k1 = null;
		this.k2 = null;
	}
	
	public void ParseCond() {
		this.k1 = Tokenizer1.instance().getToken();
		if(this.k1 == TokenKind.NOT || this.k1 == TokenKind.LEFT_BRACKET) {
			Tokenizer1.instance().skipToken();
			this.s2 = new Cond();
			this.s2.ParseCond();
			this.k2 = Tokenizer1.instance().getToken();
			if(this.k2 == TokenKind.AND_OPERATOR || this.k2 == TokenKind.OR_OPERATOR) {
				Tokenizer1.instance().skipToken();
				this.s3 = new Cond();
				this.s3.ParseCond();
				if(!Tokenizer1.instance().isKind(TokenKind.RIGHT_BRACKET)) {
					Tokenizer1.instance().errMsg("]");
				}
			}		
		}else {
			this.s1 = new Comp();
			this.s1.ParseComp();
		}
	}

	public void printCond() {
		if(this.k1 == TokenKind.LEFT_PARENTHESIS) {
			this.s1.printComp();
		}else if (this.k1 == TokenKind.LEFT_BRACKET) {
			System.out.print("[");
			this.s2.printCond();
			if(this.k2 == TokenKind.AND_OPERATOR) {
				System.out.print(" && ");
			}else {
				System.out.print(" or ");
			}
			this.s3.printCond();
			System.out.print("]");
		}else {
			System.out.print("!");
			this.s2.printCond();
		}
		
	}

	public boolean execCond() {
		if(this.k1 == TokenKind.LEFT_PARENTHESIS) {
			return this.s1.execComp();
		}else if (this.k1 == TokenKind.LEFT_BRACKET) {
			boolean c1 = this.s2.execCond();
			if(this.k2 == TokenKind.AND_OPERATOR) {
				return c1 && this.s3.execCond();
			}else {
				return c1 || this.s3.execCond();
			}
		}else {
			return !this.s2.execCond();
		}
	}
}
