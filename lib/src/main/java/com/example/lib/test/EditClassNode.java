package com.example.lib.test;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class EditClassNode extends ClassNode {
    public EditClassNode() {
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        for (MethodNode method : methods) {
           if (method.name.equals("setAaa")) {
               for (AbstractInsnNode instruction : method.instructions) {

               }
               System.out.println("EditClassNode visitEnd method.name:" + method.name);
           }
        }
    }
}