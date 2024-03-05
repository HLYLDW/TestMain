package com.example.lib.test;

import com.example.lib.ChildTestClass;
import com.example.lib.ITest;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class AsmTest {
    public static String path = "D:\\project\\gittest\\TestMain\\lib\\build\\classes\\java\\main\\com\\example\\lib\\ChildTestClass1.class";
    public static void main(String[] args) throws IOException {
        Class clazz = ChildTestClass.class;
        String clazzFilePath = Utils.getClassFilePath(clazz);
//        String clazzFilePath = path;
        ClassReader classReader = new ClassReader(new FileInputStream(clazzFilePath));

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        EditClassVisitor editClassVisitor = new EditClassVisitor(Opcodes.ASM9, classWriter);
        classReader.accept(editClassVisitor, ClassReader.EXPAND_FRAMES);

         //写入文件
        byte[] bytes = classWriter.toByteArray();
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(bytes);
        fos.flush();
        fos.close();

//        ChildTestClass childTestClass = new ChildTestClass(222);
//        childTestClass.setAaa(5555);

        try {
            Class c1 = Class.forName("com.example.lib.ChildTestClass1");
            Constructor<?>[] cons = c1.getConstructors();
            //数组下标表示调用第几个构造方法
            ITest a1 = (ITest) cons[0].newInstance(11);
            a1.setAaa(888888);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
