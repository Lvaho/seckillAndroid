package com.zjc.keepwork.adapter.pojo;

public class Function {
    public Function(int pos, String functionName) {
        this.pos = pos;
        this.functionName = functionName;
    }

    private int pos;
    private String functionName;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public String toString() {
        return "Function{" +
                "functionName='" + functionName + '\'' +
                '}';
    }
}

