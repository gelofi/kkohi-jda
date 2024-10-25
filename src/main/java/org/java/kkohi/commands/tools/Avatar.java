package org.java.kkohi.commands.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Avatar implements ICommand {

    @Override
    public String getName() { return "avatar"; }

    @Override
    public String getDescription() { return "Displays a user's profile photo."; }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.USER, "user", "Which user's profile photo will you view?", true));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            OptionMapping user = event.getOption("user");
            assert user != null;

            // The mentioned
            String avatar = Objects.requireNonNull(user.getAsUser()).getAvatarUrl();
            String name = user.getAsUser().getEffectiveName();

            // Build Embed
            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle(name)
                    .setImage(avatar)
                    .setColor(Color.decode(String.valueOf(Objects.requireNonNull(user.getAsMember()).getColorRaw())));

            event.replyEmbeds(builder.build()).queue();
        } catch (Exception e) {
            event.reply("An error occured while fetching their photo! Please try again.").setEphemeral(true).queue();
            throw new RuntimeException(e);
        }

    }
}
