package tech.medivh.classpy.classfile.bytecode;

import tech.medivh.classpy.classfile.ClassFileReader;
import tech.medivh.classpy.classfile.jvm.Opcode;

public class Iinc extends Instruction {

    private int index;
    private int _const;

    public Iinc(Opcode opcode, int pc) {
        super(opcode, pc);
    }

    @Override
    protected void readOperands(ClassFileReader reader) {
        index = reader.readFixedU8();
        _const = reader.readByte();
        setDesc(getDesc() + " " + index + ", " + _const);
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getConst() {
        return _const;
    }

}
