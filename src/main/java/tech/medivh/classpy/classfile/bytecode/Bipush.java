package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.ClassFileReader;
import tech.medivh.classpy.classfile.jvm.Opcode;

public class Bipush extends Instruction {

    public Bipush(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    private byte operand;

    @Override
    protected void readOperands(ClassFileReader reader) {
        operand = reader.readByte();
        setDesc(getDesc() + " " + operand);
    }


    public byte getPushByte() {
        return operand;
    }


}
