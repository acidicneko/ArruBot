package arru.cmdlets;

import java.util.List;
import java.util.Collections;

import arru.utility.Cmd;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import arru.utility.AudioPlayer.GuildMusicManager;
import arru.utility.AudioPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import com.github.natanbc.lavadsp.timescale.TimescalePcmAudioFilter;

public class Speedup implements Cmd {

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
            channel.sendMessage("You aren't in any voice channel.").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("ewe you just dumb. We aren't in same voice channel.").queue();
            return;
        }

        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());

        musicManager.audioPlayer.setFilterFactory((track, format, output)->{
            TimescalePcmAudioFilter audioFilter = new TimescalePcmAudioFilter(output, format.channelCount, format.sampleRate);
            audioFilter.setSpeed(Float.parseFloat(argv.get(0)));
            return Collections.singletonList(audioFilter);
        });
    }

    @Override
    public String cmdName() {
        return "speedup";
    }

    @Override
    public String printHelp() {
        return "Speed up the music.";
    }  
}
