package tech.medivh.classpy.classfile;

import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.common.BytesReader;

import java.nio.ByteOrder;

public class ClassFileReader extends BytesReader {

    private ConstantPool constantPool;

    public ClassFileReader(byte[] data) {
        super(data, ByteOrder.BIG_ENDIAN);
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

}
