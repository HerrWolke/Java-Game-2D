package de.marcus.javagame.datahandling.data.dialog;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Dialog {

    private String dialogTitle;
    private String dialogText;
    private List<String> buttonTexts;
    private List<Dialog> nextDialogs;
    private String dialogTextOnceFinished;
    private boolean disableOnOnceFinished;

    /**
     * @param disableOnOnceFinished Describes if the dialog is not to be displayed anymore once the dialog was finished once
     */
    public Dialog(String title, String dialogText, List<String> buttonTexts, List<Dialog> nextDialogs, boolean topDialog, boolean disableOnOnceFinished, String dialogTextOnceFinished) {
        this.dialogTitle = Objects.requireNonNullElse(title, "");
        this.dialogTextOnceFinished = Objects.requireNonNullElse(dialogTextOnceFinished, "");
        this.dialogText = Objects.requireNonNullElse(dialogText, "");
        this.buttonTexts = Objects.requireNonNullElse(buttonTexts, Arrays.asList("", "", ""));
        this.nextDialogs = Objects.requireNonNullElse(nextDialogs, new ArrayList<>());
        this.disableOnOnceFinished = disableOnOnceFinished;
        if (topDialog) {
            overwriteTitles(this);
        }
    }

    public Dialog getNextDialog(int dialog) {
        return nextDialogs.get(dialog);
    }

    public void overwriteTitles(Dialog dialog) {
        List<Dialog> nextDials = dialog.getNextDialogs();
        if (nextDials != null) {
            for (Dialog dialogInLoop : nextDials) {
                if (dialogInLoop.getDialogTitle().equalsIgnoreCase("") || dialogInLoop.getDialogTitle() == null) {
                    dialogInLoop.setDialogTitle(dialogTitle);
                    overwriteTitles(dialogInLoop);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "dialogTitle='" + dialogTitle + '\'' +
                ", dialogText='" + dialogText + '\'' +
                ", nextDialogs=" + nextDialogs.size() +
                "}  \n";
    }
}
