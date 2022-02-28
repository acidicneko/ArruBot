package arru.cmdlets;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import arru.utility.Cmd;
import arru.utility.AudioPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Play implements Cmd {

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

        if(argv.isEmpty()){
            channel.sendMessage("what to play huh?").queue();
            return;
        }

        String link = String.join(" ", argv);

        if(!isUrl(link)){
            link = "ytsearch:" + link;
        }


        PlayerManager.getINSTANCE()
                .loadAndPlay(channel, link, event);

    }

    @Override
    public String cmdName() {
        return "play";
    }

    @Override
    public String printHelp() {
        return "Plays the song from YouTube";
    }

    private boolean isUrl(String url){
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

}
