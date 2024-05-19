package com.mygdx.game.data;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.Play1;
import com.mygdx.game.screens.play;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class saveload {
    MyGdxGame game;
    int i;

    public saveload(MyGdxGame game, int i){
        this.game=game;
        this.i=i;
    }
    public void save() {
        try {
            if (i == 1) {
                ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File("save1.dat").toPath()));
                datastorage ds =new datastorage();
                ds.tankposx1= Play1.player.getPosition().x;
                ds.tankposx2= Play1.player1.getPosition().x;
                ds.tankposy1= Play1.player.getPosition().y;
                ds.tankposy2= Play1.player1.getPosition().y;
                oos.writeObject(ds);
            }
            else if (i == 2) {
                ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File("save2.dat").toPath()));
                datastorage ds =new datastorage();
                ds.tankposx1= Play1.player.getPosition().x;
                ds.tankposx2= Play1.player1.getPosition().x;
                ds.tankposy1= Play1.player.getPosition().y;
                ds.tankposy2= Play1.player1.getPosition().y;
                oos.writeObject(ds);
            }
            else if (i == 3) {
                ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File("save3.dat").toPath()));
                datastorage ds =new datastorage();
                ds.tankposx1= Play1.player.getPosition().x;
                ds.tankposx2= Play1.player1.getPosition().x;
                ds.tankposy1= Play1.player.getPosition().y;
                ds.tankposy2= Play1.player1.getPosition().y;
                oos.writeObject(ds);
            }
            else if (i == 4) {
                ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File("save4.dat").toPath()));
                datastorage ds =new datastorage();
                ds.tankposx1= Play1.player.getPosition().x;
                ds.tankposx2= Play1.player1.getPosition().x;
                ds.tankposy1= Play1.player.getPosition().y;
                ds.tankposy2= Play1.player1.getPosition().y;
                oos.writeObject(ds);
            }
            else if (i == 0) {
                game.setScreen(new Play1(game));
                datastorage ds =new datastorage();
                Play1.player.getPosition().x=ds.tankposx1;
                Play1.player1.getPosition().x=ds.tankposx2;
                Play1.player.getPosition().y= ds.tankposy1;
                Play1.player1.getPosition().y=ds.tankposy2;
            }

        }
        catch(Exception e){
            System.out.println("Save Exception");
        }
    }
    public void load() {
        try {
            if (i == 1) {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("save1.dat").toPath()));
                datastorage ds =(datastorage)ois.readObject();
                game.setScreen(new Play1(game));
                 Play1.player.getPosition().x=ds.tankposx1;
                 Play1.player1.getPosition().x=ds.tankposx2;
                 Play1.player.getPosition().y= ds.tankposy1;
                 Play1.player1.getPosition().y=ds.tankposy2;
            }
            else if (i == 2) {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("save2.dat").toPath()));
                datastorage ds =(datastorage)ois.readObject();
                game.setScreen(new Play1(game));
                Play1.player.getPosition().x=ds.tankposx1;
                Play1.player1.getPosition().x=ds.tankposx2;
                Play1.player.getPosition().y= ds.tankposy1;
                Play1.player1.getPosition().y=ds.tankposy2;
                }
            else if (i == 3) {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("save3.dat").toPath()));
                datastorage ds =(datastorage)ois.readObject();
                game.setScreen(new Play1(game));
                Play1.player.getPosition().x=ds.tankposx1;
                Play1.player1.getPosition().x=ds.tankposx2;
                Play1.player.getPosition().y= ds.tankposy1;
                Play1.player1.getPosition().y=ds.tankposy2;
            }
            else if (i == 4) {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("save4.dat").toPath()));
                datastorage ds =(datastorage)ois.readObject();
                game.setScreen(new Play1(game));
                Play1.player.getPosition().x=ds.tankposx1;
                Play1.player1.getPosition().x=ds.tankposx2;
                Play1.player.getPosition().y= ds.tankposy1;
                Play1.player1.getPosition().y=ds.tankposy2;
            }

        }
        catch(Exception e){
            System.out.println("Load Exception");
        }
    }
}

