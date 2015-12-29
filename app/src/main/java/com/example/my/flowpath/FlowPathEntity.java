package com.example.my.flowpath;/**
 * Created by ttarfall on 2015/12/25.
 */

import java.io.Serializable;

/**
 * @author ttarfall
 * @date 2015-12-25 17:38
 */
public class FlowPathEntity implements Serializable {

    private int number;
    private boolean currentNumber = false;
    private boolean iconVisiable = false;
    private int icon =0;
    private String text;
    private boolean topLineVisiable = true;
    private boolean bottomLineVisiable = true;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTopLineVisiable() {
        return topLineVisiable;
    }

    public void setTopLineVisiable(boolean topLineVisiable) {
        this.topLineVisiable = topLineVisiable;
    }

    public boolean isBottomLineVisiable() {
        return bottomLineVisiable;
    }

    public void setBottomLineVisiable(boolean bottomLineVisiable) {
        this.bottomLineVisiable = bottomLineVisiable;
    }

    public void setIconVisiable(boolean iconVisiable) {
        this.iconVisiable = iconVisiable;
    }

    public boolean isIconVisiable() {
        return iconVisiable;
    }

    public boolean isCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(boolean currentNumber) {
        this.currentNumber = currentNumber;
    }
}
