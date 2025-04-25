package tech.medivh.classpy.classfile.constant;

import tech.medivh.classpy.classfile.datatype.U2;

import java.util.ArrayList;
import java.util.List;

/*
CONSTANT_Methodref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
*/
public class ConstantMethodrefInfo extends ConstantFieldrefInfo {

    public String className(ConstantPool constantPool) {
        U2 classIndex = (U2) getParts().get(1);
        ConstantClassInfo classInfo = constantPool.getClassInfo(classIndex.getValue());
        return constantPool.getUtf8String(classInfo.getNameIndex()).replace("/", ".");
    }


    public List<String> paramClassName(ConstantPool constantPool) {
        U2 nameAndTypeIndex = (U2) getParts().get(2);
        ConstantNameAndTypeInfo nameAndTypeInfo = constantPool.getNameAndTypeInfo(nameAndTypeIndex.getValue());
        U2 descIndex = (U2) nameAndTypeInfo.getParts().get(2);
        String constantDesc = constantPool.getConstantDesc(descIndex.getValue());
        int endParamsIndex = constantDesc.indexOf(')');
        String paramsPart = constantDesc.substring(1, endParamsIndex);
        return parseParameterTypes(paramsPart);
    }

    public boolean isVoid(ConstantPool constantPool) {
        U2 nameAndTypeIndex = (U2) getParts().get(2);
        ConstantNameAndTypeInfo nameAndTypeInfo = constantPool.getNameAndTypeInfo(nameAndTypeIndex.getValue());
        U2 descIndex = (U2) nameAndTypeInfo.getParts().get(2);
        String constantDesc = constantPool.getConstantDesc(descIndex.getValue());
        int endParamsIndex = constantDesc.indexOf(')');
        String returnPart = constantDesc.substring(endParamsIndex + 1);
        return "V".equals(returnPart);
    }

    public String methodName(ConstantPool constantPool) {
        U2 nameAndTypeIndex = (U2) getParts().get(2);
        ConstantNameAndTypeInfo nameAndTypeInfo = constantPool.getNameAndTypeInfo(nameAndTypeIndex.getValue());
        return constantPool.getUtf8String(nameAndTypeInfo.getNameIndex());
    }

    private static List<String> parseParameterTypes(String params) {
        List<String> types = new ArrayList<>();
        int i = 0;
        while (i < params.length()) {
            char c = params.charAt(i);
            if (c == 'L') {
                int semicolonIndex = params.indexOf(';', i);
                String className = params.substring(i + 1, semicolonIndex).replace('/', '.');
                types.add(className);
                i = semicolonIndex + 1;
            } else if (c == '[') {
                int arrayDepth = 1;
                while (params.charAt(++i) == '[') {
                    arrayDepth++;
                }
                String elementType = parseBaseType(params.charAt(i));
                types.add(elementType + "[]".repeat(arrayDepth));
                i++;
            } else {
                types.add(parseBaseType(c));
                i++;
            }
        }
        return types;
    }

    private static String parseBaseType(char c) {
        return switch (c) {
            case 'B' -> "byte";
            case 'C' -> "char";
            case 'D' -> "double";
            case 'F' -> "float";
            case 'I' -> "int";
            case 'J' -> "long";
            case 'S' -> "short";
            case 'Z' -> "boolean";
            default -> throw new IllegalArgumentException("Unknown base type: " + c);
        };
    }
}
