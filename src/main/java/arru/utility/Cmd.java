package arru.utility;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.List;

public interface Cmd {
    void execute(List<String> argv, MessageReceivedEvent event);
    String cmdName();
    String printHelp();
}
