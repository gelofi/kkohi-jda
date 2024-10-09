package org.java.kkohi.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Add implements ICommand {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Returns the sum of two numbers";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER, "augend", "The first number.", true)
                .setMinValue(1)
                .setMaxValue(9999999L));
        data.add(new OptionData(OptionType.INTEGER, "addend", "The second number.", true)
                .setMinValue(1)
                .setMaxValue(9999999L));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping num1 = event.getOption("num1");
        assert num1 != null;
        int augend = num1.getAsInt();
        OptionMapping num2 = event.getOption("num2");
        assert num2 != null;
        int addend = num2.getAsInt();

        int result = augend + addend;
        event.reply(augend + addend + " = " + result).queue();
    }
}
