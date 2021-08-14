package DSC30.PA2;

import java.util.Arrays;
import java.util.List;

public class CodeMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT = "The source path is not valid.";

    // Error message to use in OperationDeniedException for the invalid line number
    private static final String INVALID_CODE = "The files are not long enough.";

    // input validation criteria
    private static final String[] ACCEPTABLE_EXTENSIONS = new String[] { "html", "java", "py", "mjs", "ipynb", "md",
            "yml" };

    // instance variable
    private String extension;
    private int lines;

    public CodeMessage(User sender, String codeSource, int lines) throws OperationDeniedException {
        super(sender);
        if (codeSource == null) {
            throw new IllegalArgumentException();
        }
        this.contents = new String(codeSource);
        int locOfPoint = codeSource.lastIndexOf('.');
        extension = codeSource.substring(locOfPoint, codeSource.length()).toLowerCase();
        List<String> list = Arrays.asList(ACCEPTABLE_EXTENSIONS);
        if (!list.contains(extension))
            throw new OperationDeniedException(INVALID_INPUT);
        this.lines = lines;
        if (lines < 10)
            throw new OperationDeniedException(INVALID_CODE);
    }

    public String getContents() {
        return String.format("%s %s: Code at %s", getSender().displayName(), getDate().toString(), contents);
    }

    public String getExtension() {
        return extension;
    }

    public int getLines() {
        return lines;
    }

}
