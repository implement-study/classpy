package tech.medivh.classpy.classfile.constant;

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
*/
public class ConstantFieldrefInfo extends ConstantInfo {

    {
        u2("class_index");
        u2("name_and_type_index");
    }

    private ConstantClassInfo classInfo;

    private ConstantNameAndTypeInfo nameAndTypeInfo;

    @Override
    protected String loadDesc(ConstantPool cp) {
        int classIndex = super.getUInt("class_index");
        int nameAndTypeIndex = super.getUInt("name_and_type_index");

        classInfo = cp.getClassInfo(classIndex);
        String className = cp.getUtf8String(classInfo.getNameIndex());
        nameAndTypeInfo = cp.getNameAndTypeInfo(nameAndTypeIndex);
        String fieldName = cp.getUtf8String(nameAndTypeInfo.getNameIndex());
        return className + "." + fieldName;
    }

    public String getFieldName(ConstantPool cp) {
        return cp.getUtf8String(nameAndTypeInfo.getNameIndex());
    }
    
    public ConstantClassInfo getClassInfo() {
        return classInfo;
    }

    public ConstantNameAndTypeInfo getNameAndTypeInfo() {
        return nameAndTypeInfo;
    }
}
