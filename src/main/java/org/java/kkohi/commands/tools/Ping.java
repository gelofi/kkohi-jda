package org.java.kkohi.commands.tools;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;

import java.util.List;

public class Ping implements ICommand {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Returns Kkohi ping in milliseconds.";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Kkohi ping is `" + event.getJDA().getGatewayPing() + "ms`!").queue();
    }
}
