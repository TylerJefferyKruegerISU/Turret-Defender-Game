package com.example.towerDefender.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public abstract class Sprite {

    public enum SPRITE_STATUS{
        ATTACKING, MOVING, IDLE, DYING
    }

    /**
     * The sprite sheet for this {@link Sprite}
     */
    public Bitmap image;
    public int xStart, yStart, xEnd, yEnd;
    protected Sprite.SPRITE_STATUS status;
    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static int normalizedInventorySize = (screenWidth - 900) / 4;
    public static int normalizedButtonSize = screenWidth / 15;

    public Sprite(Bitmap bitmap, int xStart, int yPos){
        image = bitmap;
        this.xStart = xStart;
        this.yStart = yPos;
        xEnd = this.xStart + image.getWidth();
        yEnd = this.yStart + image.getHeight();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, xStart, yStart, null);
    }

    /**
     * @return the starting x value of this {@link Sprite}'s {@link Bitmap}
     */
    public int getxStart(){ return xStart; }

    /**
     * @return the starting y value of this {@link Sprite}'s {@link Bitmap}
     */
    public int getyStart(){ return yStart; }

    /**
     * @return the ending x value of this {@link Sprite}'s {@link Bitmap}
     */
    public int getxEnd(){ return xEnd; }

    /**
     * @return the ending y value of this {@link Sprite}'s {@link Bitmap}
     */
    public int getyEnd(){ return yEnd; }

    public void setStatus(Sprite.SPRITE_STATUS status){
        this.status = status;
    }

}
