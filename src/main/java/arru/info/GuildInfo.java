package arru.info;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.Guild;

public class GuildInfo {
    public static Map<String, Guild> guilds = new HashMap<>();

    public static void addGuild(Guild guild){
        if(!GuildInfo.guilds.containsKey(guild.getName())){
            System.out.printf("Adding guild: %s\n", guild.getName());
            GuildInfo.guilds.put(guild.getName(), guild);
        }
    }

    public static Guild getLocalGuild(String name){
        return GuildInfo.guilds.get(name);
    }

    public static Guild getFirstGuild(){
        String key = GuildInfo.guilds.keySet().stream().findFirst().get();
        return getLocalGuild(key);
    }
}
