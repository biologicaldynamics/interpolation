/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

/**
 * Created by dbborens on 2/14/15.
 */
public class NodeVisitor {

    protected NanoToASTVisitor master;

    public NodeVisitor(NanoToASTVisitor master) {
        this.master = master;
    }
}
