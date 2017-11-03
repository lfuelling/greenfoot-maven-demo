package io.lerk.demo.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import io.lerk.demo.worlds.MyWorld;
import java.util.List;

public class Thug extends Actor
{

  public Thug()
  {
    getImage().scale(87, 156);
  }

  @Override
  public void act()
  {
    if (Greenfoot.isKeyDown("w")) {
      setLocation(getX(), getY() - 2);
    } else if (Greenfoot.isKeyDown("s")) {
      setLocation(getX(), getY() + 2);
    }

    if (Greenfoot.isKeyDown("a")) {
      setLocation(getX() - 2, getY());
    } else if (Greenfoot.isKeyDown("d")) {
      setLocation(getX() + 2, getY());
    }

    if (getIntersectingObjects(Schnapp.class).size() > 0) {
      MyWorld world = (MyWorld) getWorld();
      world.removeActors();
      world.showText("FAIL!", 400, 50);
    }
  }
}
