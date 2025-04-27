package tech.medivh.classpy.classfile;

import tech.medivh.classpy.classfile.attribute.AttributeInfo;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.datatype.Table;
import tech.medivh.classpy.classfile.datatype.U2;
import tech.medivh.classpy.classfile.datatype.U2CpIndex;
import tech.medivh.classpy.classfile.jvm.AccessFlagType;

import java.util.List;

/*
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
*/
public class ClassFile extends ClassFilePart {

    {
        U2 cpCount = new U2();

        u4hex("magic");
        u2("minor_version");
        u2("major_version");
        add("constant_pool_count", cpCount);
        add("constant_pool", new ConstantPool(cpCount));
        u2af("access_flags", AccessFlagType.AF_CLASS);
        u2cp("this_class");
        u2cp("super_class");
        u2("interfaces_count");
        table("interfaces", U2CpIndex.class);
        u2("fields_count");
        table("fields", FieldInfo.class);
        u2("methods_count");
        table("methods", MethodInfo.class);
        u2("attributes_count");
        table("attributes", AttributeInfo.class);
    }

    public ConstantPool getConstantPool() {
        return (ConstantPool) super.get("constant_pool");
    }


    public Table getMethods() {
        return (Table) this.getParts().get(13);
    }

    public MethodInfo getMainMethod() {
        return getMethods().getParts()
            .stream()
            .map(x -> (MethodInfo) x)
            .filter(info -> "main([Ljava/lang/String;)V".equals(info.getDesc()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("main method not found"));
    }

    public List<MethodInfo> getMethods(String name) {
        return getMethods().getParts().stream().map(MethodInfo.class::cast)
            .filter(info -> info.getMethodName(this.getConstantPool()).equals(name)).toList();
    }


}
