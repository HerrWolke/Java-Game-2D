package de.marcus.javagame.datahandling.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.marcus.javagame.datahandling.Loadable;
import de.marcus.javagame.handlers.DialogHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DialogCompletionData extends Loadable {
    ArrayList<DialogHandler.Dialogs> completedDialogs;

    @JsonIgnore
    public void completeDialog(DialogHandler.Dialogs dialog) {
        if (!completedDialogs.contains(dialog))
            completedDialogs.add(dialog);
    }

    public DialogCompletionData() {
        this.completedDialogs = new ArrayList<>();
    }
}
