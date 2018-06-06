import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld extends Task {
    List<Message> messages = new ArrayList<>();

    public Message createMessage() {
        Message msg = new Message();
        messages.add(msg);
        return msg;
    }

    @Override
    public void execute() throws BuildException {
        for (Message msg:messages) {
            log(msg.getMsg());
        }
    }
}
