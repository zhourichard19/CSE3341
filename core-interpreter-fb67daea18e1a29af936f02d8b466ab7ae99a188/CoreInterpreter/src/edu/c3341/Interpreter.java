package edu.c3341;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341.
 * Interpreter for Core.
 * @author Zhijian Yao
 *
 */
public final class Interpreter {
	
	/**
     * Private constructor so this utility class cannot be instantiated.
     */
	private Interpreter() {
	}
	
	
	private static void print() {
		Prog program = new Prog();
		try {
			program.ParseProg();
		}catch (RuntimeException e) {
			if(e.getMessage() != null) {
				System.err.println(e.getMessage());
				return;
			}
		}
		program.printProg();
		program.execProg();
	}
	
	private static void doNotPrint() {
		Prog program = new Prog();
		try {
			program.ParseProg();
		}catch (RuntimeException e) {
			if(e.getMessage() != null) {
				System.err.println(e.getMessage());
				return;
			}	
		}
		program.execProg();
	}
	
    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        Scanner progFile = null;
        Scanner inputFile = null;
        
        try {
            progFile = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.err.println("Error opening program file: " + args[0]);
            return;
        }
 
        if(args.length == 1) {
        	System.err.println("Insufficient number of arguments: " + args[0]);
    		progFile.close();
    		return;
        }else if(args.length == 2) {
        	Tokenizer1.create(progFile);
        	if(args[1].equals("print")){
        		print();
        	}else if(args[1].equals("doNotPrint")) {
        		doNotPrint();
        	}else {
        		System.err.println("Invalid Command: " + args[1]);
        		progFile.close();
        		return;
        	}       	
        }else if (args.length == 3){
        	try {
        		inputFile = new Scanner(Paths.get(args[1]));
        	}catch(IOException e) {
        		System.err.println("Error opening program file: " + args[1]);
        		progFile.close();
        		return;
        	}
        	Tokenizer1.create(progFile, inputFile);
        	if(args[2].equals("print")) {
        		print();
        	}else if(args[2].equals("doNotPrint")) {
        		doNotPrint();
        	}else {
        		System.err.println("Invalid Command: " + args[2]);
        		progFile.close();
        		inputFile.close();
        		return;
        	}
        }else {
        	System.err.print("Too many arguments:");
        	for(String arg : args) {
        		System.err.print(" " + arg);
        	}
    		progFile.close();
    		return;
        }      
        /*
         * Close input stream
         */
        progFile.close();
        if(inputFile != null) {
        	inputFile.close();     	
        }
    }

}
