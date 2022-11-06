package arru.cmdlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.ini4j.*;

import arru.utility.Cmd;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Prefix implements Cmd {

    @Override
    public void execute(List<String> argv, MessageReceivedEvent event) {
        File prefixFile = new File("prefix.ini");
        if(!prefixFile.exists()){
            try {
                prefixFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                FileWriter write = new FileWriter("prefix.ini");
                write.write("[prefixes]");
                write.close();
            } catch (IOException e) {
                System.out.println("An error occurred while writing to prefix file.");
                e.printStackTrace();
            }
        }
        try{
            Wini ini = new Wini(new File("prefix.ini"));
            ini.put("prefixes", event.getGuild().getId(), argv.get(0));
            ini.store();
            event.getChannel().sendMessage("Bot prefix set to: " + argv.get(0) + " for current server.").queue();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
        
    }

    @Override
    public String cmdName() {
        return "set-prefix";
    }

    @Override
    public String printHelp() {
        return "Set bot prefix for current server.";
    }
    
}
