package arru.utility;

import java.awt.Color;

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
        eb.addField("Arru is Here!", "Throw some party dude UwU", true);
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
            eb.setTitle(":cat: New mwessage!");
            eb.setColor(Color.PINK);
            eb.addField("Server", event.getGuild().getName(), true);
            eb.addField("Channel", event.getChannel().getName(), true);
            eb.addBlankField(false);
            eb.addField("By", event.getAuthor().getName(), true);
            eb.addField("Contents", event.getMessage().getContentRaw(), true);
            logChannel.sendMessageEmbeds(eb.build()).queue();
        }
    }

}
