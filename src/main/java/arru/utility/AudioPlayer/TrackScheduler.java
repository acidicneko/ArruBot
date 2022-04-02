package arru.utility.AudioPlayer;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import arru.info.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class TrackScheduler extends AudioEventAdapter {
    public final AudioPlayer player;
    public final BlockingQueue<AudioTrack> queue;
    private TextChannel lastChannel = null;

    public TrackScheduler(AudioPlayer newPlayer){
        this.player = newPlayer;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack track, MessageReceivedEvent event){
        lastChannel = event.getMessage().getTextChannel();
        if(!this.player.startTrack(track, true)){
            this.queue.offer(track);
        }
    }

    public void nextTrack(){
        AudioTrack track = this.queue.poll();
        if(lastChannel != null && track != null){
            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor(Constants.selfMember.getName() +
                        "#" + Constants.selfMember.getDiscriminator(), null,
                        Constants.selfMember.getAvatarUrl());
            eb.setTitle(":notes: Now Playing");
            eb.setColor(Color.WHITE);
            String videoID = track.getInfo().uri.substring(track.getInfo().uri.lastIndexOf("?") + 3);
            String thumbnailURL = "https://img.youtube.com/vi/" + videoID + "/hqdefault.jpg";
            eb.setImage(thumbnailURL);
            eb.addField("Name", track.getInfo().title, false);
            eb.addField("By", track.getInfo().author, false);
            eb.addField("YT Link", track.getInfo().uri, false);
            eb.setFooter("ArruChan", Constants.selfMember.getAvatarUrl());
            lastChannel.sendMessageEmbeds(eb.build()).queue();
        }
        this.player.startTrack(track, false);
    }
    
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason reason){
        if(reason.mayStartNext){
            nextTrack();
        }
    }
}
