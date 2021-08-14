package DSC30.PA2;

import java.util.ArrayList;

public class Tutor extends User {

    // instance variable
    private String customTitle;

    public Tutor(String username, String bio) {
        super(username, bio);
        customTitle = "Tutor";
    }

    public String fetchMessage(MessageExchange me) {
        if (me == null)
            throw new IllegalArgumentException();
        if (!me.addUser(this))
            throw new IllegalArgumentException();
        ArrayList<Message> arr = me.getLog(this);
        String re = new String();
        for (int i = 0; i < arr.size(); ++i) {
            re += (arr.get(i).getContents() + '\n');
        }
        return re;
    }

    public String displayName() {
        return String.format("<%s> %s", customTitle, username);
    }

    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    public MessageExchange createAutograder(ArrayList<User> users) {
        if (users == null)
            throw new IllegalArgumentException();
        Autograder auto = new Autograder(this);
        for (User user : users) {
            try {
                user.joinRoom(auto);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
