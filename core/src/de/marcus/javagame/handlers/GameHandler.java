package de.marcus.javagame.handlers;

import de.marcus.javagame.game.Location;

public class GameHandler {
    enum MAINSTORY {

    }

    enum SIDEQUESTS {

    }

    int missionLevel;
    int sideQuests;

    //methoden für jedes level
    //kriterien für level definieren
    //guide methode
    public void start() {
        buildStartArea();
        setStartMission();
        setSideQuestsStart();
        //Dialog screen + Introductiona
        //character setzen
    }

    public void buildStartArea() {

    }

    public void setStartMission() {

    }

    public void setSideQuestsStart() {

    }

    public void guide(Location location) {

    }

    public boolean missionPassed() {
        return false;
    }

    public void sideQuestPassed() {

    }


}
