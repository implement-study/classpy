package tech.medivh.classpy.classfile;

import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.datatype.*;
import tech.medivh.classpy.common.ReadableFilePart;

/**
 * Base class for all class file parts.
 */
public abstract class ClassFilePart extends ReadableFilePart<ClassFileReader> {

    protected void postRead(ConstantPool cp) {

    }

    protected int getUInt(String name) {
        return ((UInt) get(name)).getValue();
    }

    protected final void u1(String name) {
        this.add(name, new U1());
    }

    protected final void u1cp(String name) {
        this.add(name, new U1CpIndex());
    }

    protected final void u2(String name) {
        this.add(name, new U2());
    }

    protected final void u2cp(String name) {
        this.add(name, new U2CpIndex());
    }

    protected final void u2af(String name, int afType) {
        this.add(name, new U2AccessFlags(afType));
    }

    protected final void u4(String name) {
        this.add(name, new U4());
    }

    protected final void u4hex(String name) {
        this.add(name, new U4Hex());
    }

    protected final void table(String name,
                               Class<? extends ClassFilePart> entryClass) {
        UInt length = (UInt) getParts().get(getParts().size() - 1);
        Table table = new Table(length, entryClass);
        this.add(name, table);
    }

    protected final void bytes(String name) {
        UInt count = (UInt) getParts().get(getParts().size() - 1);
        Bytes bytes = new Bytes(count);
        this.add(name, bytes);
    }

    protected final void add(ClassFilePart subPart) {
        this.add(null, subPart);
    }

}
