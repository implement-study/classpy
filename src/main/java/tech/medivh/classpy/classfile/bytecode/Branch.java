package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.ClassFileReader;
import tech.medivh.classpy.classfile.jvm.Opcode;

public class Branch extends Instruction {

    public Branch(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    private int jumpTo;

    @Override
    protected void readOperands(ClassFileReader reader) {
        short offset = reader.readFixedI16();
        jumpTo = pc + offset;
        setDesc(getDesc() + " " + jumpTo);
    }

    public int getJumpTo() {
        return jumpTo;
    }

}
