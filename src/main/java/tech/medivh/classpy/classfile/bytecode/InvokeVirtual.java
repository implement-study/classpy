package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.constant.ConstantMethodrefInfo;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.datatype.U2CpIndex;
import tech.medivh.classpy.classfile.jvm.Opcode;

/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class InvokeVirtual extends InstructionCp2 {

    public InvokeVirtual(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    public int getMethodrefIndex() {
        return ((U2CpIndex) getParts().get(1)).getValue();
    }
    
    public ConstantMethodrefInfo getMethodInfo(ConstantPool pool) {
        return pool.getMethodrefInfo(getMethodrefIndex());
    }
    
}
