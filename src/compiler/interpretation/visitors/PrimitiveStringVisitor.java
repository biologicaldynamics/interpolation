/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;

/**
 * Created by dbborens on 2/15/15.
 */
public class PrimitiveStringVisitor
        extends AbstractNarrowPrimitiveVisitor<String,
        NanosyntaxParser.StringPrimitiveContext> {

    public PrimitiveStringVisitor(NanoToASTVisitor master) {
        super(master, str -> str.replaceAll("^\"|\"$", ""));
    }
}
