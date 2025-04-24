package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.jvm.Opcode;
import tech.medivh.classpy.common.ParseException;

public class NewArray extends Instruction {

    {
        u1("opcode");
        u1("atype");
    }

    public NewArray(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void postRead(ConstantPool cp) {
        int atype = super.getUInt("atype");
        setDesc(getDesc() + " " + getArrayType(atype));
    }
    
    private static String getArrayType(int atype) {
        return switch (atype) {
            case 4 -> "boolean";
            case 5 -> "char";
            case 6 -> "float";
            case 7 -> "double";
            case 8 -> "byte";
            case 9 -> "short";
            case 10 -> "int";
            case 11 -> "long";
            default -> throw new ParseException("Invalid atype: " + atype);
        };
    }
    
}
