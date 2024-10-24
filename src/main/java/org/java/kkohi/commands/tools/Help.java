package org.java.kkohi.commands.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;
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

        String guide = """
                # Guide to use Kkohi
                Kkohi is your coffee-oriented bot for all-around server work!
                Type `/` to begin using commands.""";

        EmbedBuilder builder = new EmbedBuilder()
                .setAuthor("Kkohi 1.2.0", null, event.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.decode("#F2CDA6"))
                .setDescription(guide)
                .addField("Tool Commands", fileFinder("tools"), false)
                .addField("Fun Commands", fileFinder("fun"), false)
                .setFooter("© Gelofi, 2021");
        event.replyEmbeds(builder.build()).queue();
    }
}
