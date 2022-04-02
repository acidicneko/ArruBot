package arru.utility.AudioPlayer;

import java.awt.Color;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    
    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;
    
    public PlayerManager(){
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }
    
    public GuildMusicManager getMusicManager(Guild guild){
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildID) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);
            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
            return guildMusicManager;
        });
    }

    public void loadAndPlay(TextChannel channel, String trackUrl, MessageReceivedEvent event){
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {

            @Override
            public void loadFailed(FriendlyException arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void noMatches() {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                final List<AudioTrack> tracks = playlist.getTracks();

                /*for (final AudioTrack track: tracks){
                    musicManager.scheduler.queue(track);
                }*/
                if(!tracks.isEmpty()){
                    musicManager.scheduler.queue(tracks.get(0), event);
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setAuthor(event.getMessage().getAuthor().getName() +
                                    "#" + event.getMessage().getAuthor().getDiscriminator(), null,
                                    event.getMessage().getAuthor().getAvatarUrl());
                    eb.setTitle(":notes: Track added to queue");
                    eb.setColor(Color.YELLOW);
                    eb.addField("Name", tracks.get(0).getInfo().title, false);
                    eb.addField("By", tracks.get(0).getInfo().author, false);
                    eb.addField("YT Link", tracks.get(0).getInfo().uri, false);
                    eb.setFooter("ArruChan", event.getJDA().getSelfUser().getAvatarUrl());
                    eb.setTimestamp(Instant.now());
                    String videoID = tracks.get(0).getInfo().uri.substring(tracks.get(0).getInfo().uri.lastIndexOf("?") + 3);
                    String thumbnailURL = "https://img.youtube.com/vi/" + videoID + "/hqdefault.jpg";
                    eb.setImage(thumbnailURL);
                    channel.sendMessageEmbeds(eb.build()).queue();
                }
                
            }

            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track, event);
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Track added to queue");
                eb.setColor(Color.YELLOW);
                eb.addField("Name", track.getInfo().title, false);
                eb.addField("By", track.getInfo().author, false);
                channel.sendMessageEmbeds(eb.build()).queue();
            }
            
        });
    }

    public static PlayerManager getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
}
