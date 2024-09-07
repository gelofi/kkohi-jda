package org.java.kkohi.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.jetbrains.annotations.NotNull;
import java.time.temporal.ChronoUnit;

public class Ping extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(!event.getName().equals("ping")) return;
        event.reply("Pinging... " + event.getJDA().getGatewayPing() + "ms!").queue();
        super.onSlashCommandInteraction(event);
    }
}
