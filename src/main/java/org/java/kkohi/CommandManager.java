package org.java.kkohi;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    private final List<ICommand> commands = new ArrayList<>();

    // Upsert the Command in the Guilds (This updates the # of commands)
    @Override
    public void onReady(@NotNull ReadyEvent event){
        for(Guild guild : event.getJDA().getGuilds()){
            for(ICommand command : commands){
                guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
            }
        }
    }

    // Call the command when using slash and execute
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event){
        for(ICommand commands : commands){
            if (commands.getName().equals(event.getName())){
                commands.execute(event);
                return;
            }
        }
    }

    public void add(ICommand command){
        commands.add(command);
    }

}
