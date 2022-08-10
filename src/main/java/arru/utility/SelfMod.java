package arru.utility;

import java.util.Arrays;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SelfMod {
    final String[] exceptionList = System.getenv("EXCEPTION_LIST").split(":");

    public void handleMsg(MessageReceivedEvent event){
        if(Arrays.asList(exceptionList).contains(event.getAuthor().getId())){
            return;
        }
        for(Member member: event.getMessage().getMentionedMembers()){
            if(member.getId().equals(exceptionList[1])){
                event.getChannel().sendMessage("My master is busy. You better not disturb him."
                                        + "\n:)").queue();
                event.getMessage().delete().queue();
                return;
            }
        }
    }
}
