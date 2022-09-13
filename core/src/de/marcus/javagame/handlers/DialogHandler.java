package de.marcus.javagame.handlers;

import de.marcus.javagame.datahandling.data.dialog.Dialog;
import de.marcus.javagame.datahandling.data.dialog.DialogBuilder;
import de.marcus.javagame.graphics.ui.windows.DialogWindow;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DialogHandler {
    private boolean isDialogActive;
    private Dialog currentDialog;

    private DialogWindow window;

    public DialogHandler(DialogWindow window) {
        isDialogActive = false;
        currentDialog = Dialogs.TEST_DIALOG.dialog;
        this.window = window;
    }

    public Dialog dialogButtonPressed(int buttonNumber) {
        if (buttonNumber < currentDialog.getNextDialogs().size()) {
            Dialog nextDialog = currentDialog.getNextDialog(buttonNumber);
            this.currentDialog = nextDialog;
            return nextDialog;
        } else {
            return null;
        }
    }

    public void setCurrentDialog(Dialogs currentDialog) {
        isDialogActive = true;
        this.currentDialog = currentDialog.dialog;
        window.setVisible(true);
        window.setDialogMenuOptions(currentDialog.dialog);

    }

    public boolean isDialogFinished() {
        this.isDialogActive = false;
        return currentDialog.getNextDialogs().isEmpty();
    }


    public enum Dialogs {
        TEST_DIALOG(
                new DialogBuilder()
                        .setDialogTitle("NPC1")
                        .setDialogText("Ich bin ein tolles NPC und ich esse Katzen")
                        .setButtonTexts("Text2", "test3", "test4")
                        .setNextDialogs(
                                new DialogBuilder().
                                        setDialogText("Cat text because why not?").
                                        setDialogTitle("Title").
                                        createDialog(),
                                new DialogBuilder().
                                        setDialogText("Cat text because why not?2").
                                        setDialogTitle("Title").
                                        createDialog(),
                                new DialogBuilder().
                                        setDialogText("Cat text because why not?3").
                                        setDialogTitle("Title").
                                        createDialog()
                        )
                        .createDialog()
        );

        Dialogs(Dialog dialog) {
            this.dialog = dialog;
        }

        private Dialog dialog;
    }
}
