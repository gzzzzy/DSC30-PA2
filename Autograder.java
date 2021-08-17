
import java.util.ArrayList;
import java.util.List;

public class Autograder implements MessageExchange {

    // time allowed
    private static final int DEFAULT_ALLOTTED_TIME = 5;

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    // time exchange index
    private static final int TIME_EXCHANGE_IDX = 10;

    /* instance variables */
    private DoublyLinkedList waitlist, finished;
    private int burstTime, waitTime;

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;
    private Tutor tutor;

    public Autograder(Tutor tutor) {
        this.tutor = tutor;
        users = new ArrayList<User>();
        users.add(tutor);
        log = new ArrayList<Message>();
        waitlist = new DoublyLinkedList();
        finished = new DoublyLinkedList();
        burstTime = waitTime = 0;
    }

    public ArrayList<Message> getLog(User requester) {
        if (requester instanceof Tutor) {
            tutor.fetchMessage(this);
        } else {
            requester.fetchMessage(this);
        }
        return null;
    }

    public boolean addUser(User u) {
        if (users.contains(u))
            return false;
        return users.add(u);
    }

    public boolean removeUser(User requester, User u) {
        if ((requester == tutor && u != tutor) || (requester != tutor && requester == u)) {
            return users.remove(u);
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean recordMessage(Message m) {
        return log.add(m);
    }

    public String resolveAllProblems(User requester) throws OperationDeniedException {
        int min, proc, len;
        waitTime=burstTime=0;
        for (Message message : log) {
            if (message instanceof CodeMessage) {
                if (((CodeMessage) message).getExtension() == "java") {
                    min = (int) Math.ceil((double) ((CodeMessage) message).getLines() / 10);
                    waitlist.add(min);
                }
            }
        }
        len = waitlist.size();
        for (int i = 0; i < len; ++i) {
        }
        while (!waitlist.isEmpty()) {
            proc = waitlist.remove(0);
            if (proc > DEFAULT_ALLOTTED_TIME) {
                burstTime += DEFAULT_ALLOTTED_TIME;
                waitTime += (DEFAULT_ALLOTTED_TIME * waitlist.size());
                waitlist.add(proc - 5);
            } else {
                burstTime += proc;
                waitTime += (proc * waitlist.size());
            }
        }
        return String.format("All tasks are handled within %d units of burst time and %d units of wait time.", burstTime,waitTime);
    }

}