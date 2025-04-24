package tech.medivh.classpy.classfile.attribute;

import tech.medivh.classpy.classfile.ClassFilePart;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
 */
public abstract class AttributeInfo extends ClassFilePart {

    {
        u2("attribute_name_index");
        u4("attribute_length");
    }

}
