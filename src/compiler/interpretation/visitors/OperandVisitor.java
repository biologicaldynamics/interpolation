/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.ValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Created by dbborens on 2/14/15.
 */
public class OperandVisitor extends NodeVisitor {

    private final Class[] legalChildClasses = new Class[] {
        NanosyntaxParser.ReferenceContext.class,
        NanosyntaxParser.AssignmentContext.class,
        NanosyntaxParser.PrimitiveContext.class
    };

    public OperandVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ValueNode visit(NanosyntaxParser.OperandContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Unexpected number of children in operand");
        }

        ParseTree child = ctx.getChild(0);
        verify(child);
        return (ValueNode) child.accept(master);
    }

    private void verify(ParseTree child) {
        if (child.getPayload() == null) {
            throw new IllegalArgumentException("Empty operand");
        }

        Object payload = child.getPayload();

        for(Class clazz : legalChildClasses) {
            if (clazz.isInstance(payload)) {
                return;
            }
        }

        throw new IllegalArgumentException("Unexpected operand class");
    }
}
