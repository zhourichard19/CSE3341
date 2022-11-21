package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Assign for Core.
 * @author Zhijian Yao
 *
 */

public class Assign {
	private Id s1;
	private Exp s2;
	
	public Assign() {
		this.s1 = null;
		this.s2 = null;
	}
	public void ParseAssign() {
		this.s1 = Id.ParseId();
		if(!Tokenizer1.instance().isKind(TokenKind.ASSIGNMENT_OPERATOR)) {
			Tokenizer1.instance().errMsg("=");
		}
		this.s2 = new Exp();
		this.s2.ParseExp();
		if(!Tokenizer1.instance().isKind(TokenKind.SEMICOLON)) {
			Tokenizer1.instance().errMsg(";");
		}
	}
	public void printAssign() {
		this.s1.printId();
		System.out.print(" = ");
		this.s2.printExp();
		System.out.print(";");	
	}
	public void execAssign() {
		int result = this.s2.execExp();
		this.s1.setIdVal(result);
	}
}
