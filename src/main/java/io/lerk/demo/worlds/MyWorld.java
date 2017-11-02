package io.lerk.demo.worlds;

import io.lerk.demo.actors.Home;
import io.lerk.demo.actors.Schnapp;
import io.lerk.demo.actors.Text;
import greenfoot.*;

/**
 *
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class io.lerk.demo.worlds.MyWorld.
     */
    public MyWorld()
    {
        super(600, 400, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Home home = new Home();
        addObject(home,50,59);

        Schnapp schnapp = new Schnapp();
        addObject(schnapp,534,300);

        Text text = new Text();
        addObject(text,191,359);
    }
}
