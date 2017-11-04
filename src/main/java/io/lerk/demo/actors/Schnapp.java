package io.lerk.demo.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import io.lerk.demo.worlds.MyWorld;

/**
 *
 */
public class Schnapp extends Actor
{

  /**
   * Act - do whatever the Schnapp wants to do. This method is called whenever the 'Act' or 'Run' button gets
   * pressed in
   * the environment.
   */
  @Override
  public void act()
  {
    if (Greenfoot.isKeyDown("up")) {
      setLocation(getX(), getY() - 2);
    } else if (Greenfoot.isKeyDown("down")) {
      setLocation(getX(), getY() + 2);
    }
    if (Greenfoot.isKeyDown("left")) {
      setLocation(getX() - 2, getY());
    } else if (Greenfoot.isKeyDown("right")) {
      setLocation(getX() + 2, getY());
    }

    if (getIntersectingObjects(Home.class).size() > 0) {
      ((MyWorld) getWorld()).endGame(true);
    }



    






  }
}

