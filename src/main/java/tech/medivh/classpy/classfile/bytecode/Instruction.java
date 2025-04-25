package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.ClassFilePart;
import tech.medivh.classpy.classfile.ClassFileReader;
import tech.medivh.classpy.classfile.jvm.Opcode;

/**
 * Base class for all instructions.
 */
public class Instruction extends ClassFilePart {

    protected final Opcode opcode;
    protected final int pc;

    public Instruction(Opcode opcode, int pc) {
        this.opcode = opcode;
        this.pc = pc;
        setDesc(opcode.name());
    }

    public int getPc() {
        return pc;
    }

    public Opcode getOpcode() {
        return opcode;
    }

    @Override
    protected final void readContent(ClassFileReader reader) {
        if (!super.getParts().isEmpty()) {
            super.readContent(reader);
        } else {
            reader.readFixedU8(); // opcode
            readOperands(reader);
        }
    }

    protected void readOperands(ClassFileReader reader) {
        if (opcode.operandCount > 0) {
            reader.skipBytes(opcode.operandCount);
        }
    }

}
