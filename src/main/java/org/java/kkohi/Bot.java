package org.java.kkohi;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

// Import Commands
import org.java.kkohi.commands.fun.Kiss;
import org.java.kkohi.commands.fun.Ship;
import org.java.kkohi.commands.fun.Topic;
import org.java.kkohi.commands.tools.Arithmetic;
import org.java.kkohi.commands.tools.Help;
import org.java.kkohi.commands.tools.Ping;
import org.java.kkohi.commands.tools.Purge;

public class Bot {
    public static void main(String[] args) {
        // Turn Kkohi ONLINE.
        JDA jda = JDABuilder.createDefault(Keys.TOKEN).build();

        // Listen and Manage Commands
        jda.addEventListener(new Listeners());
        CommandManager manager = new CommandManager();

        // Upsert Commands

        // Tool Commands
        manager.add(new Help());
        manager.add(new Ping());
        manager.add(new Arithmetic());
        manager.add(new Purge());

        // Fun Commands
        manager.add(new Ship());
        manager.add(new Kiss());
        manager.add(new Topic());


        // Execute the Manager
        jda.addEventListener(manager);
    }
}