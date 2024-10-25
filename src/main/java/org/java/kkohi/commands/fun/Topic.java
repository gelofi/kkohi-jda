package org.java.kkohi.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.java.kkohi.ICommand;

import java.util.List;

public class Topic implements ICommand {

    @Override
    public String getName() {
        return "topic";
    }

    @Override
    public String getDescription() {
        return "If you ran out of topics to talk about, let Kkohi ask a random question.";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        String[] topics = {"What was the last funny video you saw?",
                "What is something that is popular now that annoys you?",
                "What do you do when you hang out with your friends?",
                "If you could have any animal as a pet, what animal would you choose?",
                "If you opened a business, what kind of business would it be?",
                "What is the strangest dream you have ever had?",
                "How much time do you spend on the internet? What do you usually do?",
                "If you had to change your name, what would your new name be?",
                "What is your guilty pleasure?",
                "How often do you curse?",
                "Is human nature constant or is it molded by culture? Can human nature be completely changed by culture or society?",
                "What aspects of humans have made us a successful species?",
                "Should governments make laws to protect people from hurting themselves?",
                "What is the most uplifting thing happening in the world right now?",
                "How important is freedom of the press to a healthy society?",
                "What is the purpose of a human life?",
                "What does it mean to die well?",
                "Are emotions necessary for human survival? Why or why not?",
                "What is the most beneficial emotion? How about the most destructive?",
                "Are some lives more valuable than others?",
                "If sacrificing your own life would save the lives of a specific number of strangers, how many strangers would need to be saved for you to sacrifice your own life? What if the people were friends? How about family?",
                "What’s something terrifying that we’ve all come to accept as a fact of life?",
                "What makes people believe absurd conspiracy theories?",
                "What is the best way to explore human nature: psychology, philosophy, or biology?",
                "If you had to sum up the whole human species in 3 words, what would those words be?",
                "How would you react if there was irrefutable proof that God doesn’t exist? How about if there was irrefutable proof that God does exist?",
                "Do people have an obligation to help others or should people be responsible for helping themselves?",
                "What is the purpose of art in society?",
                "Do you think you are being controlled by something supernatural?",
                "Do you fear death?",
                "What is the silliest fear you have?",
                "What are you best at?",
                "What weird or useless talent do you have?",
                "In your opinion, what’s the closest thing to real magic?",
                "How do you think you will die?",
                "What social stigma does society need to get over?",
                "When did something start out badly for you but in the end, it was great?",
                "Should full access to the internet be a fundamental human right?",
                "What’s the most ridiculous fact you know?",
                "If you were a king / queen, what would your throne look like?",
                "What would you want your last meal to be if you were on death row?",
                "What does your own personal hell look like? How about your own personal heaven?",
                "If you could call up anyone in the world and have a one hour conversation, who would you call?",
                "Time freezes for everyone but you for one day. What do you do?",
                "What’s one thing your learned this week?"};

        String topicMsg;

        // Pick a random String
        topicMsg = topics[(int) Math.round(Math.random() * (topics.length - 1))];
        event.reply(topicMsg).queue();
    }
}
