package org.java.kkohi.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;
import org.java.kkohi.Keys;

import java.util.ArrayList;
import java.util.List;

public class Pinned implements ICommand {
    @Override
    public String getName() { return "pinned"; }

    @Override
    public String getDescription() { return "Returns the set pinned message."; }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.STRING, "message", "Pin a message!", false));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            OptionMapping message = event.getOption("message");
            if (message == null){
                if (Keys.PINNED == null){
                    event.reply("No message is pinned yet, tbh.").queue();
                } else {
                    // calls the Accessor Method
                    event.reply(Keys.getPinned()).queue();
                }
            } else {
                // calls the Mutator Method
                Keys.setPinned(message.getAsString());
                event.reply("I pinned `" + message.getAsString() + "` now.").queue();
            }
        } catch (Exception e) {
            event.reply("There was an error... but just try again.").queue();
            throw new RuntimeException(e);
        }
    }
}
