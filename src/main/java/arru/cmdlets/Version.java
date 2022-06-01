package arru.cmdlets;

import java.awt.Color;
import java.time.Instant;
import java.util.List;

import arru.info.Constants;
import arru.utility.Cmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Version implements Cmd {

    @Override
    public void execute(List<String> argv, MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(event.getJDA().getSelfUser().getName() +
                        "#" + event.getJDA().getSelfUser().getDiscriminator(), null,
                        event.getJDA().getSelfUser().getAvatarUrl());
        eb.setThumbnail("https://raw.githubusercontent.com/clawbhaiya/ArruBot/main/images/arru-img.jpg");
        eb.setTitle(":cat: Arru the cat");
        eb.setColor(Color.BLUE);
        eb.setDescription("Made with :brown_heart: by Acidicneko.");
        eb.addField("Version", Constants.version, false);
        eb.addField("Written in", ":coffee: Java (JDK17)", false);
        eb.setFooter("ArruChan", event.getJDA().getSelfUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());
        event.getChannel().sendMessageEmbeds(eb.build()).queue();
    }

    @Override
    public String cmdName() {
        return "version";
    }

    @Override
    public String printHelp() {
        return "Print version and build information.";
    }
    
}
