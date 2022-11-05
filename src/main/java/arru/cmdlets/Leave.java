package arru.cmdlets;

import arru.utility.Cmd;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Leave implements Cmd {
    
    @Override
    public void execute(List<String> argv, MessageReceivedEvent event){
        if(event.getMessage().getMember().getVoiceState().getChannel() == null){
            event.getChannel().sendMessage("You sure you aint high?\nI am not in your voice channel").queue();
            return;
        }
        event.getGuild().getAudioManager().closeAudioConnection();
    }

    @Override
    public String printHelp(){
        return "Leave the audio channel in which the user is present.";
    }
    
    @Override
    public String cmdName(){
        return "leave";
    }
}
