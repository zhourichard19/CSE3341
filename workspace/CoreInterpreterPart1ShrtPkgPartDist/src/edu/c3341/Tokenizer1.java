/**
 * Shortened package name for Wayne Heym's CSE 3341 course.
 */
package edu.c3341;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Tokenizer for a "subset" of the Core language.
 *
 * @author Richard Zhou
 * @author Wayne Heym (author of skeleton)
 *
 */
final class Tokenizer1 implements Tokenizer {

    /**
     * According to the singleton pattern, either null or a reference to the
     * single instance of Tokenizer.
     */
    private static Tokenizer1 singleInstance = null;

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

    private static final Map<String, TokenKind> RESERVED_WORDS;
    static {
        RESERVED_WORDS = new HashMap<>();
        RESERVED_WORDS.put("program", TokenKind.PROGRAM);
        RESERVED_WORDS.put("begin", TokenKind.BEGIN);
        RESERVED_WORDS.put("end", TokenKind.END);
        RESERVED_WORDS.put("int", TokenKind.INT);
        RESERVED_WORDS.put("if", TokenKind.IF);
        RESERVED_WORDS.put("then", TokenKind.THEN);
        RESERVED_WORDS.put("else", TokenKind.ELSE);
        RESERVED_WORDS.put("while", TokenKind.WHILE);
        RESERVED_WORDS.put("loop", TokenKind.LOOP);
        RESERVED_WORDS.put("read", TokenKind.READ);
        RESERVED_WORDS.put("write", TokenKind.WRITE);
    }

    /**
     * A Set containing each of the characters that serve as prefixes of
     * delimiters (a.k.a. special symbols) in a "subset" of the Core language.
     */
    private static final Set<Character> DELIMITER_PREFIX_CHARACTERS;

