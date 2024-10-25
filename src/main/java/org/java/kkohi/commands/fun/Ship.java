package org.java.kkohi.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Ship implements ICommand {

    @Override
    public String getName() {
        return "ship";
    }

    @Override
    public String getDescription() {
        return "Ship two people! Full of love!";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.USER, "user_one", "Who do you want to ship?", true));
        data.add(new OptionData(OptionType.USER, "user_two", "Who do you want to ship them with?", true));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            OptionMapping userOne = event.getOption("user_one"), userTwo = event.getOption("user_two");
            assert userOne != null;
            assert userTwo != null;
            // the mentioned
            String shipOne = userOne.getAsMember().getAsMention();
            String shipTwo = userTwo.getAsMember().getAsMention();

            // Generate a number from 1-100
            RandomGenerator randomGenerator = new Random();
            int randNum = randomGenerator.nextInt(1, 100);

            // do not fight us
            if ((userOne.getAsMember().getIdLong() == 583328224491208863L ) && (userTwo.getAsMember().getIdLong() == 870321446428229632L)){
                randNum = 100;
            }

            // Select a splash text based on percentage
            String splashText;
            if (randNum >= 50){
                splashText = "Almost there, lovebirds!";
            } else {
                splashText = "Take it further!";
            } if (randNum == 100){
                splashText = "Get married!";
            }

            event.reply(shipOne + " and " + shipTwo + " are " + randNum + "% compatible! " + splashText).queue();
        } catch (Exception e) {
            event.reply("Love finds a way to challenge destiny, but not today...").queue();
            throw new RuntimeException(e);
        }

    }
}