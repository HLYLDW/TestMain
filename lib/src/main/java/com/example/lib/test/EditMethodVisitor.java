package com.example.lib.test;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.IALOAD;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.ICONST_3;
import static org.objectweb.asm.Opcodes.IF_ICMPLE;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.SIPUSH;


import org.objectweb.asm.Label;
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
        System.out.println("visitMethodInsn owner:" + owner + " name:" + name + " opcode:" + opcode);
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

            mv.visitIntInsn(ILOAD, 1);
            mv.visitIntInsn(SIPUSH, 11222);
            mv.visitInsn(IADD);
            mv.visitVarInsn(ISTORE, 2);


            mv.visitVarInsn(ILOAD, 2);
            mv.visitInsn(ICONST_2);

            Label label0 = new Label();
            mv.visitJumpInsn(IF_ICMPLE, label0);
            mv.visitIntInsn(SIPUSH, 555);
            mv.visitIntInsn(SIPUSH, 11222);
            mv.visitInsn(IADD);
            mv.visitVarInsn(ISTORE, 2);
            Label label1 = new Label();
            mv.visitJumpInsn(GOTO, label1);
            mv.visitLabel(label0);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("ChildTestClass aaa<=10");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitLabel(label1);

        }
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        System.out.println("visitMaxs " + maxLocals + " " + maxLocals);
        super.visitMaxs(maxStack, maxLocals);
//        mv.visitMaxs(maxStack);
    }
}
