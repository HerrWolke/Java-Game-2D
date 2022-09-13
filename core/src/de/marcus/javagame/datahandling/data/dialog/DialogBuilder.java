package de.marcus.javagame.datahandling.data.dialog;

import java.util.Arrays;
import java.util.List;

public class DialogBuilder {
    private String dialogText;
    private List<String> buttonTexts;
    private List<Dialog> nextDialogs;
    private String dialogTitel;

    public DialogBuilder setDialogText(String dialogText) {
        this.dialogText = dialogText;
        return this;
    }

    public DialogBuilder setDialogTitle(String dialogTitel) {
        this.dialogTitel = dialogTitel;
        return this;
    }

    public DialogBuilder setButtonTexts(String... buttonTexts) {
        this.buttonTexts = Arrays.asList(buttonTexts);
        return this;
    }

    public DialogBuilder setNextDialogs(Dialog... dialogs) {
        this.nextDialogs = Arrays.asList(dialogs);
        return this;
    }

    public Dialog createDialog() {
        return new Dialog(dialogTitel, dialogText, buttonTexts, nextDialogs);
    }
}