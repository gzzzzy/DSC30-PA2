import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG = "This message cannot be fetched because you are not a premium user.";

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    public Student(String username, String bio) {
        super(username, bio);
    }

    public String fetchMessage(MessageExchange me) {
        if (me == null)
            throw new IllegalArgumentException();
        if (!me.addUser(this))
            throw new IllegalArgumentException();
        ArrayList<Message> arr = me.getLog(this);
        String re = new String();
        int len = arr.size(), i = 0;
        if (len > 100) {
            i = len - 100;
        }
        for (; i < len; ++i) {
            if (arr.get(i) instanceof TextMessage) {
                re += (arr.get(i).getContents() + '\n');
            } else {
                re += (FETCH_DENIED_MSG + '\n');
            }
        }
        return re;
    }

    public String displayName() {
        return username;
    }
}
