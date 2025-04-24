package tech.medivh.classpy.classfile;

import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.common.FileParser;
import tech.medivh.classpy.common.FilePart;

public class ClassFileParser implements FileParser {

    public ClassFile parse(byte[] data) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassFileReader(data));
        postRead(cf, cf.getConstantPool());
        return cf;
    }

    private static void postRead(ClassFilePart fc, ConstantPool cp) {
        for (FilePart c : fc.getParts()) {
            postRead((ClassFilePart) c, cp);
        }
        fc.postRead(cp);
    }

}
