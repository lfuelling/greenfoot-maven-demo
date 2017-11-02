package io.lerk.demo.actors;

import greenfoot.*;

/**
 *
 */
public class Schnapp extends Actor
{

    /**
     * Act - do whatever the Schnapp wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("up")) {
            move(1);
        } else if (Greenfoot.isKeyDown("down")) {
            move(-1);
        }
        if(Greenfoot.isKeyDown("left")) {
            turn(-1);
        } else if (Greenfoot.isKeyDown("right")) {
            turn(1);
        }

        Actor home = getOneObjectAtOffset(0,0,Home.class);
        if(home != null) {
            World world = getWorld();
            world.removeObject(Schnapp.this);
            world.showText("DONE!", 50, 50);
        }
    }
}

