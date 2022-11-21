package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * No for Core.
 * @author Zhijian Yao
 *
 */

public class No {
	private int num;
	public void ParseNo() {
		if(Tokenizer1.instance().getToken() != TokenKind.INTEGER_CONSTANT) {
			Tokenizer1.instance().errMsg("integer");
		}
		this.num = Tokenizer1.instance().intVal();
		Tokenizer1.instance().skipToken();		
	}
	public void printNo() {
		System.out.print(Integer.toString(this.num));		
	}
	
	public int execNo() {
		return num;
	}
}
