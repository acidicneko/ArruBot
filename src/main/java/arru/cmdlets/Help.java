package arru.cmdlets;

import arru.utility.Cmd;
import arru.utility.CmdManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Help implements Cmd {
    @Override
    public void execute(List<String> argv, MessageReceivedEvent event){
        event.getChannel().sendMessage("Ayy wait lemme check...").queue();
        CmdManager manager = new CmdManager();
        if(!argv.isEmpty()){
            event.getChannel().sendMessage(manager.getCmd(argv.get(0)).printHelp()).queue();
        } else {
            event.getChannel().sendMessage("Help for wot? OwO\"").queue();
        }
    }

    @Override
    public String cmdName(){
        return "help";
    }

    @Override 
    public String printHelp(){
        return "Provides help for given command.";
    }
}
