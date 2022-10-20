package de.marcus.javagame.handlers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.marcus.javagame.datahandling.data.DialogCompletionData;
import de.marcus.javagame.datahandling.data.datahandling.SavedataHandler;
import de.marcus.javagame.datahandling.data.dialog.Dialog;
import de.marcus.javagame.datahandling.data.dialog.DialogBuilder;
import de.marcus.javagame.graphics.ui.windows.DialogWindow;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DialogHandler {

    @JsonIgnore
    private Dialogs currentDialogType;

    @JsonIgnore
    private boolean isDialogActive;
    @JsonIgnore
    private Dialog currentDialog;

    @JsonIgnore
    private DialogWindow window;

    private DialogCompletionData dialogCompletionData;

    public DialogHandler(DialogWindow window) {
        isDialogActive = false;
        currentDialog = Dialogs.WEAPON_SHOP_DIALOG.dialog;
        this.window = window;
        dialogCompletionData = SavedataHandler.load(DialogCompletionData.class);
        dialogCompletionData.completeDialog(Dialogs.DIALOG_DEFAULT);
        dialogCompletionData.completeDialog(Dialogs.WEAPON_SHOP_DIALOG);
        SavedataHandler.save(dialogCompletionData);

    }

    public boolean hasDialogBeenCompletedBefore(Dialogs dialog) {
        return dialogCompletionData.getCompletedDialogs().contains(dialog);
    }

    public boolean hasCurrentDialogBeenCompletedBefore() {
        return dialogCompletionData.getCompletedDialogs().contains(currentDialogType);
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
        this.currentDialogType = currentDialog;
        window.setVisible(true);
        window.setDialogMenuOptions(currentDialog.dialog);
    }

    public boolean isDialogFinished() {
        boolean empty = currentDialog.getNextDialogs().isEmpty();
        System.out.println("This dialog is now " + empty);
        this.isDialogActive = !empty;
        if (!isDialogActive) {

            System.out.println("Overwriting dialog");
        }
        return empty;
    }


    /**
     * Wie erstellt man einen Dialog?
     * <br><br>
     *
     * <p>1. Copy the {@link DialogHandler.Dialogs#DIALOG_DEFAULT}</p><br>
     * <p>2. What variables? <br>
     * <p style="text-indent:40px">2.1. DialogTitle der oben links im Dialog angezeigt werden.<b style="color:orange">!!! Muss nur für die Klasse gesetzt werden, bei der .markAsTop aufgerufen würde(oberste)!!!</b></p>
     * <p style="text-indent:40px">2.2 DialogText der Text über den der NPC redet</p>
     * <p style="text-indent:40px">  2.3 ButtonTexts: (Optional) Diese Methode muss nur aufgerufen werden, wenn du Buttons haben willst in diesem Dialog.
     * Wenn du nur Text willst (z.B. wenn es der letzte Dialog der Reihe ist oder ein Monolog) dann brauchst du die Methode
     * .setButtonTexts(); nicht aufzurufen</p>
     * <p style="text-indent:40px"> 2.4 NextDialogs: (Optional: Nur wenn Buttons Texts gesetzt wurden). Der erste Button Text führt zum ersten Dialog
     * der zweite zum zweiten etc. <b style="color:red">ES KANN NIE MEHR ALS 3 GEBEN!!!!!</b></p>
     * <br>
     * 3. Structure
     * <p style="text-indent:40px"> 3.1 Die folgende Struktur muss immer existieren:
     * new DialogBuilder()
     * .setDialogTitle("This is the title")
     * .setDialogText("This is some text")
     * .markAsTop()
     * .createDialog()
     * </p>
     * <p>
     * Das ist die Basisstruktur. Dies wird einen Dialog erzeugen, welcher nur Text, einen Titel aber keine Überschrift hat.
     * Wenn der Dialog fertig erzählt ist, wird dir gesagt, du kannst Enter drücken, um den Dialog zu schließen
     *
     * <br>
     *
     * <p>
     * <br>
     * 4. Events
     * <br>
     * Available Events:<br>
     * GiftItem, OpenShop, GiftMoney, DialogFinished<br><br>
     * For all you have to call a second event<br>
     * <p>
     * This should describe the item using the names from {@link de.marcus.javagame.datahandling.data.inventory.InventoryItem} or the Shop from {@link de.marcus.javagame.datahandling.data.shop.Shops}
     * or the amount of money (int!) or for DialogFinished the name of the dialog
     * <br>
     * Events are called by writing {EVENT=EVENTNAME} in the text
     * <br>
     * Example: "Here you go.{WAIT=1} {EVENT=GiftItem} {EVENT=HEAL_POTION}"
     *
     * </p>
     */
    public enum Dialogs {
        TEST_DIALOG
                (
                        new DialogBuilder().
                                setDialogTitle("Fortnite").
                                setDialogText("Ich liebe Fortnite").
                                setButtonTexts("ich auch", "Halts maul", "Für fortnite").
                                setNextDialogs(new DialogBuilder().
                                        setDialogText("").
                                        createDialog()).markAsTop().createDialog()),

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
                                        setAsDefaultDialog().
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
                                                        setDialogText("Angriff ist die beste Verteidigung, daher würde ich dir das Schwert aus dem Shop empfehlen.{WAIT=1}{EVENT=OpenShop}{EVENT=DialogFinished}").
                                                        createDialog()
                                        ).
                                        createDialog(),
                                new DialogBuilder().
                                        setDialogText("Schönen Tag noch, Auf Wiedersehen!").
                                        setAsDefaultDialog().
                                        createDialog()
                        )
                        .markAsTop()
                        .createDialog()


        ), POTION_SHOP_DIALOG(new DialogBuilder().
                setDialogTitle("Torben - Tränke").
                setDialogText("Guten Tag Der Herr, was kann ich für sie tun?").
                setButtonTexts("Guten Tag! Ich benötige Beratung bei den Tränken.", "Guten Tag! Ich möchte mich erstmals ein wenig umschauen. ", "Guten Tag! wie viel kosten die Tränke jeweils?  ").
                setNextDialogs(
                        new DialogBuilder().
                                setDialogText("Das kommt auf die Situation an in der sie sich befinden. \n Manchmal muss man sich schnell regenerieren, ein andermal sollte man besser schneller sein als der Gegner und manchmal ist es von Vorteil Stärker zu sein als er.{EVENT=OpenShop}").
                                setAsDefaultDialog().
                                createDialog(),
                        new DialogBuilder().
                                setDialogText("Falls sie Hilfe benötigen, geben sie mir bescheid. {EVENT=OpenShop}").
                                setAsDefaultDialog()
                                .createDialog(),
                        new DialogBuilder().
                                setDialogText("Der Heiltrank kostet [], der Stärketrank kostet [] und der Geschwindigkeitstrank kostet []. {EVENT=OpenShop}").
                .setAsDefaultDialog().
                createDialog()


        ),

        VILLAGER_DIALOG_1(new DialogBuilder().

                setDialogTitle("Alf - Dorfbewohner").

                setDialogText("Hallo, Fremder! Du kommst mir bekannt vor. …. Jetzt fällt es mir ein, du bist Ryu.").

                setButtonTexts("Ich glaube sie verwechseln mich.", "Woher kennen sie mich?", "Sie verwechseln mich, ich bin sein Bruder, Shyu.").

                setNextDialogs(
                        new DialogBuilder().

                                setDialogText("Oh, tut mir leid. Als Entschuldigung nimm diese Rhoades.").

                                createDialog(),
                        new

                                DialogBuilder().

                                setDialogText("Ich kannte deinen Vater. Ein toller Mann, ehrlicher Mann, ein guter Mann ein Mann mit Ehre, ein Mann mit Glauben. Nimm daher diese 3 Tränke.").

                                createDialog(),
                        new

                                DialogBuilder().

                                setDialogText("Ich wusste gar nicht das Ryu einen Bruder hat. Gib ihm diese Rüstung von mir. Euer Vater war ein Mann mit Idealen.").

                                createDialog()

                )

                        .

                markAsTop().

                createDialog()
        ),

        VILLAGER_DIALOG_2(new DialogBuilder().

                setDialogTitle("Quentin - Dorfbewohner"));

        Dialogs(Dialog dialog) {
            this.dialog = dialog;
        }

        private Dialog dialog;
    }
}
