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
        currentDialog = Dialogs.WEAPON_SHOP_DIALOG.dialog;
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


    /**
     * How to build a dialog
     * <br><br>
     *
     * <p>1. Copy the {@link DialogHandler.Dialogs#DIALOG_DEFAULT}</p><br>
     * <p>2. What variables? <br>
     *      <p style="text-indent:40px">2.1. DialogTitle is the text which is to be displayed in top left of dialog box.<b style="color:orange">!!! ONLY NEEDS TO BE SET FOR TOP DIALOG!!!</b></p>
     *      <p style="text-indent:40px">2.2 DialogText is the stuff the NPC talks to you about</p>
     *      <p style="text-indent:40px">  2.3 ButtonTexts: (Optional) This only has to be called, if you want to have
     *      buttons on that dialog. If you just want some text (for example if its the last dialog or a monologue) then no buttons are needed so dont call the
     *          .setButtonTexts();</p>
     *      <p style="text-indent:40px"> 2.4 NextDialogs: (Optional if Button Texts is not set) This only has to be called when ButtonTexts are set. The first button text will lead to the first follow
     *      up dialog, the second text to the second dialog specified etc.  <b style="color:red">THERE CAN NEVER BE MORE THAN THREE!!!!</b></p>
     * <br>
     * 3. Structure
     *     <p style="text-indent:40px"> 3.1 The following structure always has to exist:
     *      new DialogBuilder()
     *      .setDialogTitle("This is the title")
     *      .setDialogText("This is some text")
     *      .markAsTop()
     *      .createDialog()
     *      </p>
     *
     *  This is the base structure. This will create a dialog window with a title, a text but no buttons.
     *  When the text is done displaying, you will be told you can now close the dialog. That's it.
     *
     *  <br>
     *
     * <p>
     *     <br>
     * 4. Events
     *<br>
     * Available Events:<br>
     * GiftItem, OpenShop, GiftMoney<br><br>
     * For all you have to call a second event<br>
     * This should describe the item using the names from {@link de.marcus.javagame.datahandling.data.inventory.InventoryItem} or the Shop from {@link de.marcus.javagame.datahandling.data.shop.Shops}
     * or the amount of money (int!)
     * <br>
     * Events are called by writing {EVENT=EVENTNAME} in the text
     * <br>
     * Example: "Here you go.{WAIT=1} {EVENT=GiftItem} {EVENT=HEAL_POTION}"
     *  </p>
     *
     */
    public enum Dialogs {
        DIALOG_DEFAULT(
                new DialogBuilder().
                        setDialogTitle("NPC1").
                        setDialogText("Ich bin ein tolles NPC und ich esse Katzen").
                        setButtonTexts("Text2", "test3", "test4").
                        setNextDialogs(
                                new DialogBuilder().
                                        setDialogText("Cat text because why not?").
                                        createDialog(),
                                new
                                        DialogBuilder().
                                        setDialogText("Cat text because why not?2").
                                        createDialog(),
                                new
                                        DialogBuilder().
                                        setDialogText("Cat text because why not?3").
                                        createDialog()

                        )
                        .markAsTop()
                        .createDialog()
        ),
        WEAPON_SHOP_DIALOG(
                new DialogBuilder()
                        .setDialogTitle("Waffenhändler")
                        .setDialogText("Oh, neue Kundschaft. Wie großartig! Was kann ich für dich tun?")
                        .setButtonTexts("Ich schaue mich erstmal um", "Ich bräuchte Hilfe, um Ausrüstung zusammenzustellen", "Ich sehe hier nicht interessantes. Auf Wiedersehen!")
                        .setNextDialogs(
                                new DialogBuilder().
                                        setDialogText("Lass mich wissen, wenn du Hilfe brauchst! {EVENT=OpenShop}").
                                        createDialog(),
                                new DialogBuilder().
                                        setDialogText("Gerne! Wofür brauchst du denn Ausrüstung?")
                                        .setButtonTexts("Das geht sie nichts an! ", "Ich möchte in die Fußstapfen meines Vaters treten.", "Ich muss mich verteidigen können.")
                                        .setNextDialogs(
                                                new DialogBuilder().
                                                        setDialogText("Entschuldigung, ich wollte dir nicht zu nahe treten … \n Mit einem Schwert kann man nie was falsch machen.{WAIT=1}").
                                                        createDialog(),
                                                new DialogBuilder().
                                                        setDialogText("Viel Glück dabei! Ich denke dieses Schwert könnte dir sicherlich dabei helfen.{WAIT=1} {EVENT=GiftSword}").
                                                        createDialog(),
                                                new DialogBuilder().
                                                        setDialogText("Angriff ist die beste Verteidigung, daher würde ich dir das Schwert aus dem Shop empfehlen.{WAIT=1}{EVENT=OpenShop}").
                                                        createDialog()
                                        ).
                                        createDialog(),
                                new DialogBuilder().
                                        setDialogText("Schönen Tag noch, Auf Wiedersehen!").
                                        createDialog()
                        )
                        .markAsTop()
                        .createDialog()


        );

        Dialogs(Dialog dialog) {
            this.dialog = dialog;
        }

        private Dialog dialog;
    }
}
