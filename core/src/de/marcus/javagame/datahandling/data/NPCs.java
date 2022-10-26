package de.marcus.javagame.datahandling.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
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

    public NPCs(List<Vector2> coordList, UI ui, World world) {
        npcList = new ArrayList<>();
        System.out.println(" This world " + world);
        int i = 0;
        for (DialogHandler.Dialogs dialog : DialogHandler.Dialogs.values()) {
            if(coordList.size() > i) {
                NPC npc = new NPC(coordList.get(i).x, coordList.get(i).y, dialog, new Texture("npc.png"), ui, world);
                npcList.add(npc);
                i++;
            } else {
                break;
            }
        }
    }
}
