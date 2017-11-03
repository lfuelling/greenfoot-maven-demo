# Development Notes

I will try to cover every step necessary to create and build a working Greenfoot project from scratch without using any 
Greenfoot supplied tools.

Please read the [README](README.md) first, if you haven't already.

## Creating a new Project

So you want to make your own game. Great. 

### Placeholders

A few placeholders will be used in this file, you'll have to replace them 
if you plan to do copy & paste. I will use the following format:

```
- placeholder - explaination - sample value used in this project
```

Here you go:

- `$package-group$` - The groupId of your package identifier - `io.lerk`
- `$package-artifact$` - The artifactId of your package identifier - `greenfoot-maven-demo`
- `$project-name$` - The name of your project - `greenfoot-maven-demo`
- `$main-class$` - The full identifier of the class containing the `main(String[] args)` method (including full 
package name) - `io.lerk.demo.DemoApp`
- `$main-class-package$` - The package of the main class - `io.lerk.demo`
- `$main-class-name$` - The name of your main class - `DemoApp`
- `$main-class-path$` - The path to the main class relative from `src/main/java` - `io/lerk/demo`

- `$world-class$` - The full identifier of the main World class - `io.lerk.demo.worlds.MyWorld`
- `$world-class-name$` - Only the name of the class - `MyWorld`
- `$world-class-package$` - Only the package of the class - `io.lerk.demo.worlds`
- `$world-class-path$` - The location of the world class relative to `src/main/java` - `io/lerk/demo/worlds`
- `$world-image$` - The file name of the image to use as world background - `crumpled-paper.jpg`

- `$actor-class$` - The full identifier of the main World class - `io.lerk.demo.actors.MyActor`
- `$actor-class-name$` - Only the name of the class - `MyActor`
- `$actor-class-package$` - Only the package of the class - `io.lerk.demo.actors`
- `$actor-class-path$` - The location of the actor class relative to `src/main/java` - `io/lerk/demo/actors`
- `$actor-image$` - The file name of the image to use as world background - `some-dude.jpg`

**REMEMBER TO REPLACE THOSE PLACEHOLDERS!**

### Preparation

Let's start by creating the project directory and go into it:

```
$ mkdir ~/$project-name$ && cd ~/$project-name$ 
```

### Setting up Maven

In the directory, create a new file called `pom.xml` and put the following stuff into it:

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>$package-group$</groupId>
    <artifactId>$package-artifact$</artifactId>
    <version>1.0-SNAPSHOT</version>

    <repositories>
        <repository>
            <id>k40s-public</id>
            <url>https://nexus.k40s.net/repository/maven-public/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.greenfoot</groupId>
            <artifactId>greenfoot</artifactId>
            <version>3.1.0</version>
        </dependency>
        <dependency>
            <groupId>org.bluej</groupId>
            <artifactId>bluej-core</artifactId>
            <version>4.0.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <excludes>
                        <!-- exclude>**/log4j.properties</exclude -->
                    </excludes>
                    <archive>
                        <manifest>
                            <mainClass>$main-class$</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>$main-class$</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

#### Repository and Dependencies

The repository is needed, because the dependencies are not available anywhere else for maven. On a mac, you can find 
the `greenfoot.jar` at `/Applications/Greenfoot.app/Resources/Java/extensions/greenfoot.jar` and you'll have to 
download the greenfoot source code and build the `bluej` project with `ant jar-core` to get the `bluej.jar`. As you 
can see, it's a lot more convenient to just use the Maven dependencies.

#### Plugins

The plugins are needed because we need a valid manifest (done by `maven-jar-plugin`) and all the dependencies are 
required to be present in the jar file (done by `maven-assembly-plugin`).

### Adding required Greenfoot stuff

Some files and directories are required by greenfoot. Let's take care of those first.

#### Directory structure

At first, we'll need to create a few directories:

```
$ mkdir -p src/main/java src/main/resources/images src/main/resources/sounds
```

#### standalone.properties

Now, let's create the file `src/main/resources/standalone.properties` and put the following stuff into it:

```
project.name=$project-name$
main.class=$world-class$
scenario.lock=false
```

The `scenario.lock` property disables the speed seek bar and the act once button.

#### project.greenfoot

The next required file is `src/main/resources/project.greenfoot`. Let's use the following content for it:

```
# Class definitions
class.$world-class$.image=$world-image$

# Editor Settings
editor.fx.0.height=0
editor.fx.0.width=0
editor.fx.0.x=0
editor.fx.0.y=0

# Main Window Settings
mainWindow.height=800
mainWindow.width=850
mainWindow.x=40
mainWindow.y=40

# Misc Project Settings
package.numDependencies=0
package.numTargets=1
project.charset=UTF-8
readme.height=58
readme.name=@README
readme.width=47
readme.x=10
readme.y=10

# Target definitions (unknown use)
target1.height=50
target1.name=$world-class$
target1.showInterface=false
target1.type=ClassTarget
target1.typeParameters=
target1.width=80
target1.x=0
target1.y=0

# Misc/Unknown
version=3.0.0
world.lastInstantiated=$world-class$
```

