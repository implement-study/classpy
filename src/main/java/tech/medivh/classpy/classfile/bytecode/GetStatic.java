package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.constant.ConstantClassInfo;
import tech.medivh.classpy.classfile.constant.ConstantFieldrefInfo;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.datatype.U2;
import tech.medivh.classpy.classfile.datatype.U2CpIndex;
import tech.medivh.classpy.classfile.jvm.Opcode;

/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class GetStatic extends InstructionCp2 {

    public GetStatic(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    public int getFieldrefIndex() {
        return ((U2CpIndex) getParts().get(1)).getValue();
    }

    public ConstantFieldrefInfo getFieldrefInfo(ConstantPool pool) {
        return pool.getFieldrefInfo(getFieldrefIndex());
    }
    
    
    public String getClassName(ConstantPool pool) {
        ConstantFieldrefInfo fieldrefInfo = getFieldrefInfo(pool);
        int classIndex = ((U2)fieldrefInfo.getParts().get(1)).getValue();
        ConstantClassInfo classInfo = pool.getClassInfo(classIndex);
        return classInfo.getClassName(pool);
    }
    
    public String getFieldName(ConstantPool pool) {
        return getFieldrefInfo(pool).getFieldName(pool);
    }
}
