package model;

/**
 * Created by joshuapro on 15-07-13.
 */
public class Dice {
    private boolean mark=false;
    private boolean save=false;
    private int diceSide=0;


    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public int getDiceSide() {
        return diceSide;
    }

    public void setDiceSide(int diceSide) {
        this.diceSide = diceSide;
    }


}
