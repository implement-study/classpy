package tech.medivh.classpy.classfile;

import org.junit.jupiter.api.Test;
import tech.medivh.classpy.classfile.bytecode.GetStatic;
import tech.medivh.classpy.classfile.bytecode.Instruction;
import tech.medivh.classpy.classfile.bytecode.InvokeStatic;
import tech.medivh.classpy.classfile.bytecode.InvokeVirtual;
import tech.medivh.classpy.classfile.constant.ConstantMethodrefInfo;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tech.medivh.classpy.classfile.jvm.Opcode.getstatic;
import static tech.medivh.classpy.classfile.jvm.Opcode.invokestatic;
import static tech.medivh.classpy.classfile.jvm.Opcode.invokevirtual;


class ClassFileTest {


    @Test
    void testClassFile() throws IOException {
        byte[] allBytes = this.getClass().getClassLoader().getResourceAsStream("Hello17.class").readAllBytes();
        ClassFile classFile = new ClassFileParser().parse(allBytes);
        assertNotNull(classFile.getMainMethod());
        List<Instruction> codes = classFile.getMainMethod().getCodes();
        for (Instruction code : codes) {
            if (code.getOpcode().equals(invokevirtual)) {
                String desc = code.getDesc();
                int methodIndex = Integer.parseInt(desc.substring(desc.indexOf("#") + 1, desc.indexOf("-")));
                ConstantMethodrefInfo methodrefInfo = classFile.getConstantPool().getMethodrefInfo(methodIndex);
                System.out.println(methodrefInfo.className(classFile.getConstantPool()));
                System.out.println(methodrefInfo.paramClassName(classFile.getConstantPool()));
                System.out.println(methodrefInfo.methodName(classFile.getConstantPool()));
                System.out.println(methodrefInfo.isVoid(classFile.getConstantPool()));
            }
            if (code.getOpcode().equals(getstatic)) {
                assertInstanceOf(GetStatic.class, code);
                ((GetStatic) code).getClassName(classFile.getConstantPool());
                ((GetStatic) code).getFieldName(classFile.getConstantPool());
            }
            if(code.getOpcode().equals(invokevirtual)) {
                assertInstanceOf(InvokeVirtual.class, code);
                ConstantMethodrefInfo methodInfo = ((InvokeVirtual) code).getMethodInfo(classFile.getConstantPool());
                String className = methodInfo.className(classFile.getConstantPool());
                List<String> params = methodInfo.paramClassName(classFile.getConstantPool());
                System.out.println(className);
                System.out.println(params);
            }
            if(code.getOpcode().equals(invokestatic)){
                assertInstanceOf(InvokeStatic.class, code);
                ConstantMethodrefInfo methodInfo = ((InvokeStatic) code).getMethodInfo(classFile.getConstantPool());
                String className = methodInfo.className(classFile.getConstantPool());
                List<String> params = methodInfo.paramClassName(classFile.getConstantPool());
                System.out.println(className);
                System.out.println(params);
            }
        }

    }

}
