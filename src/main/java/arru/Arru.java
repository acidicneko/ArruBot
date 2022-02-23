package arru;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.api.requests.GatewayIntent;


class Arru extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder.createDefault(Dotenv.load().get("BOT_TOKEN"))
                    .setActivity(Activity.playing("with your life"))
                    .enableIntents(GatewayIntent.GUILD_VOICE_STATES)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new Listener())
                    .build();
    }
}