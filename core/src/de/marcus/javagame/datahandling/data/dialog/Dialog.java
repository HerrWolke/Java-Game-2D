package de.marcus.javagame.datahandling.data.dialog;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Dialog {

    private String dialogTitle;
    private String dialogText;
    private List<String> buttonTexts;
    private List<Dialog> nextDialogs;

    public Dialog(String title, String dialogText, List<String> buttonTexts, List<Dialog> nextDialogs) {
        this.dialogTitle = Objects.requireNonNullElse(title, "");
        this.dialogText = Objects.requireNonNullElse(dialogText, "");
        ;
        this.buttonTexts = Objects.requireNonNullElse(buttonTexts, Arrays.asList("", "", ""));
        ;
        this.nextDialogs = Objects.requireNonNullElse(nextDialogs, new ArrayList<>());
        ;

    }

    public Dialog getNextDialog(int dialog) {
        return nextDialogs.get(dialog);
    }


}
