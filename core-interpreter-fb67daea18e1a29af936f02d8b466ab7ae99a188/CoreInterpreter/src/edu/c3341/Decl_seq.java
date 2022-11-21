package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Decl_seq for Core.
 * @author Zhijian Yao
 *
 */

public class Decl_seq {
	private Decl s1;
	private Decl_seq s2;
	private TokenKind kind;
	
	public Decl_seq() {
		this.s1 = null;
		this.s2 = null;
		this.kind = null;
	}
	
	public void ParseDecl_seq(){
		this.s1 = new Decl();
		this.s1.ParseDecl();
		this.kind = Tokenizer1.instance().getToken();
		if(this.kind != TokenKind.BEGIN) {
			this.s2 = new Decl_seq();
			this.s2.ParseDecl_seq();			
		}
	}

	public void printDecl_seq(int n) {
		String spaces = String.format("%" + n +"s", "");
		System.out.print("\n" + spaces);
		this.s1.printDecl();
		if(this.kind != TokenKind.BEGIN) {
			this.s2.printDecl_seq(n);
		}
	}

	public void execDecl_seq() {
		this.s1.execDecl();
		if(this.kind != TokenKind.BEGIN) {
			this.s2.execDecl_seq();
		}
		
	}
}
