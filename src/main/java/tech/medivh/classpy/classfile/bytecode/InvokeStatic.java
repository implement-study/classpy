package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.constant.ConstantMethodrefInfo;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.datatype.U2CpIndex;
import tech.medivh.classpy.classfile.jvm.Opcode;

/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class InvokeStatic extends InstructionCp2 {

    public InvokeStatic(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    public int getMethodrefIndex() {
        return ((U2CpIndex) getParts().get(1)).getValue();
    }
    
    public ConstantMethodrefInfo getMethodInfo(ConstantPool pool) {
        return pool.getMethodrefInfo(getMethodrefIndex());
    }
 
    
    
}
