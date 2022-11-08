package arru.info;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.ini4j.*;

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

    public static String getGuildPrefix(String guildID){
        try{
            File prefixFile = new File("prefix.ini");
            if(!prefixFile.exists()){
                try {
                    prefixFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try{
                    FileWriter write = new FileWriter("prefix.ini");
                    write.write("[prefixes]");
                    write.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to prefix file.");
                    e.printStackTrace();
                }
            }
            Wini ini = new Wini(prefixFile);
            if(!ini.get("prefixes").containsKey(guildID)){
                return "arru";
            }
            String prefix = ini.get("prefixes", guildID);
            return prefix;
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return "arru";
    }
}
