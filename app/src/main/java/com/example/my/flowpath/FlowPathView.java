package com.example.my.flowpath;/**
 * Created by ttarfall on 2015/12/28.
 */

import android.content.Context;
        import android.content.res.TypedArray;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Matrix;
        import android.graphics.Paint;
        import android.util.AttributeSet;
        import android.view.View;

/**
 * @author ttarfall
 * @date 2015-12-09 13:47
 */
public class FlowPathView extends View {

    private int lineColor;
    private int lineLeftColor;
    private int lineTopColor;
    private int lineRightColor;
    private int lineBottomColor;
    private int lineLeftWidth;
    private int lineTopWidth;
    private int lineRightWidth;
    private int lineBottomWidth;
    private int lineLeftHeight;
    private int lineTopHeight;
    private int lineRightHeight;
    private int lineBottomHeight;
    private boolean lineVisiable;
    private boolean lineLeftVisiable;
    private boolean lineTopVisiable;
    private boolean lineRightVisiable;
    private boolean lineBottomVisiable;
    private int roundRadius;
    private int roundColor;
    private int padding;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private boolean numberVisiable;
    private int number;
    private int numberColor;
    private float numberSize;
    private int imageResId;
    private Paint paint = new Paint();


    public FlowPathView(Context context) {
        super(context);
    }

