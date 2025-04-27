package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.constant.ConstantInfo;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.datatype.U1CpIndex;
import tech.medivh.classpy.classfile.jvm.Opcode;

/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class Ldc extends InstructionCp1 {
    
    public Ldc(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    public ConstantInfo getConstantInfo(ConstantPool pool) {
        int constantIndex = ((U1CpIndex) getParts().get(1)).getValue();
        return pool.getConstant(constantIndex);
    }
    
}
