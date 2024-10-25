package org.java.kkohi.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Kiss implements ICommand {

    @Override
    public String getName() {
        return "kiss";
    }

    @Override
    public String getDescription() {
        return "Kiss a user!";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.USER, "user", "Who do you want to kiss?", true));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            OptionMapping user = event.getOption("user");
            assert user != null;
            // the mentioned
            String kissed = user.getAsMember().getAsMention();
            // the command executioner
            String kisser = event.getMember().getAsMention();
            event.reply(kisser + " kissed " + kissed + "!").queue();
        } catch (Exception e) {
            event.reply("It seems your kiss did not reach them...").queue();
            throw new RuntimeException(e);
        }

    }
}
