package edu.c3341;

import java.nio.file.Paths;
import java.util.Scanner;

import components.utilities.Reporter;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Id for Core.
 * @author Zhijian Yao
 *
 */

public class Id {
	private String name;
	private int val;
	private boolean declared;
	private boolean initialized;
	private static Id eIds[] = new Id[20];
	private static int idCount = 0;
	
	private Id(String s) {
		this.name = s;
		this.declared = false;
		this.initialized = false;
	}
	
	public static Id ParseId() {
		String name = Tokenizer1.instance().idName();
		Tokenizer1.instance().skipToken();
		for(int i = 0; i < idCount; i++) {			
			if(eIds[i].getIdName().equals(name)) {
				return eIds[i];
			}
		}  
		Id nId = new Id(name);
		eIds[idCount] = nId;
		idCount++;
		return nId;
	}
	
	public int getIdVal() {
		Reporter.assertElseFatalError(this.declared, "Error: " + this.name + " must get declared!");
		Reporter.assertElseFatalError(this.initialized, "Error: " + this.name + " must get initialized");
		return this.val;
	}
	
	public void setIdVal(int n) {		
		Reporter.assertElseFatalError(this.declared, "Error: " + this.name + " must get declared before assignment!");
		this.val = n;
		this.initialized = true;
	}
	
	public String getIdName() {
		return this.name;
	}

	public void printId() {
		System.out.print(this.name);
		
	}

	public void execId() {
		this.declared = true;	
	}	
}
