package de.marcus.javagame.framework.questsystem;

import de.marcus.javagame.framework.questsystem.Quest;

import java.util.LinkedHashMap;

//import static de.marcus.javagame.screens.LoadingScreen.loggingSystem;


public class QuestingManager {
    private LinkedHashMap<Quest.QuestType, Quest> activeQuests;

    public QuestingManager() {
        activeQuests = new LinkedHashMap<>();
    }

    public void setActiveQuest(Quest.QuestType type, Quest questActivate) {
        Quest quest = activeQuests.get(type);
        if (quest == null) {
            activeQuests.replace(type, questActivate);
        } else {
            //loggingSystem.getLogger().log(Logger.LoggerLevel.ERROR, "Tried to set a quest, but the quest " + quest.name() + " is currently active. The quest to be set was " + questActivate.name());
        }
    }


}
