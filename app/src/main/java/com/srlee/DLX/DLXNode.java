package com.srlee.DLX;

public class DLXNode extends LL2DNode {
    private final DLXColumn C; // Pointer to Column Header
    private final int RowIdx; // Index to row

    public DLXNode(DLXColumn col, int ri) {
        RowIdx = ri;
        C = col;
        col.GetUp().SetDown(this);
        SetUp(col.GetUp());
        SetDown(col);
        col.SetUp(this);
        col.IncSize();
    }

    public DLXColumn GetColumn() {
        return C;
    }

    public int GetRowIdx() {
        return RowIdx;
    }
}
