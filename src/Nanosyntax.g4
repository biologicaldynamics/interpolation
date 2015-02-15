/* Grammar for the Nanoverse programming language, Nanosyntax.
 * 
 * Portions of this grammar are based on other grammars:
 *   https://github.com/antlr/grammars-v4/blob/master/java/Java.g4
 *   http://media.pragprog.com/titles/tpantlr2/code/examples/JSON.g4

 */
grammar Nanosyntax;

root: (statement)*;

statement
    : assignment ';'
    | definition ';';

assignment
    : reference block
    | reference ':' singleton;

definition
    : 'define' type ID block
    | 'define' type ID ':' singleton;

block: '{' (statement)* '}' ;

singleton
    : reference
    | operation
    | primitive
    | assignment;

operation
    : operand operator operand
    | operand operator operation;

operand
    : reference
    | assignment
    | primitive;

operator
    : '+'
    | '-'
    | '*'
    | '/'
    | '<='
    | '>='
    | '>'
    | '<';

reference
    : ID
    | ID '->' reference;

type
    : ID;

primitive
    :   stringPrimitive
    |   floatPrimitive
    |   intPrimitive
    ;

stringPrimitive: STRING;
floatPrimitive: FLOAT;
intPrimitive: INTEGER;

ID : [a-zA-Z] [a-zA-Z0-9]*;

STRING :  '"' (~["\\])* '"' ;

FLOAT
    :   '-'? INT '.' [0-9]+ EXP? // 1.35, 1.35E-9, 0.3, -4.5
    |   '-'? INT EXP             // 1e10 -3e4
    ;

INTEGER: '-'? INT;
fragment INT :   '0' | [1-9] [0-9]* ; // no leading zeros
fragment EXP :   [Ee] [+\-]? INT ; // \- since - means "range" inside [...]

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;
