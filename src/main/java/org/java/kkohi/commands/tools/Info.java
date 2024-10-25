package org.java.kkohi.commands.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;

import java.awt.Color;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Info implements ICommand {
    @Override
    public String getName() { return "info"; }

    @Override
    public String getDescription() { return "Provides information on a specified user."; }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();

        // Server Info
        data.add(new OptionData(OptionType.STRING, "type", "What information must be fetched?", true)
                .addChoice("server", "server")
                .addChoice("role", "role")
                .addChoice("channel", "channel")
                .addChoice("user", "user"));

        // Role Info
        data.add(new OptionData(OptionType.ROLE, "role", "Pick a role.", false));

        // Channel Info
        data.add(new OptionData(OptionType.CHANNEL, "channel", "Pick a channel.", false));

        // User Info
        data.add(new OptionData(OptionType.USER, "user", "Pick a user.", false));
        return data;
    }

    public static String hexColor(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            String type = event.getOption("type").getAsString(); // Retrieve the selected type

            EmbedBuilder infoEmbed = new EmbedBuilder().setColor(Color.decode("#F2CDA6"));

            switch (type) {
                // Server
                case "server":
                    Guild server = event.getGuild();
                    if (server != null) {
                        infoEmbed.setTitle(server.getName() + " Information")
                                .setThumbnail(server.getIconUrl())
                                .addField("Owner", Objects.requireNonNull(server.getOwner()).getEffectiveName(), true)
                                .addField("Members", String.valueOf(server.getMemberCount()), true)
                                .addField("ID", server.getId(), true)
                                .addField("Roles", String.valueOf(server.getRoles().size()), true)
                                .addField("Channels", String.valueOf(server.getChannels().size()), true)
                                .addField("Voice Channels", String.valueOf(server.getVoiceChannels().size()), true)
                                .addField("AFK VC", server.getAfkChannel() == null ? "No AFK Channel" : server.getAfkChannel().getName(), true)
                                .addField("Created on", server.getTimeCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), true);
                    } else {
                        event.reply("Unable to fetch server information.").setEphemeral(true).queue();
                        return;
                    }
                    break;

                // Channel
                case "channel":
                    OptionMapping channelOption = event.getOption("channel");
                    if (channelOption != null) {
                        GuildChannel channel = channelOption.getAsChannel();
                        infoEmbed.setTitle("Channel Information")
                                .addField("Channel Name", channel.getName(), true)
                                .addField("Channel ID", channel.getId(), true)
                                .addField("Channel Type", channel.getType().name(), true);
                    } else {
                        event.reply("Please select a channel for more information.").setEphemeral(true).queue();
                        return;
                    }
                    break;

                // Role
                case "role":
                    OptionMapping roleOption = event.getOption("role");
                    if (roleOption != null) {
                        Role role = roleOption.getAsRole();
                        infoEmbed.setTitle("Role Information")
                                .addField("Role Name", role.getName(), true)
                                .addField("Role ID", role.getId(), true)
                                .addField("Member Count", String.valueOf(role.getGuild().getMembersWithRoles(role).size()), true)
                                .setColor(role.getColor() != null ? role.getColor() : Color.decode("#F2CDA6"));
                    } else {
                        event.reply("Please select a role for more information.").setEphemeral(true).queue();
                        return;
                    }
                    break;

                // User
                case "user":
                    OptionMapping userOption = event.getOption("user");
                    User user = userOption.getAsUser();

                    // Formatted Hexadecimal Color Code
                    String color = hexColor(Objects.requireNonNull(userOption.getAsMember().getRoles().getFirst().getColor()));

                    // Formatted Time
                    String creationDate = user.getTimeCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String joinDate = userOption.getAsMember().getTimeJoined().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    if (userOption != null) {
                        infoEmbed.setTitle(user.getEffectiveName() + "'s Information").setThumbnail(user.getAvatarUrl())
                                .addField("Tag", user.getAsTag().replace("#0000", ""), true)
                                .addField("Nickname", userOption.getAsMember().getNickname() != null ? userOption.getAsMember().getNickname() : "None", true)
                                .addField("ID", user.getId(), true)
                                .addField("Avatar URL", ":link: [Link here](" +Objects.requireNonNull(user.getAvatarUrl() + ")"), true)
                                .addField("Color", color, true)
                                .addField("Account Creation", String.valueOf(creationDate), true)
                                .addField("Joined server on", String.valueOf(joinDate), true)
                                .addField("Bot", user.isBot() ? "Yes" : "No", true);
                    } else {
                        event.reply("Please select a user for more information.").setEphemeral(true).queue();
                        return;
                    }
                    break;

                // Error
                default:
                    event.reply("Invalid input. Please choose from `server`, `role`, `channel`, or `user`, with their corresponding values!.").setEphemeral(true).queue();
                    return;
            }

            event.replyEmbeds(infoEmbed.build()).queue();
        } catch (Exception e) {
            event.reply("An error occured while fetching information. Did you provide valid input?").setEphemeral(true).queue();
            throw new RuntimeException(e);
        }

    }
}
