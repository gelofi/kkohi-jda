package org.java.kkohi;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listeners extends ListenerAdapter {

    // STARTUP
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // Set status
        Activity activity = Activity.customStatus("Brewing ideas.");
        event.getJDA().getPresence().setActivity(activity);

        // Notify console
        System.out.println("Kkohi now online!"); // Print status.
        super.onReady(event);
    }

    // JOINING A SERVER
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        MessageChannel channel = event.getGuild().getSystemChannel();
        if (channel == null) return;
        channel.sendMessage("hi <3 thank you for adding me! how can i be of service to you today?").queue();
        super.onGuildJoin(event);
    }

    // RECEIVING DMS
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return; // ignore bot messages
        if(event.getChannelType().isGuild()) return; // make it only work for DMs
        MessageChannel channel = event.getChannel();
        channel.sendMessage("hello pi,,").queue();
        super.onMessageReceived(event);
    }
}
