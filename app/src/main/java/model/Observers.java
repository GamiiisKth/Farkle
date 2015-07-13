package model;

/**
 * Created by joshuapro on 15-07-13.
 */


    public abstract class Observers {


        protected Subject subject;
        public abstract void updateMarkState();
        public abstract void updateSaveState();
        public abstract void updateFromMarkToSave();
        public abstract void reset();



    }

