package org.java.kkohi;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.java.kkohi.commands.Ping;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) throws LoginException {
        // Turn the bot ONLINE.
        JDA jda = JDABuilder.createDefault(Token.TOKEN).build();

        jda.addEventListener(new Listeners());
        jda.addEventListener(new Ping());
    }
}
