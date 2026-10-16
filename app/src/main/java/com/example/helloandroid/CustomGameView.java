package com.example.helloandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

public class CustomGameView extends View implements Choreographer.FrameCallback {
    private final Paint paint = new Paint();
    private boolean running = false;
    private long lastFrameTimeNanos = 0;

    private float playerX = 0.0F;
    private float playerY = 0.0F;
    private static final int SPEED_PER_SEC = 500;

    public CustomGameView(Context context) {
        super(context);

        paint.setAntiAlias(true);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (!running) {
            return;
        }

        float deltaTimeSecs = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000_000.0F;
        lastFrameTimeNanos = frameTimeNanos;

        update(deltaTimeSecs);

        invalidate();

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        paint.setColor(Color.BLUE);
        canvas.drawCircle(playerX, playerY, 70.0F, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE) {
            playerY = event.getY();
        }

        return super.onTouchEvent(event);
    }

    public void update(float deltaTimeSecs) {
        playerX += SPEED_PER_SEC * deltaTimeSecs;

        if (playerX > getWidth()) {
            playerX = 0.0F;
        }
    }

    public void resume() {
        running = true;
        lastFrameTimeNanos = System.nanoTime();

        Choreographer.getInstance().postFrameCallback(this);
    }

    public void pause() {
        running = false;

        Choreographer.getInstance().removeFrameCallback(this);
    }
}