package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Id_list for Core.
 * @author Zhijian Yao
 *
 */

public class Id_list {
	private Id s1;
	private Id_list s2;
	private TokenKind kind;
	
	public Id_list() {
		this.s1 = null;
		this.s2 = null;
		this.kind = null;
	}
	
	public void ParseId_list() {
		this.s1 = Id.ParseId();
		this.kind = Tokenizer1.instance().getToken();
		if(this.kind == TokenKind.COMMA) {
			Tokenizer1.instance().skipToken();
			this.s2 = new Id_list();
			this.s2.ParseId_list();			
		}
	}

	public void printId_list() {
		this.s1.printId();
		if(this.kind == TokenKind.COMMA) {
			System.out.print(", ");
			this.s2.printId_list();
		}
		
	}

	public void execId_list() {
		this.s1.execId();
		if(this.kind == TokenKind.COMMA) {
			this.s2.execId_list();
		}	
	}
	
	public void readId_list() {
		int n = Tokenizer1.instance().readData();
		this.s1.setIdVal(n);
		if(this.kind == TokenKind.COMMA) {
			this.s2.readId_list();
		}	
	}
	
	public void writeId_list() {
		System.out.println(this.s1.getIdName() + " = " + this.s1.getIdVal());
		if(this.kind == TokenKind.COMMA) {
			this.s2.writeId_list();
		}	
	}
}
