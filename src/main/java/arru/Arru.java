package arru;

import javax.security.auth.login.LoginException;

import arru.info.Constants;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.requests.GatewayIntent;


class Arru extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        Constants.botPrefix = System.getenv("BOT_PREFIX");//Dotenv.load().get("BOT_PREFIX");
        JDABuilder.createDefault(System.getenv("BOT_TOKEN"))
                    .setActivity(Activity.playing("with your life"))
                    .enableIntents(GatewayIntent.GUILD_VOICE_STATES)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new Listener())
                    .build();
    }
}