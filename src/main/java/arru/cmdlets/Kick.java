package arru.cmdlets;

import java.util.List;

import arru.utility.Cmd;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Kick implements Cmd {

    @Override
    public void execute(List<String> argv, MessageReceivedEvent event) {
        if(!event.getMessage().getMember().hasPermission(Permission.KICK_MEMBERS))   return;

        if(argv.size() == 0){
            event.getChannel().sendMessage("No member specified. Whom you wanna kick? Air?").queue();
        } else if(event.getMessage().getMentionedMembers().size() == 0){
            
            for(String id: argv){
                event.getGuild().kick(id).queue();
            }
        } else {
            for(Member member: event.getMessage().getMentionedMembers()){
                if(!event.getGuild().getSelfMember().canInteract(member)){
                    event.getChannel().sendMessage("Cannot kick the specified member.").queue();
                    return;
                }
                event.getGuild().kick(member).queue();
            }
        }
    }

    @Override
    public String cmdName() {
        return "kick";
    }

    @Override
    public String printHelp() {
        return "Kick the specified members.";
    }
    
}
