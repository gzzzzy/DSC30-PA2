
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH = "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    public TextMessage(User sender, String text) throws OperationDeniedException {
        super(sender);
        if (text == null)
            throw new IllegalArgumentException();
        if (text.length() > MAX_TEXT_LENGTH)
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        this.contents = new String(text);
    }

    // Yuxuan [16:38:36.868882500]: A sample text message.
    public String getContents() {
        return String.format("%s %s: %s", getSender().displayName(), getDate().toString(), contents);
    }

}
