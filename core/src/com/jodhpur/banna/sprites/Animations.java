package com.jodhpur.banna.sprites;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Yashwant on 9/27/2015.
 */
public class Animations {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animations(TextureRegion  region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWiddth = region.getRegionWidth()/frameCount;
        for(int i=0;i<frameCount;i++){
            frames.add(new TextureRegion(region,i*frameWiddth,0, frameWiddth ,region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime/frameCount;
        frame = 0;

    }

    public void update(float dt){
        currentFrameTime +=dt;
        if(currentFrameTime>maxFrameTime){
            frame++;
            currentFrameTime=0;
        }
        if(frame >= frameCount) {
            frame = 0;
        }
    }

    public  TextureRegion getFrame(){
        return  frames.get(frame);
    }
}
