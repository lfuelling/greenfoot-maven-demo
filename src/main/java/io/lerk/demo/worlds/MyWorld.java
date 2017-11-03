package io.lerk.demo.worlds;

import greenfoot.World;
import io.lerk.demo.actors.Home;
import io.lerk.demo.actors.Schnapp;
import io.lerk.demo.actors.Text;
import io.lerk.demo.actors.Thug;

/**
 *
 */
public class MyWorld extends World
{

  private Text text;
  private Home home;
  private Thug thug;
  private Schnapp schnapp;

  /**
   * Constructor for objects of class io.lerk.demo.worlds.MyWorld.
   */
  public MyWorld()
  {
    super(850, 600, 1);
    prepare();
  }

  /**
   * Prepare the world for the start of the program.
   * That is: create the initial objects and add them to the world.
   * Z-Index is based on order of addition (last to be added is on top)
   */
  private void prepare()
  {
    home = new Home();
    schnapp = new Schnapp();
    thug = new Thug();
    text = new Text();

    addObject(home, 425, 30);
    addObject(schnapp, 700, 500);
    addObject(thug, 50, 80);
    addObject(text, 191, 359);
  }

  /**
   * Called when the game is finished. Removes all active actors.
   */
  public void removeActors() {
    if (home != null) {
      removeObject(home);
    }
    if(text != null) {
      removeObject(text);
    }
    if (schnapp != null) {
      removeObject(schnapp);
    }
    if(thug != null) {
      removeObject(thug);
    }
  }
}
