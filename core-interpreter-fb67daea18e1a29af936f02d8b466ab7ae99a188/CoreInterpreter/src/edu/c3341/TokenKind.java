package edu.c3341;

/**
 * Token kinds needed for Part 2 of the Core Interpreter project.
 *
 * @author Zhijian Yao
 *
 */
enum TokenKind {
  
    /**
     * Test driver's token number = 1.
     */
    PROGRAM(1),

    /**
     * Test driver's token number = 2.
     */
    BEGIN(2),
    
    /**
     * Test driver's token number = 3.
     */
    END(3),
    
    /**
     * Test driver's token number = 4.
     */
    INT(4),
    
    /**
     * Test driver's token number = 5.
     */
    IF(5),
    
    /**
     * Test driver's token number = 6.
     */
    THEN(6),
    
    /**
     * Test driver's token number = 7.
     */
    ELSE(7),
    
    /**
     * Test driver's token number = 8.
     */
    WHILE(8),
    
    /**
     * Test driver's token number = 9.
     */
    LOOP(9),
    
    /**
     * Test driver's token number = 10.
     */
    READ(10),
    
    /**
     * Test driver's token number = 11.
     */
    WRITE(11),
    
    /**
     * Test driver's token number = 12; token is ;.
     */
    SEMICOLON(12),

    /**
     * Test driver's token number = 13; token is ,.
     */
    COMMA(13),
    
    
    /**
     * Test driver's token number = 14; token is =.
     */
    ASSIGNMENT_OPERATOR(14),
    
    /**
     * Test driver's token number = 15; token is !.
     */
    NOT(15),
    
    /**
     * Test driver's token number = 16; token is [.
     */
    LEFT_BRACKET(16),
    
    /**
     * Test driver's token number = 17; token is ].
     */
    RIGHT_BRACKET(17),
    
    /**
     * Test driver's token number = 18; token is &&.
     */
    AND_OPERATOR(18),
    
    /**
     * Test driver's token number = 19; token is ||.
     */
    OR_OPERATOR(19),
    
    /**
     * Test driver's token number = 20; token is (.
     */
    LEFT_PARENTHESIS(20),
    
    /**
     * Test driver's token number = 21; token is ).
     */
    RIGHT_PARENTHESIS(21),
    
    /**
     * Test driver's token number = 22; token is +.
     */
    PLUS(22),
    
    /**
     * Test driver's token number = 23; token is -.
     */
    MINUS(23),
    
    /**
     * Test driver's token number = 24; token is *.
     */
    MULTIPLY(24),
    
    /**
     * Test driver's token number = 25; token is !=.
     */
    NOT_EQUAL(25),
    

    /**
     * Test driver's token number = 26; token is ==.
     */
    EQUALITY_TEST(26),

    /**
     * Test driver's token number = 27; token is <.
     */
    SMALLER_THAN(27),
    
    /**
     * Test driver's token number = 28; token is >.
     */
    GREATER_THAN(28),
    
    /**
     * Test driver's token number = 29; token is <=.
     */
    SMALLER_OR_EQUAL(29),
    
    /**
     * Test driver's token number = 30; token is >=.
     */
    GREATER_OR_EQUAL(30),
    
    /**
     * Test driver's token number = 31.
     */
    INTEGER_CONSTANT(31),

    /**
     * Test driver's token number = 32.
     */
    IDENTIFIER(32),

    /**
     * Test driver's token number = 33.
     */
    EOF(33),

    /**
     * Test driver's token number = 34.
     */
    ERROR(34);

    /**
     * Test driver's token number.
     */
    private final int testDriverTokenNumber;

    /**
     * Constructor. (As class TokenKind is an enum, the visibility of the
     * explicit constructor is automatically private (i.e., private by default).
     * The default visibility for it is not package visibility.)
     *
     * @param number
     *            the test driver's token number
     */
    TokenKind(int number) {
        this.testDriverTokenNumber = number;
    }

    /**
     * Return test driver's token number.
     *
     * @return test driver's token number
     */
    public int testDriverTokenNumber() {
        return this.testDriverTokenNumber;
    }
}
