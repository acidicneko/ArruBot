package arru.cmdlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
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

            String quote = jsonObject.get("q").toString();
            String author = jsonObject.get("a").toString();

            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor(event.getJDA().getSelfUser().getName() +
                        "#" + event.getJDA().getSelfUser().getDiscriminator(), null,
                        event.getJDA().getSelfUser().getAvatarUrl());
            eb.setTitle("Get up you dead soul!");
            eb.setThumbnail("https://raw.githubusercontent.com/clawbhaiya/ArruBot/main/images/arru-img.jpg");
            eb.setDescription(quote);
            eb.addField("By", author, false);
            eb.setColor(Color.GREEN);
            eb.setFooter("ArruChan", event.getJDA().getSelfUser().getAvatarUrl());
            eb.setTimestamp(Instant.now());
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
