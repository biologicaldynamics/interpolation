/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.ClassSymbolTable;
import compiler.symbol.ListSymbolTable;
import compiler.symbol.ReservedContext;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/22/15.
 */
public class ListObjectNode implements ObjectNode {

    private ListSymbolTable symbolTable;
    private final LocalContextList local;
    private final ReservedContext reserved;

    public ListObjectNode(ListSymbolTable symbolTable, ReservedContext reserved) {
        this(symbolTable, new LocalContextList(), reserved);
    }

    public ListObjectNode(ListSymbolTable symbolTable, LocalContextList local, ReservedContext reserved) {
        this.symbolTable = symbolTable;
        this.local = local;
        this.reserved = reserved;
    }

    public Stream<ObjectNode> getMemberStream() {
        return local.getMembers();
    }

    public ObjectNode getMember(int index) {
        if (index >= size()) {
            throw new IllegalArgumentException("List context member index out of bounds.");
        }
        return local.get(index);
    }

    public void loadMember(ObjectNode value) {
        local.loadMember(value);
    }

    public int size() {
        return local.size();
    }

    public ClassSymbolTable getSymbolTable() {
        return symbolTable;
    }

    public ReservedContext getReserved() {
        return reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListObjectNode that = (ListObjectNode) o;

        if (!local.equals(that.local)) return false;
        if (!symbolTable.equals(that.symbolTable)) return false;

        return true;
    }
}
