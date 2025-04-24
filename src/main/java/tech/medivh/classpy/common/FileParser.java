package tech.medivh.classpy.common;

public interface FileParser {

    FileParser NOP = data -> new FilePart() {};

    FilePart parse(byte[] data);

}
