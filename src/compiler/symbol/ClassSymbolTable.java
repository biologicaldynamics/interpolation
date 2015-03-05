/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.util.UnrecognizedIdentifierError;

import java.util.HashMap;

/**
 * Created by dbborens on 3/3/15.
 */
public abstract class ClassSymbolTable implements SymbolTable {

    private HashMap<String, ClassSymbol> members;

    public ClassSymbolTable() {
        members = resolveSubclasses();
    }

    protected abstract HashMap<String, ClassSymbol> resolveSubclasses();

    public InstanceSymbolTable getSymbolTable(ASTValueNode value) {
        String identifier;
        if (value instanceof ASTReferenceNode) {
            identifier = ((ASTReferenceNode) value).getIdentifier();
        } else if (value instanceof ASTAssignmentNode) {
            identifier = ((ASTAssignmentNode) value).getReference().getIdentifier();
        } else {
            throw new IllegalArgumentException("Unrecognized class specifier");
        }

        return doGet(identifier);
    }

    private InstanceSymbolTable doGet(String identifier) {
        if (!members.containsKey(identifier)) {
            throw new UnrecognizedIdentifierError();
        }

        return members.get(identifier).getSymbolTable();
    }
}
