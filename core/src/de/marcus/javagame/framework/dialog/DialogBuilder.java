package de.marcus.javagame.framework.dialog;

import java.util.Arrays;
import java.util.List;

public class DialogBuilder {
    private String dialogText;
    private List<String> buttonTexts;
    private List<Dialog> nextDialogs;
    private String dialogTitel;
    private String dialogTextOnceFinished;

    private boolean topDialog;

    private boolean disableOnceFinishedOnce = true;

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


    public DialogBuilder setAsDefaultDialog() {
        this.disableOnceFinishedOnce = false;
        return this;
    }

    public DialogBuilder setDialogTextOnceFinished(String dialogTextOnceFinished) {
        this.dialogTextOnceFinished = dialogTextOnceFinished;
        return this;
    }

    public Dialog createDialog() {
        return new Dialog(dialogTitel, dialogText, buttonTexts, nextDialogs, topDialog, disableOnceFinishedOnce,dialogTextOnceFinished);
    }


    public DialogBuilder markAsTop() {
        this.topDialog = true;
        this.disableOnceFinishedOnce = false;
        return this;
    }
}