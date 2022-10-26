package de.marcus.javagame.datahandling.data;

import com.badlogic.gdx.math.Vector2;
import de.marcus.javagame.entities.NPC;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.handlers.DialogHandler;


import java.util.List;

public class NPCs {
    List<NPC> npcList;

    public NPCs(List<Vector2> coordList, UI ui) {
        int i = 0;
        for (DialogHandler.Dialogs dialog : DialogHandler.Dialogs.values()) {
            new NPC(coordList.get(i).x,coordList.get(i).y,dialog,null,ui);
            i++;
        }
    }
}
