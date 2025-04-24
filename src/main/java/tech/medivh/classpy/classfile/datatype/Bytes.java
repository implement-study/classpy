package tech.medivh.classpy.classfile.datatype;

import tech.medivh.classpy.classfile.ClassFilePart;
import tech.medivh.classpy.classfile.ClassFileReader;

/**
 * Unparsed bytes.
 */
public class Bytes extends ClassFilePart {

    private final UInt count;

    public Bytes(UInt count) {
        this.count = count;
    }

    @Override
    protected void readContent(ClassFileReader reader) {
        reader.skipBytes(count.getValue());
    }

}
