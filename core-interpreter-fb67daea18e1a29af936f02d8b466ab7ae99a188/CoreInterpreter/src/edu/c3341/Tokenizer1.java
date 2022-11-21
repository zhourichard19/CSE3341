/**
 * Shortened package name for Wayne Heym's CSE 3341 course.
 */
package edu.c3341;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



/**
 * Tokenizer for a "subset" of the Core language.
 *
 * @author Zhijian Yao
 * @author Wayne Heym (author of skeleton)
 *
 */
final class Tokenizer1 implements Tokenizer {

    /**
     * According to the singleton pattern, either null or a reference to the
     * single instance of Tokenizer.
     */
    private static Tokenizer singleInstance = null;

    /**
     * A Set containing each of the strict single character tokens of a "subset"
     * of the Core language.
     */
    private static final Set<Character> STRICT_SINGLE_CHARACTER_TOKENS;

    static {
        STRICT_SINGLE_CHARACTER_TOKENS = new HashSet<>();
        String source = ";,[]()+-*";
        for (int i = 0; i < source.length(); i++) {
            STRICT_SINGLE_CHARACTER_TOKENS.add(source.charAt(i));
        }
    }

    /**
     * A Set containing each of the characters that serve as prefixes of
     * delimiters (a.k.a. special symbols) in a "subset" of the Core language.
     */
    private static final Set<Character> DELIMITER_PREFIX_CHARACTERS;

    static {
        DELIMITER_PREFIX_CHARACTERS = new HashSet<>();
        String source = "=|&!<>";
        for (int i = 0; i < source.length(); i++) {
            DELIMITER_PREFIX_CHARACTERS.add(source.charAt(i));
        }
    }

    /**
     * A Set containing each of the characters that serve as delimiters in the
     * Core language.
     */
    private static final Set<Character> DELIMITER_CHARACTERS;

    static {
        DELIMITER_CHARACTERS = new HashSet<>(STRICT_SINGLE_CHARACTER_TOKENS);
        DELIMITER_CHARACTERS.addAll(DELIMITER_PREFIX_CHARACTERS);
    }

    /**
     * States for the finite state automaton simulated by this tokenizer.
     */
    private enum State {

        /**
         * State START is shown in the diagram with "Ready for 1st char. of next
         * token" inside its oval and "(starting state)" written above it.
         */
        START,

        /**
         * State ERROR is shown in the diagram with "Gath. Err." inside its
         * oval.
         */
        ERROR,

        /**
         * State GATHERING_UPPERCASE is shown in the diagram with "Gath. UC"
         * inside its oval.
         */
        GATHERING_UPPERCASE,

        /**
         * State ID_GATHERING_DIGITS is shown in the diagram with "Finish ID"
         * inside its oval.
         */
        ID_GATHERING_DIGITS,

        /**
         * State DIGIT_GATHERING is shown in the diagram with "Gath. digits"
         * inside its oval.
         */
        DIGIT_GATHERING,

        /**
         * State GATHERING_LOWER_CASE is shown in the diagram with "Gath. lc"
         * inside its oval.
         */
        GATHERING_LOWER_CASE,

        /**
         * State EQ is shown in the diagram with "one =" inside its oval.
         */
        EQ,

        /**
         * State VERT_BAR is shown in the diagram with "one |" inside its oval.
         */
        VERT_BAR,
    	
    	/**
         * State AND is shown in the diagram with "one &" inside its oval.
         */
        AND,
    	
    	/**
         * State NOT is shown in the diagram with "one !" inside its oval.
         */
        NOT,
    	
    	/**
         * State GREATER is shown in the diagram with "one >" inside its oval.
         */
        GREATER,
        
        /**
         * State SMALLER is shown in the diagram with "one <" inside its oval.
         */
        SMALLER;
    	
    	
    }

    /**
     * Head of contents to be tokenized.
     */
    private String head;

    /**
     * Position in head at which tokenizing should continue.
     */
    private int pos;

    /**
     * Tail of contents to be tokenized.
     */
    private Iterator<String> tail;

    /**
     * The current token.
     */
    private StringBuilder token;

    /**
     * The current token kind.
     */
    private TokenKind kind;

    /**
     *  The inputfile scanner
     */
    private Scanner inputFile;
    
