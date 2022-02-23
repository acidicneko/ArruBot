package arru.cmdlets;

import java.util.List;

import arru.utility.Cmd;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;


public class Join implements Cmd {
    @Override
    public void execute(List<String> argv, MessageReceivedEvent event){
        if(event.getGuild().getSelfMember().getVoiceState().inAudioChannel()){
            event.getChannel().sendMessage("Mew already in voice channel.").queue();
        }

        if(event.getMessage().getMember().getVoiceState().getChannel() == null){
            event.getChannel().sendMessage("ewe you lier\nYou are not in any voice channel").queue();
            return;
        } else {
            final AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.openAudioConnection(event.getMessage().getMember().getVoiceState().getChannel());
            event.getChannel().sendMessage("Joined the voice channel: "
                                            + event.getMessage().getMember()
                                                .getVoiceState().getChannel().getName()).queue();
        }
    }

    @Override
    public String printHelp(){
        return "Join the voice channel in which the user is present.";
    }

    @Override
    public String cmdName(){
        return "join";
    }
    
}
