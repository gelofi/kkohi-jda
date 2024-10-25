package org.java.kkohi.commands.tools;

import net.dv8tion.jda.api.entities.Message;
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

public class Purge implements ICommand {
    private static final Logger log = LoggerFactory.getLogger(Purge.class);

    @Override
    public String getName() { return "purge"; }

    @Override
    public String getDescription() { return "Deletes a number of messages (or messages from a specified user) in the current channel."; }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER, "count", "Number of messages to purge/delete in this channel. Maximum of 100 messages.", true));
        data.add(new OptionData(OptionType.USER, "user", "Delete messages in this channel from this user.", false));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            OptionMapping messages = event.getOption("count"), user = event.getOption("user");
            assert messages != null;

            // (Channel-bound) User-specific Purge
            if (user != null){
                event.getChannel().getIterableHistory().takeAsync(messages.getAsInt()).thenAccept(message -> {
                    int count = 0;
                    for (Message msg : message) {
                        if (msg.getAuthor().getId().equals(user.getAsUser().getId())) {
                            msg.delete().queue();
                            count++;
                        }
                    }
                    // Confirm once messages are purged.
                    if (count == messages.getAsInt()) {
                        event.reply(":white_check_mark: Cleaned up " + messages.getAsString() + " messages from " + Objects.requireNonNull(user.getAsMember()).getUser().getName() + ".").setEphemeral(true).queue();
                    }
                });
            // (Channel-bound) Non-specific Purge
            } else {
                event.getChannel().getIterableHistory().takeAsync(messages.getAsInt()).thenAccept(msgs -> {
                    int count = 0;
                    for (Message msg : msgs) {
                        msg.delete().queue();
                        count++;
                    }
                    // Confirm once messages are purged.
                    if (count == messages.getAsInt()){
                        event.reply(":white_check_mark: Cleaned up " + messages.getAsString() + " messages.").setEphemeral(true).queue();
                    }
                });
            }
        } catch (Exception e){
            log.error("e: ", e);
            event.reply("An error occured processing the purge! Please try again.").setEphemeral(true).queue();
        }
    }
}
