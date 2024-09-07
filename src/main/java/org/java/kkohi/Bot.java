package org.java.kkohi;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

// Commands to be imported
import org.java.kkohi.commands.Ping;

public class Bot {
    public static void main(String[] args) throws LoginException {
        // Turn the bot ONLINE.
        JDA jda = JDABuilder.createDefault(Token.TOKEN).build();

        // Listen and add commands
        jda.addEventListener(new Listeners());
        CommandManager manager = new CommandManager();

        // Upsert Commands
        manager.add(new Ping());
        jda.addEventListener(manager);
    }
}
