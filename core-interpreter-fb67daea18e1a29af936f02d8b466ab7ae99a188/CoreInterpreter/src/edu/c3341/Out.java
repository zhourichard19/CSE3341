package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Out for Core.
 * @author Zhijian Yao
 *
 */

public class Out {
	private Id_list s1;
	
	public Out() {
		this.s1 = null;
	}
	public void ParseOut() {
		this.s1 = new Id_list();
		if(!Tokenizer1.instance().isKind(TokenKind.WRITE)) {
			Tokenizer1.instance().errMsg("write");
		}
		this.s1.ParseId_list();
		if(!Tokenizer1.instance().isKind(TokenKind.SEMICOLON)){
			Tokenizer1.instance().errMsg(";");
		}
	}
	public void printOut() {
		System.out.print("write ");
		this.s1.printId_list();
		System.out.print(";");		
	}
	
	public void execOut() {
		this.s1.writeId_list();
	}
}
