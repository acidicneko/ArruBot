package arru;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;

import arru.utility.CmdManager;
import arru.utility.Logger;

public class Listener extends ListenerAdapter {

    CmdManager manager = new CmdManager();
    Logger logger = new Logger();

    @Override
    public void onReady(@Nonnull ReadyEvent event){
        System.out.println(event.getJDA().getSelfUser().getName() + " is online.");
    }

    @Override
    public void onGuildReady(@Nonnull GuildReadyEvent event){
        logger.onGuildReady(event);
        
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event){
        manager.handleMsg(event);
        logger.onMessageReceived(event);
    }

}
