package org.java.kkohi;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

// Import Commands
import org.java.kkohi.commands.*;

public class Bot {
    public static void main(String[] args) {
        // Turn Kkohi ONLINE.
        JDA jda = JDABuilder.createDefault(Token.TOKEN).build();

        // Listen and Manage Commands
        jda.addEventListener(new Listeners());
        CommandManager manager = new CommandManager();

        // Upsert Commands
        manager.add(new Ping());
        manager.add(new Arithmetic());
        manager.add(new Ship());
        manager.add(new Kiss());
        manager.add(new Topic());

        // Execute the Manager
        jda.addEventListener(manager);
    }
}