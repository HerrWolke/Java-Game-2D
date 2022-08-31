package de.marcus.javagame.questing;

/**
 * All Quests have to have the same number of stageTexts as the maxStage
 */
public enum Quest {
    //Main Quests
    TAKAYAMA_VILLAGE
            (QuestType.MAIN, new QuestDetails(3, "Suche den Kartographen des Dorfes auf", "Hole die Kopie der Abenteurerkarte deines Vaters ab", "Tritt deine Reise an")),
    WATER_TEMPLE
            (QuestType.MAIN, new QuestDetails(0, "")),
    ICE_CITY
            (QuestType.MAIN, new QuestDetails(0, "")),
    JUNGLE
            (QuestType.MAIN, new QuestDetails(0, "")),
    MOUNTAINS
            (QuestType.MAIN, new QuestDetails(0, "")),
    DESSERT
            (QuestType.MAIN, new QuestDetails(0, "")),


    //Side Quests
    EXAMPLE_QUEST
            (QuestType.SIDE, new QuestDetails(0, ""));

    Quest(QuestType type, QuestDetails details) {
        this.type = type;
        this.details = details;
    }

    private QuestType type;
    private QuestDetails details;

    public enum QuestType {
        MAIN, SIDE
    }
}


