package tech.medivh.classpy.classfile;

import tech.medivh.classpy.classfile.attribute.AttributeInfo;
import tech.medivh.classpy.classfile.attribute.CodeAttribute;
import tech.medivh.classpy.classfile.bytecode.Instruction;
import tech.medivh.classpy.classfile.constant.ConstantPool;
import tech.medivh.classpy.classfile.datatype.Table;
import tech.medivh.classpy.classfile.jvm.AccessFlagType;
import tech.medivh.classpy.common.FilePart;

import java.util.List;

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
        u2af("access_flags", AccessFlagType.AF_METHOD);
        u2cp("name_index");
        u2cp("descriptor_index");
        u2("attributes_count");
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

    public Table getAttributes() {
        return (Table) this.getParts().stream()
            .filter(part -> part instanceof Table && part.getName().equals("attributes"))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("Attributes not found"));
    }

    public CodeAttribute getCodeAttribute() {
        return (CodeAttribute) getAttributes().getParts().stream()
            .filter(CodeAttribute.class::isInstance)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Code attribute not found"));
    }

    public int getMaxStack() {
        return getCodeAttribute().getUInt("max_stack");
    }

    public int getMaxLocals() {
        return getCodeAttribute().getUInt("max_locals");
    }

    public List<Instruction> getCodes() {
        FilePart code = getCodeAttribute()
            .getParts()
            .stream()
            .filter(p -> p.getName().equals("code"))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Code not found"));
        return code.getParts().stream().map(Instruction.class::cast).toList();
    }

}
