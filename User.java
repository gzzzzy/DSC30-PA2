
import java.util.ArrayList;

public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED = "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE = "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    public User(String username, String bio) {
        if (username == null || bio == null)
            throw new IllegalArgumentException();
        this.username = username;
        this.bio = bio;
        rooms = new ArrayList<MessageExchange>();
    }

    public void setBio(String newBio) {
        if (newBio == null)
            throw new IllegalArgumentException();
        bio = new String(newBio);
    }

    public String displayBio() {
        return bio;
    }

    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null)
            throw new IllegalArgumentException();
        try {
            me.addUser(this);
        } catch (Exception e) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
    }

    public void quitRoom(MessageExchange me) {
        if (me == null)
            throw new IllegalArgumentException();
        rooms.remove(me);
        me.removeUser(this, this);
    }

    public void sendMessage(MessageExchange me, String contents, int lines) {
        if(me==null||contents==null) throw new IllegalArgumentException();
        if(!me.addUser(this)) throw new IllegalArgumentException();
        if(lines==-1){
            try {
                TextMessage message=new TextMessage(this, contents));
            } catch(Exception e) {
                System.out.println(e.getMessage());
            } finally {
                rooms.add(me);
            }
        } else {
            try {
                CodeMessage message=new CodeMessage(this, contents, lines);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            } finally {
                rooms.add(me);
            }
        }
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
