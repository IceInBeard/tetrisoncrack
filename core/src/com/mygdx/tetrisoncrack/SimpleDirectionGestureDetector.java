package com.mygdx.tetrisoncrack;


import com.badlogic.gdx.input.GestureDetector;


public class SimpleDirectionGestureDetector extends GestureDetector {
    public interface DirectionListener {
        void onLeft();

        void onRight();

        void onUp();

        void onDown();

    }

    public SimpleDirectionGestureDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureDetector.GestureAdapter {
        DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if(Math.abs(velocityX)>Math.abs(velocityY)){
                if(velocityX>=1){
                    directionListener.onRight();
                }else if(velocityX <= -1){
                    directionListener.onLeft();
                }
            }else{
                if(velocityY>=1){
                    directionListener.onDown();
                }else if(velocityY<= -1){
                    directionListener.onUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }

    }

}
