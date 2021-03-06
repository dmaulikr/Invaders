package com.invaders.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.invaders.game.Invaders;
import com.invaders.game.sound.SoundProvider;
import com.invaders.game.texture.TextureManager;

/**
 * Created by aliyuabdullahi on 9/6/16.
 */
public class Player extends Entity {
    EntityManager entityManager;
    private long lastFire;
    public static Sound sound;
    public static long soundId;

    public Player(Texture texture, Vector2 position, Vector2 direction, EntityManager entityManager) {
        super(texture, position, direction);
        this.entityManager = entityManager;
    }

    @Override
    public void update() {
        position.add(direction);

        if (Gdx.input.isKeyPressed(Input.Keys.A) && this.getPosition().x > 0) {
            setDirection(-300, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && this.getPosition().x < Invaders.WIDTH)
            setDirection(300, 0);
        else
            setDirection(0, 0);

//        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            if(System.currentTimeMillis() - lastFire >= 250){
//                entityManager.addEntity(new Missile(position.cpy().add(25, TextureManager.PLAYER.getHeight())));
//                lastFire = System.currentTimeMillis();
//            }
//        }

        //Auto Fire

        if (System.currentTimeMillis() - lastFire >= 700) {
            entityManager.addEntity(new Missile(position.cpy().add(25, TextureManager.PLAYER.getHeight())));
            soundId = SoundProvider.playSound(SoundProvider.InvaderSound.DEFAULT_GUN, sound, false);
            lastFire = System.currentTimeMillis();
        }
    }
}
