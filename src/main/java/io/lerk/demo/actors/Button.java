package io.lerk.demo.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Button extends Actor {

    private final Action action;
    private final OnClickListener onClickListener;

    public Button(Action action, OnClickListener onClickListener) {
        this.action = action;
        this.onClickListener = onClickListener;

        setImage(action.getImage());
    }

    @Override
    public void act() {
        if(Greenfoot.mouseClicked(this)) {
            onClickListener.click(this);
        }
    }

    public enum Action {
        RESTART("restart.png");

        private final String image;

        Action(String image) {
            this.image = image;
        }

        public String getImage() {
            return image;
        }
    }

    public interface OnClickListener {
        void click(Button button);
    }
}
