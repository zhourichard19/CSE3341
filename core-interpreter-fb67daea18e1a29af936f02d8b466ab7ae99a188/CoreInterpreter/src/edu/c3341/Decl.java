package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Decl for Core.
 * @author Zhijian Yao
 *
 */

public class Decl {
	private Id_list s1;
	
	public Decl() {
		this.s1 = null;
	}
	
	public void ParseDecl(){
		if(!Tokenizer1.instance().isKind(TokenKind.INT)) {
			Tokenizer1.instance().errMsg("int");
		}
		this.s1 = new Id_list();
		this.s1.ParseId_list();
		if(!Tokenizer1.instance().isKind(TokenKind.SEMICOLON)) {
			Tokenizer1.instance().errMsg(";");
		}
	}

	public void printDecl() {
		System.out.print("int ");
		this.s1.printId_list();
		System.out.print(";");
		
	}

	public void execDecl() {
		this.s1.execId_list();
		
	}
}
