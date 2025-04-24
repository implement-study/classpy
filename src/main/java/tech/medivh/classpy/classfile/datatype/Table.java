package tech.medivh.classpy.classfile.datatype;

import tech.medivh.classpy.classfile.ClassFilePart;
import tech.medivh.classpy.classfile.ClassFileReader;
import tech.medivh.classpy.classfile.attribute.AttributeFactory;
import tech.medivh.classpy.classfile.attribute.AttributeInfo;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.common.FilePart;
import tech.medivh.classpy.common.ParseException;
import tech.medivh.classpy.helper.StringHelper;

/**
 * Array of class parts.
 */
public class Table extends ClassFilePart {

    private final UInt length;
    private final Class<? extends ClassFilePart> entryClass;

    public Table(UInt length, Class<? extends ClassFilePart> entryClass) {
        this.length = length;
        this.entryClass = entryClass;
    }
    
    @Override
    protected void readContent(ClassFileReader reader) {
        try {
            for (int i = 0; i < length.getValue(); i++) {
                super.add(readEntry(reader));
            }
        } catch (ReflectiveOperationException e) {
            throw new ParseException(e);
        }
    }

    private ClassFilePart readEntry(ClassFileReader reader) throws ReflectiveOperationException {
        if (entryClass == AttributeInfo.class) {
            return readAttributeInfo(reader);
        } else {
            ClassFilePart c = entryClass.getDeclaredConstructor().newInstance();
            c.read(reader);
            return c;
        }
    }
    
    private AttributeInfo readAttributeInfo(ClassFileReader reader) {
        int attrNameIndex = reader.getFixedI16(reader.getPosition());
        String attrName = reader.getConstantPool().getUtf8String(attrNameIndex);
        
        AttributeInfo attr = AttributeFactory.create(attrName);
        attr.setName(attrName);
        attr.read(reader);
        
        return attr;
    }

    @Override
    protected void postRead(ConstantPool cp) {
        int i = 0;
        for (FilePart entry : super.getParts()) {
            String newName = StringHelper.formatIndex(length.getValue(), i++);
            String oldName = entry.getName();
            if (oldName != null) {
                newName += " (" + oldName + ")";
            }
            entry.setName(newName);
        }
    }

}
