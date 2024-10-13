package org.java.kkohi.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Arithmetic implements ICommand {
    private static final Logger log = LoggerFactory.getLogger(Arithmetic.class);

    @Override
    public String getName() {
        return "math";
    }

    @Override
    public String getDescription() {
        return "Returns the result of two numbers";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.STRING, "operation", "The operation to be used.", true)
                .addChoice("add", "+")
                .addChoice("subtract", "-")
                .addChoice("multiply", "*")
                .addChoice("divide", "/")
                .addChoice("modulo", "%")
                .addChoice("exponentation", "^"));
        data.add(new OptionData(OptionType.INTEGER, "first_number", "The first number. This is the augend, minuend, multiplicand, dividend, or base.", true)
                .setMinValue(1)
                .setMaxValue(9999999L));
        data.add(new OptionData(OptionType.INTEGER, "second_number", "The second number. This is the addend, subtrahend, multiplier, divisor, or exponent.", true)
                .setMinValue(1)
                .setMaxValue(9999999L));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            OptionMapping operation = event.getOption("operation");
            OptionMapping num1 = event.getOption("first_number");
            assert num1 != null;
            OptionMapping num2 = event.getOption("second_number");
            assert num2 != null;

            int firstN = num1.getAsInt();
            int secondN = num2.getAsInt();

            // logic for the oepration
            int result = 0;

            switch (Objects.requireNonNull(operation).getAsString()) {
                case "+" -> result = firstN + secondN;
                case "-" -> result = firstN - secondN;
                case "*" -> result = firstN * secondN;
                case "/" -> result = firstN / secondN;
                case "%" -> result = firstN % secondN;
                case "^" -> result = (int) Math.pow(firstN, secondN);
            }

            event.reply(firstN + " " + operation.getAsString() + " " + secondN + " = " + result).queue();
        } catch (Exception e){
            log.error("e: ", e);
            event.reply("An error occured. Did you insert correct values?").queue();
        }
    }
}
