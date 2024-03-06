package com.example.lib.test;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.IF_ICMPLE;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.SIPUSH;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;


public class EditMethodVisitor1 extends AdviceAdapter {

    protected EditMethodVisitor1(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
    }

    int currentTimeVarIndex = 1;

    Label label = new Label();
    @Override
    protected void onMethodEnter() {

        super.onMethodEnter();
        System.out.println("onMethodEnter " + getName() + " currentTimeVarIndex==" + currentTimeVarIndex);
        currentTimeVarIndex = newLocal(Type.LONG_TYPE);
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitVarInsn(LSTORE, currentTimeVarIndex);
        visitVarInsn(LLOAD, currentTimeVarIndex);
        visitLdcInsn(Long.valueOf(10L));
        visitInsn(LCMP);
        visitJumpInsn(IFLE, label);
        visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        visitLdcInsn("currentTimeMillis > 1000");
        visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        visitLabel(label);
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);

        // 异常处理代码
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Throwable", "printStackTrace", "()V", false);
    }

}
