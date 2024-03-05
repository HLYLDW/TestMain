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
        super.visit(version, access, "com/example/lib/ChildTestClass1", signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
//        if (!"<init>".equals(name)) {
//            return new EditMethodVisitor1(api, mv, access, name, descriptor);
//        }
        return new EditMethodVisitor1(api, mv, access, name, descriptor);
    }
}
