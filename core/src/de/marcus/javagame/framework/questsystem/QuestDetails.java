package de.marcus.javagame.framework.questsystem;

import de.marcus.javagame.framework.logging.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.marcus.javagame.ui.screens.GameScreen.loggingSystem;

//import static de.marcus.javagame.screens.LoadingScreen.loggingSystem;

public class QuestDetails {
    private final int maxStage;
    private int currentStage;
    private int completionProcess;
    private List<String> stageTexts;
    private String currentStageText;


    /**
     * @param maxStage   Starts at one not at zero!
     * @param stageTexts The text to display what the quest is about. Has to match the maxStage in amount
     */
    public QuestDetails(int maxStage, String... stageTexts) {
        this.maxStage = maxStage;
        this.stageTexts = Arrays.stream(stageTexts).collect(Collectors.toList());
        currentStage = 1;
        currentStageText = stageTexts[0];
        if (maxStage > stageTexts.length || maxStage < stageTexts.length) {
            loggingSystem.getLogger().log(Logger.LoggerLevel.INFO, "Questing Error. A quest was initialised with less stage info than stages. Max stages: " + maxStage + ", provided Info: " + stageTexts.length);
            System.exit(0);
        }

    }

    /**
     * @param percentageToProgress How much to progess (in percent) in this stage of the quest.
     *                             For Example if you have 5 collectibles to collect, each would be 20
     * @return Returns if the quest is finished
     */
    public boolean progressCurrentStage(int percentageToProgress) {
        if ((completionProcess + percentageToProgress) >= 100) {
            completionProcess = 100;
            return progressStage();
        } else {
            completionProcess += percentageToProgress;
            return false;
        }
    }

    /**
     * @return Returns if the quest is finished.
     */
    private boolean progressStage() {
        if ((currentStage + 1) >= maxStage) {
            return true;
        } else {
            currentStage++;
            currentStageText = stageTexts.get(currentStage);
            return false;
        }
    }

    public String getCurrentStageText() {
        return currentStageText;
    }
}
