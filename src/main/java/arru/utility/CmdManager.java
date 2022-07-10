package arru.utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arru.cmdlets.*;
import arru.info.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CmdManager {
    private final Map<String, Cmd> commands = new HashMap<>();


    public CmdManager(){
        addCmd(new Say());
        addCmd(new Off());
        addCmd(new Help());
        addCmd(new Join());
        addCmd(new Leave());
        addCmd(new Version());
        addCmd(new Play());
        addCmd(new Stop());
        addCmd(new Quote());
        addCmd(new Kick());
        addCmd(new Skip());
        addCmd(new Ping());
    }

    public void addCmd(Cmd command){
        if (!commands.containsKey(command.cmdName())) {
            commands.put(command.cmdName(), command);
        }
    }

    public Cmd getCmd(String cmdName){
        return commands.get(cmdName.toLowerCase());
    }

    public void handleMsg(MessageReceivedEvent event){
        
        if(event.getAuthor().isBot())   return;

        final String msg = event.getMessage().getContentRaw();
        final String[] argv = msg.split(" ");

        if(argv[0].contentEquals(Constants.botPrefix)){

            if(commands.containsKey(argv[1].toLowerCase())){
                final List<String> arguments = Arrays.asList(argv).subList(2, argv.length);
                commands.get(argv[1].toLowerCase()).execute(arguments, event);
            } else {
                event.getMessage().reply("uknown command: " + argv[1]).queue();
            }

        }
    }
}
