# Development Notes

I will try to cover every step necessary to create and build a working Greenfoot project from scratch without using any 
Greenfoot 
supplied tools.

## Creating a new Project

So you want to make your own game. Great. A few placeholders will be used in this file, you'll have to replace them 
if you plan to do copy & paste:

- `$package-group$` - The groupId of your package identifier
- `$package-artifact$` - The artifactId of your package identifier
- `$project-name$` - The name of your project
- `$main-class$` - The full identifier of the class containing the `main(String[] args)` method (including full 
package name)

Let's start by creating the project directory:

```
$ mkdir ~/<project-name>
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