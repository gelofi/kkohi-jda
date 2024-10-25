package org.java.kkohi.commands.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;
import org.java.kkohi.Keys;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Help implements ICommand {

    @Override
    public String getName() { return "help"; }

    @Override
    public String getDescription() { return "Learn how to use Kkohi in your server."; }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    public static String fileFinder(String type) {
        ArrayList<String> array = new ArrayList<>();
        File folder = new File("src/main/java/org/java/kkohi/commands/" + type);
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (!file.isDirectory()) {
                array.add(file.getName().toLowerCase().replace(".java", ""));
            } else {
                fileFinder(type);
            }
        }
        return "`" + String.join("` `", array) + "`";
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        try {
            String guide = """
                # Guide to use [Kkohi](https://github.com/gelofi/kkohi-jda)
                Kkohi is your coffee-oriented bot for all-around server work!
                Type `/` to begin using commands.
                To view the source-code, click Kkohi.""";

            EmbedBuilder builder = new EmbedBuilder()
                    .setAuthor("Kkohi " + Keys.VERSION, "https://github.com/gelofi/kkohi-jda", event.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.decode("#F2CDA6"))
                    .setDescription(guide)
                    .addField("Tools", fileFinder("tools"), false)
                    .addField("Fun", fileFinder("fun"), false)
                    .setFooter("© Gelofi, 2021", Objects.requireNonNull(event.getJDA().getUserById(583328224491208863L)).getAvatarUrl());
            event.replyEmbeds(builder.build()).queue();
        } catch (Exception e) {
            event.reply("An error occured. Run the command again!").setEphemeral(true).queue();
            throw new RuntimeException(e);
        }

    }
}
