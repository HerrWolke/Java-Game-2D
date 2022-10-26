package de.marcus.javagame.datahandling.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.marcus.javagame.entities.NPC;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.handlers.DialogHandler;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NPCs {
    List<NPC> npcList;

    public NPCs(List<Vector2> coordList, UI ui) {
        npcList = new ArrayList<>();
        int i = 0;
        for (DialogHandler.Dialogs dialog : DialogHandler.Dialogs.values()) {
            if(coordList.size() > i) {
                NPC npc = new NPC(coordList.get(i).x, coordList.get(i).y, dialog, new Texture("npc.png"), ui);
                npcList.add(npc);
                i++;
            } else {
                break;
            }
        }
    }
}
