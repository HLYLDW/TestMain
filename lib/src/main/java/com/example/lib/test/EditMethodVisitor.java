package com.example.lib.test;

import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.ICONST_3;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;


import org.objectweb.asm.MethodVisitor;


public class EditMethodVisitor extends MethodVisitor {
    protected EditMethodVisitor(int api) {
        super(api);
    }

    protected EditMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    String methodName;

    public EditMethodVisitor(int api, MethodVisitor mv, String name) {
        super(api, mv);
        this.methodName = name;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        System.out.println("visitMethodInsn ");
        if ("com/example/lib/TestClass".equals(owner) && "<init>".equals(name) && !methodName.equals("setAaa")) {
            super.visitMethodInsn(opcode, "com/example/lib/SuperTestClass", name, descriptor, isInterface);
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }


    @Override
    public void visitCode() {
        System.out.println("visitCode ");
        super.visitCode();
        if (methodName.equals("setAaa")) {
            MethodVisitor methodVisitor = this;
//            methodVisitor.visitVarInsn(ILOAD, 1);
//            methodVisitor.visitInsn(ICONST_2);
//            methodVisitor.visitInsn(IADD);
//            methodVisitor.visitVarInsn(ISTORE, 2);
//            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
//            methodVisitor.visitInsn(DUP);
//            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//            methodVisitor.visitLdcInsn("ChildTestClass \u63d2\u5165:");
//            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//            methodVisitor.visitVarInsn(ILOAD, 2);
//            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
//            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            methodVisitor.visitInsn(ICONST_2);
            methodVisitor.visitInsn(ICONST_3);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitVarInsn(ISTORE, 2);



        }
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        System.out.println("visitMaxs " + maxLocals + " " + maxLocals);
        if (methodName.equals("setAaa")) {
            super.visitMaxs(3, 3);
            return;
        }
        super.visitMaxs(maxStack, maxLocals);
    }
}
