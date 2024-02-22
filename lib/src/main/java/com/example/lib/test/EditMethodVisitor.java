package com.example.lib.test;

import org.objectweb.asm.MethodVisitor;


public class EditMethodVisitor extends MethodVisitor {
    protected EditMethodVisitor(int api) {
        super(api);
    }

    protected EditMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if ("com/example/lib/TestClass".equals(owner) && "<init>".equals(name)) {
            super.visitMethodInsn(opcode, "com/example/lib/SuperTestClass", name, descriptor, isInterface);
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }
}