    static final Map<String, TokenKind> Reserved_Words_Map = new HashMap<String, TokenKind>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
    	    put("program", TokenKind.PROGRAM);
    	    put("begin", TokenKind.BEGIN);
    	    put("end", TokenKind.END);
    	    put("int", TokenKind.INT);
    	    put("if", TokenKind.IF);
    	    put("then", TokenKind.THEN);
    	    put("else", TokenKind.ELSE);
    	    put("while",TokenKind.WHILE);
    	    put("loop",TokenKind.LOOP);
    	    put("read", TokenKind.READ);
    	    put("write", TokenKind.WRITE);
    }};
    
    /**
     * According to the singleton pattern, make the default constructor private.
     */
    private Tokenizer1() {
    }

    /**
     * If no instance of Tokenizer yet exists, create one; in any case, return a
     * reference to the single instance of the Tokenizer.
     *
     * @param itString
     *            the Iterator<String> from which tokens will be extracted;
     *            Tokenizer expects itString never to deliver an empty String or
     *            a String containing whitespace.
     * @return the single instance of the Tokenizer
     *
     */
    public static Tokenizer create(Iterator<String> itString) {
        if (Tokenizer1.singleInstance == null) {
            Tokenizer1 fresh = new Tokenizer1();
            fresh.tail = itString;
            fresh.head = "";
            fresh.token = new StringBuilder();
            fresh.findToken();
            Tokenizer1.singleInstance = fresh;
        }
        return Tokenizer1.singleInstance;
    }
    
    /**
     * If no instance of Tokenizer yet exists, create one; in any case, return a
     * reference to the single instance of the Tokenizer.
     *
     * @param itString
     *            the Iterator<String> from which tokens will be extracted;
     *            Tokenizer expects itString never to deliver an empty String or
     *            a String containing whitespace.
     * @param inputFile
     *            inputfile for read data
     * @return the single instance of the Tokenizer
     *
     */
    public static Tokenizer create(Iterator<String> itString, Scanner inputFile) {
        if (Tokenizer1.singleInstance == null) {
            Tokenizer1 fresh = new Tokenizer1();
            fresh.tail = itString;
            fresh.head = "";
            fresh.token = new StringBuilder();
            fresh.findToken();
            fresh.inputFile = inputFile;
            Tokenizer1.singleInstance = fresh;
        }
        return Tokenizer1.singleInstance;
    }

    /**
     * Return either null or the single instance of the Tokenizer, if it exists.
     *
     * @return either null or the single instance of the Tokenizer, if it exists
     */
    public static Tokenizer instance() {
        return Tokenizer1.singleInstance;
    }

    /**
     * Given a delimiter prefix character, return the DFA's new state.
     *
     * @param i
     *            a delimiter prefix character
     * @return new state
     */
    private static State newStateForDelimeterPrefixCharacter(int i) {
        State result;
        switch (i) {
            case '=': {
                result = State.EQ;
                break;
            }
            case '|': {
                result = State.VERT_BAR;
                break;
            }case '&':{
            	result = State.AND;
            	break;
            }case '!':{
            	result = State.NOT;
            	break;
            }case '>':{
            	result = State.GREATER;
            	break;
            }case '<':{
            	result = State.SMALLER;
            	break;
            }
            default: {
                /* Should only occur if precondition is violated. */
                assert false : ""
                        + "Violation of: i is a delimeter prefix character";
                result = State.ERROR;
                break;
            }
        }
        return result;
    }

    /**
     * Given a strict single-character token, return its kind.
     *
     * @param i
     *            a strict single-character token
     * @return the kind of i
     */
    private static TokenKind kindOfStrictSingleCharacterToken(int i) {
        TokenKind result;
        switch (i) {
            case ';': {
                result = TokenKind.SEMICOLON;
                break;
            }case ',':{
            	result = TokenKind.COMMA;
            	break;
            }case '[':{
            	result = TokenKind.LEFT_BRACKET;
            	break;
            }case ']':{
            	result = TokenKind.RIGHT_BRACKET;
            	break;
            }case ')':{
            	result = TokenKind.RIGHT_PARENTHESIS;
            	break;
            }case '(':{
            	result = TokenKind.LEFT_PARENTHESIS;
            	break;
            }case '+':{
            	result = TokenKind.PLUS;
            	break;
            }case '-':{
            	result = TokenKind.MINUS;
            	break;
            }case '*':{
            	result = TokenKind.MULTIPLY;
            	break;
            }
            default: {
                /* Should only occur if precondition is violated. */
                assert false : ""
                        + "Violation of: i is a strict single-character token";
                result = TokenKind.ERROR;
                break;
            }
        }
        return result;
    }


    /**
     * Update this to find the next Core language token. Do so by simulating the
     * behavior of a particular deterministic finite state automaton (DFA or
     * FSA) beginning in its Start state. This method is too long. Checkstyle
     * reports that its length exceeds 150 lines. Because, as a simple way of
     * describing the implementation of a finite state machine, I recommend the
     * use of a switch statement to our students, I find this approach
     * acceptable, despite the length of this method. --Wayne Heym
     */
    private void findToken() {
        if (this.kind != TokenKind.EOF) {
            if (this.head.length() <= this.pos && this.tail.hasNext()) {
                this.pos = 0;
                this.head = this.tail.next();
            }
            if (this.pos < this.head.length()) {
                boolean seeking = true;
                State state = State.START;
                this.token.setLength(0);
                while (seeking) {
                    switch (state) {
                        case START: {
                            char current = this.head.charAt(this.pos);
                            this.token.append(current);
                            this.pos++;
                            if (STRICT_SINGLE_CHARACTER_TOKENS
                                    .contains(current)) {
                                this.kind = kindOfStrictSingleCharacterToken(
                                        current);
                                seeking = false;
                            } else if (DELIMITER_PREFIX_CHARACTERS
                                    .contains(current)) {
                                state = newStateForDelimeterPrefixCharacter(
                                        current);
                            } else if ('a' <= current && current <= 'z') {
                                state = State.GATHERING_LOWER_CASE;
                            } else if ('A' <= current && current <= 'Z') {
                                state = State.GATHERING_UPPERCASE;
                            } else if ('0' <= current && current <= '9') {
                                state = State.DIGIT_GATHERING;
                            } else {
                                state = State.ERROR;
                            }
                            break;
                        }
                        case GATHERING_LOWER_CASE: {
                        	if (this.head.length() <= this.pos) {
                        		TokenKind key = Tokenizer1.Reserved_Words_Map.get(this.token.toString());
                                if(key == null) {
                                	state = State.ERROR;
                                }else {
                                	this.kind = key;
                                    seeking = false;
                                }       	
                                    
                            } else {
                                char current = this.head.charAt(this.pos);
                                if ('a' <= current && current <= 'z') {
                                    this.token.append(current);
                                    this.pos++;
                                } else if ('A' <= current && current <= 'Z'
                                        || '0' <= current && current <= '9') {
                                    state = State.ERROR;
                                }else {
                                	TokenKind key = Tokenizer1.Reserved_Words_Map.get(this.token.toString());
                                    if(key == null) {
                                    	state = State.ERROR;
                                    }else {
                                    	this.kind = key;
                                        seeking = false;
                                    }   
                                }
                            }
                            break;
                        }
                        case DIGIT_GATHERING: {
                            if(this.head.length() <= this.pos) {
                            	this.kind = TokenKind.INTEGER_CONSTANT;
                            	seeking = false;
                            }else {
                            	char current = this.head.charAt(this.pos);
                            	if('0' <= current && current <= '9') {
                            		this.token.append(current);
                            		this.pos++;
                            	}else if ('a' <= current && current <= 'z'
                            			|| 'A' <= current && current <= 'Z') {
                            		state = State.ERROR;
                            	}else {
                            		this.kind = TokenKind.INTEGER_CONSTANT;
                            		seeking = false;
                            	}
                            }

                            break;
                        }
                        case GATHERING_UPPERCASE: {
                            if(this.head.length() <= this.pos) {
                            	this.kind = TokenKind.IDENTIFIER;
                            	seeking = false;
                            }else {
                            	char current = this.head.charAt(this.pos);
                            	if('A' <= current && current <= 'Z') {
                            		this.token.append(current);
                            		this.pos++;
                            	}else if('0' <= current && current <= '9') {
                            		state = State.ID_GATHERING_DIGITS;
                            	}else if('a' <= current && current <= 'z') {
                            		state = State.ERROR;
                            	}else {
                            		this.kind = TokenKind.IDENTIFIER;
                            		seeking = false;
                            	}
                            }

                            break;
                        }
                        case ID_GATHERING_DIGITS: {
                            if(this.head.length() <= this.pos) {
                            	this.kind = TokenKind.IDENTIFIER;
                            	seeking = false;
                            }else {
                            	char current = this.head.charAt(this.pos);
                            	if('0' <= current && current <= '9') {
                            		this.token.append(current);
                            		this.pos++;
                            	}else if ('a' <= current && current <= 'z'
                            			|| 'A' <= current & current <= 'Z') {
                            		state = State.ERROR;
                            	}else {
                            			this.kind = TokenKind.IDENTIFIER;
                            			seeking = false;
                            	}
                            }

                            break;
                        }
                        case EQ: {
                        	if (this.head.length() <= this.pos) {
                        		this.kind = TokenKind.ASSIGNMENT_OPERATOR;
                        		seeking = false;                   
                                break;
                            } 
                        	char current = this.head.charAt(this.pos);
                            if(current == '=') {                         	
                            	this.token.append(current);
                            	this.pos++;
                            	this.kind = TokenKind.EQUALITY_TEST;
                            }else {
                            	this.kind = TokenKind.ASSIGNMENT_OPERATOR;                         	
                            }
                            seeking = false;
                            break;
                        }
                        
                        case VERT_BAR: {
                        	if (this.head.length() <= this.pos) {
                        		state = State.ERROR;               
                                break;
                            }
                        	char current = this.head.charAt(this.pos);
                        	if(current == '|') {
                        		this.token.append(current);
                            	this.pos++;
                        		this.kind = TokenKind.OR_OPERATOR;
                        		seeking = false;
                        	}else {
                        		state = State.ERROR;
                        	}
                            break;
                        }
                        
                        case AND: {
                        	if (this.head.length() <= this.pos) {
                        		state = State.ERROR;               
                                break;
                            }
                        	char current = this.head.charAt(this.pos);
                        	if(current == '&') {
                        		this.token.append(current);
                            	this.pos++;
                        		this.kind = TokenKind.AND_OPERATOR;
                        		seeking = false;
                        	}else {
                        		state = State.ERROR;
                        	}
                            break;
                        }
                        
                        case NOT: {
                        	if (this.head.length() <= this.pos) {
                        		this.kind = TokenKind.NOT;
                        		seeking = false;                   
                                break;
                            } 
                            char current = this.head.charAt(this.pos);
                            if(current == '=') {                         	
                            	this.token.append(current);
                            	this.pos++;
                            	this.kind = TokenKind.NOT_EQUAL;
                            }else {
                            	this.kind = TokenKind.NOT;                         	
                            }
                            seeking = false;
                            break;
                        }
                        
                        case GREATER: {
                        	if (this.head.length() <= this.pos) {
                        		this.kind = TokenKind.GREATER_THAN;
                        		seeking = false;                   
                                break;
                            } 
                            char current = this.head.charAt(this.pos);
                            if(current == '=') {                         	
                            	this.token.append(current);
                            	this.pos++;
                            	this.kind = TokenKind.GREATER_OR_EQUAL;
                            }else {
                            	this.kind = TokenKind.GREATER_THAN;                         	
                            }
                            seeking = false;
                            break;
                        }
                        
                        case SMALLER: {
                        	if (this.head.length() <= this.pos) {
                        		this.kind = TokenKind.SMALLER_THAN;
                        		seeking = false;                   
                                break;
                            } 
                            char current = this.head.charAt(this.pos);
                            if(current == '=') {                         	
                            	this.token.append(current);
                            	this.pos++;
                            	this.kind = TokenKind.SMALLER_OR_EQUAL;
                            }else {
                            	this.kind = TokenKind.SMALLER_THAN;                         	
                            }
                            seeking = false;
                            break;
                        }
                        
                      
                
                        case ERROR: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.ERROR;
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if (DELIMITER_CHARACTERS.contains(current)) {
                                    this.kind = TokenKind.ERROR;
                                    seeking = false;
                                } else {
                                    this.token.append(current);
                                    this.pos++;
                                }
                            }
                            break;
                        }
                        default: {
                            /*
                             * It's a programming error if control reaches here:
                             */
                            assert false : "Programming error: "
                                    + "unhandled state in simulation of FSA";
                            state = State.ERROR;
                            break;
                        }
                    }
                }
            } else {
                this.kind = TokenKind.EOF;
                this.token.setLength(0);
            }

        }
    }

    /**
     * Return the kind of the current token.
     *
     * @return the kind of the current token
     */
    @Override
    public TokenKind getToken() {
        return this.kind;
    }

    /**
     * Skip current token.
     */
    @Override
    public void skipToken() {
        this.findToken();
    }

    /**
     * Return the integer value of the current INTEGER_CONSTANT token.
     *
     * @return the integer value of the current INTEGER_CONSTANT token
     */
    @Override
    public int intVal() {
        return Integer.parseInt(this.token.toString());
    }

    /**
     * Return the name of the current IDENTIFIER token.
     *
     * @return the name of the current IDENTIFIER token
     */
    @Override
    public String idName() {
        return this.token.toString();
    }
    
    /**
     * Return the is the given TokenKind.
     * 
     * @return is the given Tokenkind
     */
    @Override
    public Boolean isKind(TokenKind kind) {
    	TokenKind currKind = this.getToken();
		if(currKind != kind) {
			return false;
		}
		this.skipToken();
		return true;
    }
    
    /**
     * Throw error message 
     * 
     */
    @Override
    public void errMsg(String s) {
    	String str;
    	if(this.kind == TokenKind.EOF) {
    		str = "nothing";
    	}else {
    		str = this.idName();
    	}
    	throw new RuntimeException ("Expected " + s + " but " + str + " is given!");
    	
    }
    
    /**
     * return int from inputfile
     * 
     */
    @Override
    public int readData() {
    	return this.inputFile.nextInt();
    }
}
