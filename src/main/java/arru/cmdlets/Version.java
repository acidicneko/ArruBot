package arru.cmdlets;

import java.awt.Color;
import java.util.List;

import arru.info.Constants;
import arru.utility.Cmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Version implements Cmd {

    @Override
    public void execute(List<String> argv, MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(":cat: Arru the cat");
        eb.setColor(Color.BLUE);
        eb.addField("Version", Constants.version, false);
        eb.addBlankField(false);
        eb.addField("Written in", ":coffee: Java (JDK17)", false);
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
