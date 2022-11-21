
package edu.c3341;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341. An interpreter for Core.
 *
 * @author Wayne Heym
 *
 */
public final class CoreInterpreter {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoreInterpreter() {
    }

    private static void print() {
        Prog program = new Prog();
        try {
            program.parseProg();
        } catch (RuntimeException e) {
            if (e.getMessage() != null) {
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
            program.parseProg();
        } catch (RuntimeException e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
                return;
            }
        }
        program.execProg();
    }

    /**
     * Number of expected command-line arguments.
     */
    private static final int ARG_NUMBER = 3;

    /**
     * Print flag string.
     */
    private static final String PRINT_STRING = "print";

    /**
     * Do not print flag string.
     */
    private static final String DO_NOT_PRINT_STRING = "doNotPrint";

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Student solutions are not required to check for correct command-line
         * arguments; they are only required to work properly when the proper
         * command-line arguments are supplied.
         */
        if (args.length != ARG_NUMBER) {
            System.err.println("Error: Expecting " + ARG_NUMBER
                    + " arguments: exactly 2 file arguments "
                    + "followed by a flag regarding printing,");
            System.err.println("but seeing only " + args.length + " arguments");
            return;
        }
        if ((!args[2].equals(PRINT_STRING))
                && (!args[2].equals(DO_NOT_PRINT_STRING))) {
            System.err.println(
                    "Error: Expecting the third argument to be either \""
                            + PRINT_STRING + "\" or \"" + DO_NOT_PRINT_STRING
                            + "\" but seeing \"" + args[2] + '"');
            return;
        }
        boolean shouldPrint = args[2].equals(PRINT_STRING);
        Scanner progFile, inputFile;
        try {
            progFile = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.err.println("Error opening file: " + args[0]);
            return;
        }
        try {
            inputFile = new Scanner(Paths.get(args[1]));
        } catch (IOException e) {
            System.err.println("Error opening file: " + args[1]);
            progFile.close();
            return;
        }
        if (args.length == 2) {
            Tokenizer1.create(progFile);
            if (args[1].equals("print")) {
                print();
            } else if (args[1].equals("doNotPrint")) {
                doNotPrint();
            } else {
                System.err.println("Invalid Command: " + args[1]);
                progFile.close();
                return;
            }
        } else if (args.length == 3) {
            try {
                inputFile = new Scanner(Paths.get(args[1]));
            } catch (IOException e) {
                System.err.println("Error opening program file: " + args[1]);
                progFile.close();
                return;
            }
            Tokenizer1.create(progFile, inputFile);
            if (args[2].equals("print")) {
                print();
            } else if (args[2].equals("doNotPrint")) {
                doNotPrint();
            } else {
                System.err.println("Invalid Command: " + args[2]);
                progFile.close();
                inputFile.close();
                return;
            }
        } else {
            System.err.print("Too many arguments:");
            for (String arg : args) {
                System.err.print(" " + arg);
            }
            progFile.close();
            return;
        }
        /*
         * Close input stream
         */
        progFile.close();
        if (inputFile != null) {
            inputFile.close();
        }
    }

}