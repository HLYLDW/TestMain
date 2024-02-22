package com.example.lib.test;

import com.example.lib.ChildTestClass;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AsmTest {
    public static String path = "D:\\project\\gittest\\TestMain\\lib\\build\\classes\\java\\main\\com\\example\\lib\\ChildTestClass.class";
    public static void main(String[] args) throws IOException {
//        Class clazz = ChildTestClass.class;
//        String clazzFilePath = Utils.getClassFilePath(clazz);
        String clazzFilePath = path;
        ClassReader classReader = new ClassReader(new FileInputStream(clazzFilePath));

        ClassWriter classWriter = new ClassWriter(0);

        EditClassVisitor editClassVisitor = new EditClassVisitor(Opcodes.ASM5, classWriter);
        classReader.accept(editClassVisitor, 0);

         //写入文件
        byte[] bytes = classWriter.toByteArray();
        FileOutputStream fos = new FileOutputStream(clazzFilePath);
        fos.write(bytes);
        fos.flush();
        fos.close();

        ChildTestClass childTestClass = new ChildTestClass(222);
        childTestClass.setAaa(5555);
    }
}
