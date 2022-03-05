package arru.cmdlets;

import arru.utility.Cmd;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Say implements Cmd {
    public void execute(List<String> argv, MessageReceivedEvent event){
        String reply = new String();
        for(String temp: argv) reply += temp + " ";
        event.getChannel().sendMessage(reply).queue();
        event.getMessage().delete().queue();
    }

    public String cmdName(){
        return "say";
    }

    public String printHelp(){
        return "Arru gonna say smth UwU";
    }
}
