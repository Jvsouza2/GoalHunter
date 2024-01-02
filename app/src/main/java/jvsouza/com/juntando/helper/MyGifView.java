package jvsouza.com.juntando.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import java.io.IOException;
import java.io.InputStream;

import jvsouza.com.juntando.R;

public class MyGifView extends View {

    private Movie movie;
    private long movieStart;

    public MyGifView(Context context) {
        super(context);
        init();
    }

    public MyGifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        InputStream is = getResources().openRawResource(R.drawable.matrix);
        movie = Movie.decodeStream(is);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        super.onDraw(canvas);

        long now = android.os.SystemClock.uptimeMillis();

        if (movieStart == 0) {
            movieStart = now;
        }

        int relTime = (int) ((now - movieStart) % movie.duration());
        movie.setTime(relTime);

        // Set the size of the GIF view to match the screen dimensions
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        // Calculate the scale factors for width and height
        float scaleX = (float) screenWidth / (float) movie.width();
        float scaleY = (float) screenHeight / (float) movie.height();

        // Create a matrix for scaling the GIF
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY);

        // Apply the matrix to the canvas
        canvas.setMatrix(matrix);

        // Draw the GIF
        movie.draw(canvas, 0, 0);

        // Invalidate the view to keep animating
        this.invalidate();
    }

    // Override onMeasure to set the view dimensions to match the screen
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(screenWidth, screenHeight);
    }
}