    public FlowPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public FlowPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlowPathView);
        lineColor = a.getColor(R.styleable.FlowPathView_rnLineColor, Color.RED);
        lineLeftColor = a.getColor(R.styleable.FlowPathView_lineLeftColor, 0);
        lineTopColor = a.getColor(R.styleable.FlowPathView_lineTopColor, 0);
        lineRightColor = a.getColor(R.styleable.FlowPathView_lineRightColor, 0);
        lineBottomColor = a.getColor(R.styleable.FlowPathView_lineBottomColor, 0);
        lineLeftWidth = a.getDimensionPixelSize(R.styleable.FlowPathView_lineLeftWidth, 0);
        lineTopWidth = a.getDimensionPixelSize(R.styleable.FlowPathView_lineTopWidth, 0);
        lineRightWidth = a.getDimensionPixelSize(R.styleable.FlowPathView_lineRightWidth, 0);
        lineBottomWidth = a.getDimensionPixelSize(R.styleable.FlowPathView_lineBottomWidth, 0);
        lineLeftHeight = a.getDimensionPixelSize(R.styleable.FlowPathView_lineLeftHeight, 0);
        lineTopHeight = a.getDimensionPixelSize(R.styleable.FlowPathView_lineTopHeight, 0);
        lineRightHeight = a.getDimensionPixelSize(R.styleable.FlowPathView_lineRightHeight, 0);
        lineBottomHeight = a.getDimensionPixelSize(R.styleable.FlowPathView_lineBottomHeight, 0);
        lineVisiable = a.getBoolean(R.styleable.FlowPathView_lineVisiable, true);
        lineLeftVisiable= a.getBoolean(R.styleable.FlowPathView_lineLeftVisiable, false);
        lineTopVisiable= a.getBoolean(R.styleable.FlowPathView_lineTopVisiable, false);
        lineRightVisiable= a.getBoolean(R.styleable.FlowPathView_lineRightVisiable, false);
        lineBottomVisiable= a.getBoolean(R.styleable.FlowPathView_lineBottomVisiable, false);
        roundRadius = a.getDimensionPixelOffset(R.styleable.FlowPathView_roundRadius, 0);
        roundColor = a.getColor(R.styleable.FlowPathView_numberRoundColor, Color.RED);
        padding = a.getDimensionPixelSize(R.styleable.FlowPathView_padding, 0);
        paddingLeft = a.getDimensionPixelSize(R.styleable.FlowPathView_paddingLeft, 0);
        paddingTop = a.getDimensionPixelSize(R.styleable.FlowPathView_paddingTop, 0);
        paddingRight = a.getDimensionPixelSize(R.styleable.FlowPathView_paddingRight, 0);
        paddingBottom = a.getDimensionPixelSize(R.styleable.FlowPathView_paddingBottom, 0);
        numberVisiable = a.getBoolean(R.styleable.FlowPathView_numberVisiable, false);
        number = a.getInteger(R.styleable.FlowPathView_number, 0);
        numberColor = a.getColor(R.styleable.FlowPathView_numberColor, Color.WHITE);
        numberSize = a.getDimension(R.styleable.FlowPathView_numberSize, 13.0f);
        imageResId = a.getResourceId(R.styleable.FlowPathView_image, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        int w = (int) (widthSize * 1.0f / 2);
        int h = (int) (heightSize * 1.0f / 2);
        if (widthMode == MeasureSpec.EXACTLY) {
            if (lineLeftWidth <= 0)
                lineLeftWidth = w;
            if (lineRightWidth <= 0)
                lineRightWidth = lineLeftWidth;
            if (lineTopWidth <= 0)
                lineTopWidth = w;
            if (lineBottomWidth <= 0)
                lineBottomWidth = lineTopWidth;
            width = widthSize;
        } else {
            if (lineRightWidth <= 0)
                lineRightWidth = lineLeftWidth;
            if (lineBottomWidth <= 0)
                lineBottomWidth = lineTopWidth;
            width = 2 * (lineLeftWidth > lineRightWidth ? lineLeftWidth : lineRightWidth);
            if (width <= 0)
                width = 2 * roundRadius;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            if (lineLeftHeight <= 0)
                lineLeftHeight = h;
            if (lineRightHeight <= 0)
                lineRightHeight = lineLeftHeight;
            if (lineTopHeight <= 0)
                lineTopHeight = h;
            if (lineBottomHeight <= 0)
                lineBottomHeight = lineTopHeight;
            height = heightSize;
        } else {
            if (lineRightHeight <= 0)
                lineRightHeight = lineLeftHeight;
            if (lineBottomHeight <= 0)
                lineBottomHeight = lineTopHeight;
            height = 2 * (lineTopHeight > lineBottomHeight ? lineTopHeight : lineBottomHeight);
            if (height <= 0)
                height = 2 * roundRadius;
        }
        if(paddingLeft <=0 && paddingTop <=0 && paddingBottom <=0 && paddingRight<=0){//当只设置了padding时
            if(padding<0) {
                padding = 0;
            } else if(padding>roundRadius)//防止内边距超界
                padding = roundRadius;
            paddingLeft = paddingTop = paddingBottom =paddingRight = padding;
        } else {
            if(paddingLeft<0)
                paddingLeft =0;
            if(paddingTop<0)
                paddingTop = 0;
            if(paddingRight<0)
                paddingRight = 0;
            if(paddingBottom<0)
                paddingBottom = 0;
            if(paddingLeft+paddingRight>2*roundRadius)
                paddingLeft = paddingRight = roundRadius;
            if(paddingTop+paddingBottom>2*roundRadius)
                paddingTop = paddingBottom = roundRadius;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置填充
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        float centerX = getWidth() * 1.0f / 2;
        float centerY = getHeight() * 1.0f / 2;
        if(lineVisiable) {
            if (lineLeftVisiable) {
                lineLeftColor = lineLeftColor == 0 ? lineColor : lineLeftColor;
                paint.setColor(lineLeftColor);
                paint.setStrokeWidth(lineLeftHeight);
                canvas.drawLine(centerX - lineLeftWidth, centerY, centerX, centerY, paint);
            }
            if (lineTopVisiable) {
                lineTopColor = lineTopColor == 0 ? lineColor : lineTopColor;
                paint.setColor(lineTopColor);
                paint.setStrokeWidth(lineTopHeight);
                canvas.drawLine(centerX - lineTopWidth * 1.0f / 2, centerY - lineTopHeight * 1.0f / 2, centerX + lineTopWidth * 1.0f / 2, centerY - lineTopHeight * 1.0f / 2, paint);
            }
            if (lineRightVisiable) {
                lineRightColor = lineRightColor == 0 ? lineColor : lineRightColor;
                paint.setColor(lineRightColor);
                paint.setStrokeWidth(lineRightHeight);
                canvas.drawLine(centerX, centerY, centerX + lineRightWidth, centerY, paint);
            }
            if (lineBottomVisiable) {
                lineBottomColor = lineBottomColor == 0 ? lineColor : lineColor;
                paint.setColor(lineBottomColor);
                paint.setStrokeWidth(lineBottomHeight);
                canvas.drawLine(centerX - lineBottomWidth * 1.0f / 2, centerY + lineBottomHeight * 1.0f / 2, centerX + lineBottomWidth * 1.0f / 2, centerY + lineBottomHeight * 1.0f / 2, paint);
            }
        }
        paint.setColor(roundColor);
        canvas.drawCircle(centerX, centerY, roundRadius, paint);
        if(imageResId!=0){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResId);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int w = 2*roundRadius-paddingLeft-paddingRight;
            int h = 2*roundRadius-paddingTop-paddingBottom;
            int r = w>h?h:w;
            if(width>r || height>r) {
                Matrix matrix = new Matrix();
                float xy = 1.0f;
                if(height!=0)
                    xy = width*1.0f/height;
                float newWidth;
                float newHeight;
                if(xy>1.0f){
                    newWidth = r;
                    newHeight = r/xy;
                } else {
                    newWidth = r;
                    newHeight = r*xy;
                }
                matrix.setScale(newWidth/width, newHeight/height);
                Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                //float left = centerX - roundRadius + (2*roundRadius-newBitmap.getWidth()-paddingLeft-paddingRight)*1.0f/2+paddingLeft;
                //float top = centerY - roundRadius + (2*roundRadius-newBitmap.getHeight()-paddingTop-paddingBottom)*1.0f/2+paddingLeft;
                float left = centerX - (newBitmap.getWidth()-paddingLeft+paddingRight)*1.0f/2;
                float top = centerY - (newBitmap.getHeight()-paddingTop+paddingBottom)*1.0f/2;
                canvas.drawBitmap(newBitmap, left, top, paint);
            } else {
                float left = centerX - (bitmap.getWidth()-paddingLeft+paddingRight)*1.0f/2;
                float top = centerY - (bitmap.getHeight()- paddingTop+paddingBottom)*1.0f/2;
                canvas.drawBitmap(bitmap, left, top, paint);
            }
        } else {
            if(numberVisiable) {
                paint.setColor(numberColor);
                paint.setTextSize(numberSize);
                String num = String.valueOf(number);
                float textWidth = paint.measureText(num);
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                float textHeight = fontMetrics.bottom - fontMetrics.top;
                float baseline = centerY - textHeight / 2 + Math.abs(fontMetrics.top);
                canvas.drawText(num, centerX - textWidth * 1.0f / 2, baseline, paint);
            }
        }
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        boolean isRefresh = false;
        if(this.lineColor != lineColor)
            isRefresh = true;
        this.lineColor = lineColor;
        if(isRefresh)
            invalidate();
    }

    public int getLineLeftColor() {
        return lineLeftColor;
    }

    public void setLineLeftColor(int lineLeftColor) {
        boolean isRefresh = false;
        if(this.lineLeftColor != lineLeftColor)
            isRefresh = true;
        this.lineLeftColor = lineLeftColor;
        if(isRefresh)
            invalidate();
    }

    public int getLineTopColor() {
        return lineTopColor;
    }

    public void setLineTopColor(int lineTopColor) {
        boolean isRefresh = false;
        if(this.lineTopColor != lineTopColor)
            isRefresh = true;
        this.lineTopColor = lineTopColor;
        if(isRefresh)
            invalidate();
    }

    public int getLineRightColor() {
        return lineRightColor;
    }

    public void setLineRightColor(int lineRightColor) {
        boolean isRefresh = false;
        if(this.lineRightColor != lineRightColor)
            isRefresh = true;
        this.lineRightColor = lineRightColor;
        if(isRefresh)
            invalidate();
    }

    public int getLineBottomColor() {
        return lineBottomColor;
    }

    public void setLineBottomColor(int lineBottomColor) {
        boolean isRefresh = false;
        if(this.lineBottomColor != lineBottomColor)
            isRefresh = true;
        this.lineBottomColor = lineBottomColor;
        if(isRefresh)
            invalidate();
    }

    public int getLineLeftWidth() {
        return lineLeftWidth;
    }

    public void setLineLeftWidth(int lineLeftWidth) {
        boolean isRefresh = false;
        if(this.lineLeftWidth != lineLeftWidth)
            isRefresh = true;
        this.lineLeftWidth = lineLeftWidth;
        if(isRefresh)
            invalidate();
    }

    public int getLineTopWidth() {
        return lineTopWidth;
    }

    public void setLineTopWidth(int lineTopWidth) {
        boolean isRefresh = false;
        if(this.lineTopWidth != lineTopWidth)
            isRefresh = true;
        this.lineTopWidth = lineTopWidth;
        if(isRefresh)
            invalidate();
    }

    public int getLineRightWidth() {
        return lineRightWidth;
    }

    public void setLineRightWidth(int lineRightWidth) {
        boolean isRefresh = false;
        if(this.lineRightWidth != lineRightWidth)
            isRefresh = true;
        this.lineRightWidth = lineRightWidth;
        if(isRefresh)
            invalidate();
    }

    public int getLineBottomWidth() {
        return lineBottomWidth;
    }

    public void setLineBottomWidth(int lineBottomWidth) {
        boolean isRefresh = false;
        if(this.lineBottomWidth != lineBottomWidth)
            isRefresh = true;
        this.lineBottomWidth = lineBottomWidth;
        if(isRefresh)
            invalidate();
    }

    public int getLineLeftHeight() {
        return lineLeftHeight;
    }

    public void setLineLeftHeight(int lineLeftHeight) {
        boolean isRefresh = false;
        if(this.lineLeftHeight != lineLeftHeight)
            isRefresh = true;
        this.lineLeftHeight = lineLeftHeight;
        if(isRefresh)
            invalidate();
    }

    public int getLineTopHeight() {
        return lineTopHeight;
    }

    public void setLineTopHeight(int lineTopHeight) {
        boolean isRefresh = false;
        if(this.lineTopHeight != lineTopHeight)
            isRefresh = true;
        this.lineTopHeight = lineTopHeight;
        if(isRefresh)
            invalidate();
    }

    public int getLineRightHeight() {
        return lineRightHeight;
    }

    public void setLineRightHeight(int lineRightHeight) {
        boolean isRefresh = false;
        if(this.lineRightHeight != lineRightHeight)
            isRefresh = true;
        this.lineRightHeight = lineRightHeight;
        if(isRefresh)
            invalidate();
    }

    public int getLineBottomHeight() {
        return lineBottomHeight;
    }

    public void setLineBottomHeight(int lineBottomHeight) {
        boolean isRefresh = false;
        if(this.lineBottomHeight != lineBottomHeight)
            isRefresh = true;
        this.lineBottomHeight = lineBottomHeight;
        if(isRefresh)
            invalidate();
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public void setRoundRadius(int roundRadius) {
        boolean isRefresh = false;
        if(this.roundRadius != roundRadius)
            isRefresh = true;
        this.roundRadius = roundRadius;
        if(isRefresh)
            invalidate();
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        boolean isRefresh = false;
        if(this.roundColor != roundColor)
            isRefresh = true;
        this.roundColor = roundColor;
        if(isRefresh)
            invalidate();
    }


    public boolean isNumberVisiable() {
        return numberVisiable;
    }

    public void setNumberVisiable(boolean numberVisiable) {
        boolean isRefresh = false;
        if(this.numberVisiable != numberVisiable)
            isRefresh = true;
        this.numberVisiable = numberVisiable;
        if(isRefresh)
            invalidate();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        boolean isRefresh = false;
        if(this.number != number)
            isRefresh = true;
        this.number = number;
        if(isRefresh)
            invalidate();
    }

    public int getNumberColor() {
        return numberColor;
    }

    public void setNumberColor(int numberColor) {
        boolean isRefresh = false;
        if(this.numberColor != numberColor)
            isRefresh = true;
        this.numberColor = numberColor;
        if(isRefresh)
            invalidate();
    }

    public float getNumberSize() {
        return numberSize;
    }

    public void setNumberSize(float numberSize) {
        boolean isRefresh = false;
        if(this.numberSize != numberSize)
            isRefresh = true;
        this.numberSize = numberSize;
        if(isRefresh)
            invalidate();
    }

    public boolean isLineVisiable() {
        return lineVisiable;
    }

    public void setLineVisiable(boolean lineVisiable) {
        boolean isRefresh = false;
        if(this.lineVisiable != lineVisiable)
            isRefresh = true;
        this.lineVisiable = lineVisiable;
        invalidate();
    }

    public boolean isLineLeftVisiable() {
        return lineLeftVisiable;
    }

    public void setLineLeftVisiable(boolean lineLeftVisiable) {
        boolean isRefresh = false;
        if(this.lineLeftVisiable != lineLeftVisiable)
            isRefresh = true;
        this.lineLeftVisiable = lineLeftVisiable;
        if(isRefresh && lineLeftWidth>0 && lineLeftHeight>0)
            invalidate();
    }

    public boolean isLineTopVisiable() {
        return lineTopVisiable;
    }

    public void setLineTopVisiable(boolean lineTopVisiable) {
        boolean isRefresh = false;
        if(this.lineTopVisiable != lineTopVisiable)
            isRefresh = false;
        this.lineTopVisiable = lineTopVisiable;
        if(isRefresh && lineTopWidth>0 && lineTopHeight>0)
            invalidate();
    }

    public boolean isLineRightVisiable() {
        return lineRightVisiable;
    }

    public void setLineRightVisiable(boolean lineRightVisiable) {
        boolean isRefresh = false;
        if(this.lineRightVisiable != lineRightVisiable)
            isRefresh = true;
        this.lineRightVisiable = lineRightVisiable;
        if(isRefresh && lineRightWidth>0 && lineRightHeight>0)
            invalidate();
    }

    public boolean isLineBottomVisiable() {
        return lineBottomVisiable;
    }

    public void setLineBottomVisiable(boolean lineBottomVisiable) {
        boolean isRefresh = false;
        if(this.lineBottomVisiable != lineBottomVisiable)
            isRefresh = true;
        this.lineBottomVisiable = lineBottomVisiable;
        if(isRefresh && lineBottomWidth>0 && lineBottomHeight>0)
            invalidate();
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        boolean isRefresh = false;
        if(this.imageResId != imageResId)
            isRefresh = true;
        this.imageResId = imageResId;
        if(isRefresh)
            invalidate();
    }
}