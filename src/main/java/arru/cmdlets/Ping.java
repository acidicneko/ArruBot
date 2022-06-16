package arru.cmdlets;

import java.util.List;

import arru.utility.Cmd;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping implements Cmd {

    @Override
    public void execute(List<String> argv, MessageReceivedEvent event) {
        long time = System.currentTimeMillis();
        event.getChannel().sendMessage("Checking Bot response Time...")
            .queue(response -> {
                response.editMessageFormat("Bot response Time: %d ms", System.currentTimeMillis() - time).queue();
            });
    }

    @Override
    public String cmdName() {
        return "ping";
    }

    @Override
    public String printHelp() {
        return "Shows the bot's ping and response time";
    }
    
}
