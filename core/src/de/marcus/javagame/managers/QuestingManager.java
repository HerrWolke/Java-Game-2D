package de.marcus.javagame.managers;

import de.marcus.javagame.entities.logging.Logger;
import de.marcus.javagame.questing.Quest;

import java.util.LinkedHashMap;

import static de.marcus.javagame.screens.LoadingScreen.loggingSystem;


public class QuestingManager {
    private LinkedHashMap<Quest.QuestType, Quest> activeQuests;

    public QuestingManager() {
        activeQuests = new LinkedHashMap<>();
    }

    public void setActiveQuest(Quest.QuestType type, Quest activeMainQuest) {
        Quest quest = activeQuests.get(type);
        if (quest == null) {
            activeQuests.replace(type, activeMainQuest);
        } else {
            loggingSystem.getLogger().log(Logger.LoggerLevel.ERROR, "Tried to set a quest, but the quest " + quest.name() + " is currently active. The quest to be set was " + activeMainQuest.name());
        }
    }


}
