package de.marcus.javagame.graphics.ui.elements;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.SnapshotArray;

public class ScrollPaneShop extends ScrollPane {
    private int lastPosition;

    public ScrollPaneShop(Actor widget, ScrollPaneStyle style) {
        super(widget, style);
        lastPosition = 3;
    }

    /**
     * Little hack used to scroll 3 items of the ScrollPane at once. This is useful for the shop, as it should only scroll three at a time
     * This uses a modified version of the code of @{@link ScrollPane} of the original library.
     */

    @Override
    protected void addScrollListener() {
        addListener(new InputListener() {
            public boolean scrolled(InputEvent event, float x, float y, float scrollAmountX, float scrollAmountY) {
                setScrollbarsVisible(true);
                if (isScrollY() || isScrollX()) {
                    if (isScrollY()) {
                        if (!isScrollX() && scrollAmountY == 0) scrollAmountY = scrollAmountX;
                    } else {
                        if (isScrollX() && scrollAmountX == 0) scrollAmountX = scrollAmountY;
                    }
                    if ((lastPosition + 3 * scrollAmountX) > 0 && ((Table) getActor()).getChildren().size >= lastPosition + 3 * scrollAmountX) {


                        SnapshotArray<Actor> children = ((Table) getActor()).getChildren();
                        for (int i = 1; i < 4; i++) {
                            AlphaAction fadeOut = new AlphaAction();
                            fadeOut.setAlpha(0.0f);
                            fadeOut.setDuration(0.15f * i);

                            AlphaAction fadeIn = new AlphaAction();
                            fadeIn.setAlpha(1.0f);
                            fadeIn.setDuration(0.2f * i);


                            /*This Clusterfuck explained:

                            This is meant to fade out the elements you are scrolling away from and fade in those you are
                            scrolling towards to make them seem more smoothly moving

                            The lastPosition variable is always one bigger than the index. Meaning, that if the last
                            element of the current visible elements is index 5 in the list (array start at 0) the
                            lastPosition is 6.

                            The only reason for the for loop to start at one is the fade out duration (IDK if that's even necessary)

                            Anyways, the lastPosition is deducted by i (i max = 3) to fit it to the index (and avoid out of bounds exceptions)
                            --
                            The fade in is more complex. As the last position is always the right most element of the current visible elements,
                            you have to go different lengths in the children list to fade them.
                            If you scroll to the right, it's only needed to add the variable i.
                            But if you go to the left (negative direction hence the *scrollAmountX which is negative when scrolling up)
                            you have to go to the left by 4 to get the very right element (because if i=0 and lastPosition is 6 => 6-4=2 [index in list] aka 3. item in row == lastPosition new)
                             */

                            ((Stack) children.get(lastPosition - i))
                                    .getChild(1)
                                    .addAction(fadeOut);
                            ((Stack) children.get(lastPosition +
                                    (int) (
                                            (i - 1)
                                                    * scrollAmountX
                                                    - (scrollAmountX > 0 ? 0 : 4))
                            ))
                                    .getChild(1)
                                    .addAction(fadeIn);
                        }


                        lastPosition = (int) (lastPosition + 3 * scrollAmountX);
                    }


                    float width = ((Table) getActor()).getChild(0).getWidth();

                    //This is multiplied by 6 as the width does not include the padding of the button (every button has a padding of button width)
                    //This width is split on the left and right of the button.
                    setScrollY(getScrollY() + width * 6 * scrollAmountX);
                    setScrollX(getScrollX() + width * 6 * scrollAmountX);
                } else
                    return false;
                return true;
            }
        });
    }

}