#### README.txt

I was yet unable to find out where this is used. That's why I'll just put one word into it for now. Create it using:

```
$ echo "stub" > src/main/resources/README.txt
```

### Adding classes

Let's write some code. 

#### World

At first, we create the world class we just defined in the `project.greenfoot` file.

Remember to have the correct package structure. You will need to create the folders for it first:

```
$ mkdir -p src/main/java/$world-class-path$
```

Now you can create the file in `src/main/java/$world-class-path$/$world-class-name$.java`. The content should look like 
this:

```
package $world-class-package$;

import greenfoot.World;

/**
 * World class.
 */
public class $world-class-name$ extends World
{

  private Text text;
  private Home home;
  private Thug thug;
  private Schnapp schnapp;

  /**
   * Constructor for objects of class $world-class$.
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
    
  }
}
```

If you have not done this by now, you should add the world image defined as `class.$world-class$.image` in `project
.greenfoot` into the 
`src/main/resources/images` folder. The file type can be `.png`, `.gif` and `.jp(e)g` and the name should not contain any spaces.

#### The first actor

Now let's create a random actor. At first, create the missing package:

```
$ mkdir src/main/java/$actor-class-path$
```

Now we create the file `src/main/java/$actor-class-path$/$actor-class-name$.java` with the following content:

```
package $actor-class-package$;

import greenfoot.Actor;

/**
 * Write a description of class $actor-class-name$ here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class $actor-class-name$ extends Actor
{
    /**
     * Act - do whatever the $actor-class-name$ wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act()
    {
        // Add your action code here.
    }
}
```

As you might have realized by now, we also need to create an entry for it in the `project.greenfoot` file. It's not 
important where you place it in the file, but I recommend putting similar keys together for better readability. Put 
the following code into `src/main/resources/project.greenfoot`:

```
class.$actor-class$.image=$actor-image$
``` 

#### Final steps

##### The main class

You will also have to have a class that starts your project. This is very nifty if you want to do some initialization before the game loads like configuring the `MenuBar` on macOS or something else.

Let's create the file `src/main/java/$main-class-path$/$main-class-name$.java` and put the following content into it:

```
package $main-class-package$;

import greenfoot.export.GreenfootScenarioMain;

/**
 * @author (User Name)
 */
public class $main-class-name$ extends GreenfootScenarioMain {
    public static void main(String[] args) {
        GreenfootScenarioMain.main(args);
    }
}
```

This will start the Greenfoot launcher which in turn loads your game. If you need to do any initialization, do it before the `GreenfootScenarioMain.main(args);` call.

##### Adding and using sound files

I must confess, I didn't read the Greenfoot docs too well. Therefore I don't know, which audio files are supported, but I guess it just supports everything that Java does. I'm using `.wav` files in this example.

First you'll need a soundfile. Download one, record it, I don't really care. I suggest that you follow the same naming principles as with the images (no spaces in file name).

Put the file in `src/main/resources/sounds` and also create a new text file: `src/main/resources/soundindex.list`. Just put in the filename of your sounds, each in it's own line. No paths or anything, just the filename.

After that, you can play your sound by using: `Greenfoot.playSound("mysound.wav")`.

##### Adding the actor into the world

If you start the game now, you'll notice that the actor is not yet visible. That's because we need to add it to the 
world first. Add the following code into the `prepare()` method in your world class:

```
$actor-class-name$ actor = new $actor-class-name$();
addObject(actor, 50, 50);
```

Also, you will maybe need to add the following below the `package` statement:

`import $actor-class$;`

Some IDE's do this automatically!

##### Let the actor move

To let the actor move, put the following code into the `act()` method of the actor class:

```
if (Greenfoot.isKeyDown("up")) {
  setLocation(getX(), getY() - 1);
} else if (Greenfoot.isKeyDown("down")) {
  setLocation(getX(), getY() + 1);
}
if (Greenfoot.isKeyDown("left")) {
  setLocation(getX() - 1, getY());
} else if (Greenfoot.isKeyDown("right")) {
  setLocation(getX() + 1, getY());
}
```

##### Building and Running

Let's try to build the project:

```
$ mvn clean install -DskipTests=true
```

If this command succeeds, you can try to run the game:

```
$ java -jar target/$project-name$-1.0-SNAPSHOT-jar-with-dependencies.jar
```

The game window should open now and if you click play, you should be able to move the actor using the arrow keys.

If this doesn't work or you got an error while maven was building please check *all* of the files for leftover 
placeholders. Alternatively you might want to read the error message (if there is any) and solve your problem this way.
