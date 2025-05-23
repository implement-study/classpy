package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.jvm.Opcode;

/**
 * The instruction whose operand is U1.
 */
public class InstructionU1 extends Instruction {

    {
        u1("opcode");
        u1("operand");
    }

    public InstructionU1(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void postRead(ConstantPool cp) {
        setDesc(getDesc() + " " + super.get("operand").getDesc());
    }

}
