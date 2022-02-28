package arru.utility;

import java.awt.Color;
import java.time.Instant;

import javax.annotation.Nonnull;

import arru.info.GuildInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Logger extends ListenerAdapter {

    @Override 
    public void onGuildReady(@Nonnull GuildReadyEvent event){
        GuildInfo.addGuild(event.getGuild());
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(":cat: Arru Online UwU");
        eb.setColor(Color.RED);
        eb.setAuthor(event.getJDA().getSelfUser().getName() +
                        "#" + event.getJDA().getSelfUser().getDiscriminator(), null,
                        event.getJDA().getSelfUser().getAvatarUrl());
        eb.setDescription("Arru is Here! Throw some party dude");
        eb.setFooter("ArruChan", event.getJDA().getSelfUser().getAvatarUrl());
        event.getGuild().getTextChannelsByName("server-logs", true).get(0)
                        .sendMessageEmbeds(eb.build())
                        .queue();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event){
        if(!event.getAuthor().isBot()){
            final TextChannel logChannel = event.getGuild()
                                            .getTextChannelsByName("server-logs", true)
                                            .get(0);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.MAGENTA);
            eb.setAuthor(event.getMessage().getAuthor().getName() + 
                            "#" + event.getMessage().getAuthor().getDiscriminator(), null,
                            event.getMessage().getAuthor().getAvatarUrl());
            
            eb.setDescription("**Message sent in** <#" + event.getMessage().getChannel().getId() + "> [Jump to Message]("
                            + event.getMessage().getJumpUrl() + ")");

            eb.addField("Content", event.getMessage().getContentRaw(), false);
            if (!event.getMessage().getAttachments().isEmpty()) {
                eb.setImage(event.getMessage().getAttachments().get(0).getUrl());
            }
            eb.setTimestamp(Instant.now());
            eb.setFooter("ArruChan", event.getJDA().getSelfUser().getAvatarUrl());
            eb.setThumbnail(event.getGuild().getIconUrl());
            logChannel.sendMessageEmbeds(eb.build()).queue();
        }
    }

}
