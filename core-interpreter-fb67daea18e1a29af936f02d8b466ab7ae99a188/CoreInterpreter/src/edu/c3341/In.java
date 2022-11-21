package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * In for Core.
 * @author Zhijian Yao
 *
 */

public class In {
	private Id_list s1;
	
	public In() {
		this.s1 = null;
	}
	
	public void ParseIn() {
		this.s1 = new Id_list();
		if(!Tokenizer1.instance().isKind(TokenKind.READ)) {
			Tokenizer1.instance().errMsg("read");
		}
		this.s1.ParseId_list();
		if(!Tokenizer1.instance().isKind(TokenKind.SEMICOLON)){
			Tokenizer1.instance().errMsg(";");
		}
	}

	public void printIn() {
		System.out.print("read ");
		this.s1.printId_list();
		System.out.print(";");	
	}
	
	public void execIn() {
		this.s1.readId_list();
	}
}
