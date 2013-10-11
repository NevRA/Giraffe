package com.home.giraffe.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CommentRootLayout extends LinearLayout
{
    private int level;
    private int padding;
    private Paint paintLevel;
    private Rect levelRect;

    public CommentRootLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public CommentRootLayout(Context context)
    {
        super(context);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if(level > 0)
            canvas.drawRect(levelRect, paintLevel);

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        if(level > 0)
            levelRect = new Rect(level * padding, 0, (level + 1) * padding, getHeight());
    }

    public void setLevel(int level, int padding)
    {
        this.level = level;
        this.padding = padding;

        if(level > 0)
        {
            setPadding((level + 2) * padding, 0, 0, 0);

            paintLevel = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintLevel.setStyle(Paint.Style.FILL);

            switch (level)
            {
                case 1:
                    paintLevel.setColor(0xFF8AA378);
                    break;
                case 2:
                    paintLevel.setColor(0xFF788EA3);
                    break;
                case 3:
                    paintLevel.setColor(0xFF906AA3);
                    break;
                case 4:
                    paintLevel.setColor(0xFF6BA6FF);
                    break;
                case 5:
                    paintLevel.setColor(0xFFFF7C6B);
                    break;
                case 6:
                    paintLevel.setColor(0xFFFFB970);
                    break;
                case 7:
                    paintLevel.setColor(0xFFFF59C4);
                    break;
                case 8:
                    paintLevel.setColor(0xFF592BFF);
                    break;
                case 9:
                    paintLevel.setColor(0xFFFF2833);
                    break;
                default:
                    paintLevel.setColor(0xFF969696);
                    break;
            }
        }
    }
}