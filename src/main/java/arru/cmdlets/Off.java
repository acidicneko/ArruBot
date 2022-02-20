package arru.cmdlets;

import java.util.List;

import arru.utility.Cmd;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Off implements Cmd {
    @Override
    public void execute(List<String> argv, MessageReceivedEvent event){
        event.getChannel().sendMessage("Paiii Paiiiii").queue();
        System.out.println("Shutting down Arru-bot.");
        System.exit(0);
    }

    @Override
    public String printHelp(){
        return "Shutdowns Arru Bot";
    }

    @Override
    public String cmdName(){
        return "off";
    }
    
}
