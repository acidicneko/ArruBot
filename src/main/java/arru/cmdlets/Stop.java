package arru.cmdlets;

import java.util.List;

import arru.utility.Cmd;
import arru.utility.AudioPlayer.GuildMusicManager;
import arru.utility.AudioPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Stop implements Cmd {

    @Override
    public void execute(List<String> argv, MessageReceivedEvent event) {
        
        final TextChannel channel = event.getTextChannel();
        final Member self = event.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            channel.sendMessage("Mew not in any voice channel. pwp\"").queue();
            return;
        }

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()){
            channel.sendMessage("You aren't in any voice channel.\nWhere are ya gonna listen this?").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("ewe you just dumb. We aren't in same voice channel.").queue();
            return;
        }

        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
        
        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();

        event.getChannel().sendMessage("Mew stopped the player UwU").queue();
    }

    @Override
    public String cmdName() {
        return "stop";
    }

    @Override
    public String printHelp() {
        return "Stops the current song and clears the queue.";
    }
    
    
}