    static {
        DELIMITER_PREFIX_CHARACTERS = new HashSet<>();
        String source = "=|!<>&";
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
         * State VERT_BAR is shown in the diagram with "one |" inside its oval.
         */
        NOT_EQ,

        /**
         * State VERT_BAR is shown in the diagram with "one |" inside its oval.
         */
        GREATER_THAN,

        /**
         * State VERT_BAR is shown in the diagram with "one |" inside its oval.
         */
        LESS_THAN;
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
    public static synchronized Tokenizer set(Iterator<String> itString) {
        if (Tokenizer1.singleInstance == null) {
            Tokenizer1.singleInstance = new Tokenizer1();
            Tokenizer1.singleInstance.token = new StringBuilder();
        } else {
            Tokenizer1.singleInstance.token.setLength(0);
        }
        Tokenizer1.singleInstance.head = "";
        Tokenizer1.singleInstance.pos = 0;
        Tokenizer1.singleInstance.tail = itString;
        Tokenizer1.singleInstance.kind = TokenKind.ERROR;
        Tokenizer1.singleInstance.findToken();
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
            }
            case '!': {
                result = State.NOT_EQ;
                break;
            }
            case '>': {
                result = State.GREATER_THAN;
                break;
            }
            case '<': {
                result = State.LESS_THAN;
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
            }
            case ',': {
                result = TokenKind.COMMA;
                break;
            }
            case '[': {
                result = TokenKind.FORWARD_BRACKET;
                break;
            }
            case ']': {
                result = TokenKind.BACKWARD_BRACKET;
                break;
            }
            case '(': {
                result = TokenKind.FORWARD_PARENTHESIS;
                break;
            }
            case ')': {
                result = TokenKind.BACKWARD_PARENTHESIS;
                break;
            }
            case '+': {
                result = TokenKind.ADDITION;
                break;
            }
            case '-': {
                result = TokenKind.SUBTRACTION;
                break;
            }
            case '*': {
                result = TokenKind.MULTIPLICATION;
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
     * Collect the given character into $this.token and move past it in
     * $this.head by incrementing $this.pos.
     *
     * @param c
     *            the character to be collected
     */
    private void collectCharacter(char c) {
        this.token.append(c);
        this.pos++;
    }

    // TODO - Note that the following comment saying this "method is too long"
    // will become true after you complete the provided skeleton for this method.
    // Of course, delete this section of comments before declaring this
    // programming assignment to be complete.

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
                            this.collectCharacter(current);
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
                                if (RESERVED_WORDS
                                        .containsKey(this.token.toString())) {
                                    this.kind = RESERVED_WORDS
                                            .get(this.token.toString());
                                } else {
                                    this.kind = TokenKind.ERROR;
                                }
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if ('a' <= current && current <= 'z') {
                                    this.collectCharacter(current);
                                } else if ('A' <= current && current <= 'Z'
                                        || '0' <= current && current <= '9') {
                                    this.collectCharacter(current);
                                    state = State.ERROR;
                                } else {
                                    if (RESERVED_WORDS.containsKey(
                                            this.token.toString())) {
                                        this.kind = RESERVED_WORDS
                                                .get(this.token.toString());
                                    } else {
                                        this.kind = TokenKind.ERROR;
                                    }
                                    seeking = false;
                                }
                            }
                            break;
                        }
                        case DIGIT_GATHERING: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.INTEGER_CONSTANT;
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if ('0' <= current && current <= '9') {
                                    this.collectCharacter(current);
                                } else if ('A' <= current && current <= 'Z'
                                        || 'a' <= current && current <= 'z') {
                                    this.collectCharacter(current);
                                    state = State.ERROR;
                                } else {
                                    this.kind = TokenKind.INTEGER_CONSTANT;
                                    seeking = false;
                                }
                            }
                            break;
                        }
                        case GATHERING_UPPERCASE: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.IDENTIFIER;
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if ('A' <= current && current <= 'Z') {
                                    this.collectCharacter(current);
                                } else if ('a' <= current && current <= 'z') {
                                    this.collectCharacter(current);
                                    state = State.ERROR;
                                } else if ('0' <= current && current <= '9') {
                                    state = State.ID_GATHERING_DIGITS;
                                } else {
                                    this.kind = TokenKind.IDENTIFIER;
                                    seeking = false;
                                }
                            }
                            break;
                        }
                        case ID_GATHERING_DIGITS: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.IDENTIFIER;
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if ('0' <= current && current <= '9') {
                                    this.collectCharacter(current);
                                } else if ('A' <= current && current <= 'Z'
                                        || 'a' <= current && current <= 'z') {
                                    this.collectCharacter(current);
                                    state = State.ERROR;
                                } else {
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
                            } else {
                                char current = this.head.charAt(this.pos);
                                if (current == '=') {
                                    this.collectCharacter(current);
                                    this.kind = TokenKind.EQUALITY_TEST;
                                    seeking = false;
                                } else {
                                    this.kind = TokenKind.ASSIGNMENT_OPERATOR;
                                    seeking = false;
                                }
                            }
                            break;
                        }
                        case VERT_BAR: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.ERROR;
                                seeking = false;
                                state = State.ERROR;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if (current == '|') {
                                    this.collectCharacter(current);
                                    this.kind = TokenKind.OR_OPERATOR;
                                    seeking = false;
                                } else {
                                    this.kind = TokenKind.ERROR;
                                    seeking = false;
                                    state = State.ERROR;
                                }
                            }
                            break;
                        }
                        case NOT_EQ: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.EXCLAMATION;
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if (current == '=') {
                                    this.collectCharacter(current);
                                    this.kind = TokenKind.NOT_EQUAL;
                                    seeking = false;
                                } else {
                                    this.kind = TokenKind.EXCLAMATION;
                                    seeking = false;
                                }
                            }
                            break;
                        }
                        case GREATER_THAN: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.GREATER_THAN;
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if (current == '=') {
                                    this.collectCharacter(current);
                                    this.kind = TokenKind.GREATER_THAN_OR_EQUAL_TO;
                                    seeking = false;
                                } else {
                                    this.kind = TokenKind.GREATER_THAN;
                                    seeking = false;
                                }
                            }
                            break;
                        }
                        case LESS_THAN: {
                            if (this.head.length() <= this.pos) {
                                this.kind = TokenKind.LESS_THAN;
                                seeking = false;
                            } else {
                                char current = this.head.charAt(this.pos);
                                if (current == '=') {
                                    this.collectCharacter(current);
                                    this.kind = TokenKind.LESS_THAN_OR_EQUAL_TO;
                                    seeking = false;
                                } else {
                                    this.kind = TokenKind.LESS_THAN;
                                    seeking = false;
                                }
                            }
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
                                    this.collectCharacter(current);
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
    public BigInteger intVal() {
        return new BigInteger(this.token.toString());
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
}
