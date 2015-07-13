package model;

/**
 * Created by joshuapro on 15-07-13.
 */
public class Dice  extends Observers {
    private boolean mark=false;
    private boolean save=false;
    private int diceSide=0;

    public Dice(Subject subject){
        this.subject=subject;
        this.subject.attach(this);
    }


    public int getDiceSide() {
        return diceSide;
    }
    public void setDiceSide(int diceSide) {
        this.diceSide = diceSide;
    }
    public boolean isMark() {
        return mark;
    }
    public void setMark() {
        this.mark = true;
    }
    public boolean isSave() {
        return save;
    }
    public void setSave() {
        this.save = true;
    }
    public void cancelSaved(){
        this.save=false;
    }
    public void cancelMark(){
        this.save=false;
    }




    @Override
    public void updateMarkState() {

        setMark();
    }

    @Override
    public void updateSaveState() {

        setSave();
    }

    @Override
    public void reset() {
        cancelMark();
        cancelSaved();
        setDiceSide(0);
    }


    @Override
    public void updateFromMarkToSave() {
        cancelMark();
        setSave();
    }


}
