package arru.cmdlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.awt.Color;
import java.io.BufferedReader;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import arru.utility.Cmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Quote implements Cmd {

    @Override
    public void execute(List<String> argv, MessageReceivedEvent event) {
        try {
            URL url = new URL("https://zenquotes.io/api/random");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String parseLine = new String();
            String result = new String();
            while((parseLine = br.readLine())!= null){
                result += parseLine;
            }

            JSONParser parser = new JSONParser();
            Object object = parser.parse(result);
            JSONArray array = (JSONArray) object;

            JSONObject jsonObject = (JSONObject) array.get(0);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Get up you dead soul!");
            eb.setColor(Color.ORANGE);
            eb.addField("Say", jsonObject.get("q").toString(), false);
            eb.addBlankField(false);
            eb.addField("By", jsonObject.get("a").toString(), false);
            event.getChannel().sendMessageEmbeds(eb.build()).queue();
            
        } catch (MalformedURLException e) {
            event.getChannel().sendMessage("Can't get contents of URL.").queue();
            e.printStackTrace();
        } catch (IOException e) {
            event.getChannel().sendMessage("Can't read contents of URL.").queue();
            e.printStackTrace();
        } catch (ParseException e) {
            event.getChannel().sendMessage("Can't parse contents of URL.").queue();
            e.printStackTrace();
        }
    }

    @Override
    public String cmdName() {
        return "quote";
    }

    @Override
    public String printHelp() {
        return "Provides a quote to motivate your dead soul.";
    }
    
    
}
