package tech.medivh.classpy.classfile;

import tech.medivh.classpy.classfile.attribute.AttributeInfo;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.jvm.AccessFlagType;

/*
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class MethodInfo extends ClassFilePart {

    {
        u2af ("access_flags", AccessFlagType.AF_METHOD);
        u2cp ("name_index");
        u2cp ("descriptor_index");
        u2   ("attributes_count");
        table("attributes", AttributeInfo.class);
    }

    @Override
    protected void postRead(ConstantPool cp) {
        int nameIndex = super.getUInt("name_index");
        int descIndex = super.getUInt("descriptor_index");
        if (nameIndex > 0) {
            // TODO: fix loading java.lang.String from rt.jar
            setDesc(cp.getUtf8String(nameIndex) + cp.getUtf8String(descIndex));
        }
    }
    
}
