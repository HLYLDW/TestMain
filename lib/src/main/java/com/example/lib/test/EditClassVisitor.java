package com.example.lib.test;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class EditClassVisitor extends ClassVisitor {
    protected EditClassVisitor(int api) {
        super(api);
    }

    protected EditClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, "com/example/lib/SuperTestClass", interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("<init>")) {
            return new EditMethodVisitor(api, mv);
        }
        return mv;
    }
}
