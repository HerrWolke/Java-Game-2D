package de.marcus.javagame.entities;



import de.marcus.javagame.datahandling.data.dialog.Dialog;

import java.util.List;

public class DialogData {
    public String title;
    public String dialogText;
    public java.util.List<String> buttonTexts;public List<Dialog> nextDialogs;
    public boolean topDialog;
    public boolean disableOnOnceFinished;
    public String dialogTextOnceFinished;
    public void setTitle(String title) {
        this.title = title;
    }
    public void setNextDialogs(List<Dialog> l){
        this.nextDialogs = l;
    }
    public void setDialogText(String dialogText) {
        this.dialogText = dialogText;
    }
    public void setButtonTexts(List<String> bText){
        this.buttonTexts = bText;

    }
    public void setTopDialog(boolean topDialog)
    {
        this.topDialog = topDialog;
    }
  public void setDisableOnOnceFinished(boolean d){
        this.disableOnOnceFinished = d;
    }
    public void setDialogTextOnceFinished(String dialogText){
        this.dialogTextOnceFinished = dialogText;
    }
}
