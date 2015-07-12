package model;

import java.util.Arrays;

/**
 * Created by joshuapro on 15-07-13.
 */
public class GameCalculator {
   private  int [] unique;
   private Dice [] deck;
   private     int [] markedDiceArray;

    public void throwDice(){

        //     if (countSave()==6 && checkForStraightFlush(markedDiceArray)){
        //       resetFromSaveToNewDice();
        //    }

        for (int a = 0; a <= deck.length - 1; a++) {

            if (!deck[a].isSave() ){
                deck[a].setDiceSide((int) ((Math.random() * 6) + 1));
                // a+1 för stege
                // (int) ((Math.random() * 6) + 1)
            }

        }


    }
    // under omgången
    public int RoundScoreValue(){
        int sum=0;

        Arrays.sort(markedDiceArray);

        if (checkForStraightFlush(markedDiceArray)){
            sum=1000;

        }else{

            sum=trippleOrOneORFive(markedDiceArray);

            //TODO arrayen kolla om det behövs
            Arrays.fill(unique, 0);


        }

        return  sum;
    }

    private void checkMarkDice(){
        markedDiceArray =new int [deck.length];
        for (int i=0; i<= deck.length-1; i++) {
            if (deck[i].isMark()) {
                markedDiceArray[i] = deck[i].getDiceSide();
            }
        }
    }

    private   boolean checkForStraightFlush(int [] chek){

        boolean chekNext=true;
        int i=0;
        while (chekNext && i < chek.length-1){
            if (chek[i]==(chek[i]+1)) {

                i++ ;
            }
            else
            { chekNext=false; }

        }
        if (i==chek.length-1){
            return true;
        }
        return false;
    }

    private   int trippleOrOneORFive(int [] check){
        int sum=0;
        for (Integer a: check){
            if(a==1) {sum+=a;}
        }if (sum==6){
            return 2000;
        }else {
            sum=0;
            Find_Unique_Elements(check);
            for (int i=0; i<unique.length; i++){
                sum +=countValue(unique[i], check);
            }if (sum > 0){
                return sum;

            }
        }


        return 0;
    }

    private   int countValue(int key , int []a){
        int nrOfOneOrFive=0;
        int s=0;

        for (int i=0; i< a.length; i++){
            if (a[i]==key){
                nrOfOneOrFive+=1;
            }
        }

        if ( nrOfOneOrFive >=3  && key > 1){
            nrOfOneOrFive=nrOfOneOrFive-3;
            s+= key*100;

        } if (nrOfOneOrFive >=3  && key == 1)  {
            nrOfOneOrFive=nrOfOneOrFive-3;
            s+= 1000;

        } if (key == 1 && nrOfOneOrFive <3){
            s+= nrOfOneOrFive*100;
        } if (key == 5 && nrOfOneOrFive <3 ){
            s+= nrOfOneOrFive*50;


        }

        return s;
    }

    void Find_Unique_Elements(int[] numarray)
    {

        int element = numarray[0];
        int count = 1;
        for (int i = 1; i < numarray.length; i++)
        {
            if (element == numarray[i])
                continue;
            else
            {
                element = numarray[i];
                count++;
            }
        }

        unique=new int[count];

        count = 0;
        element = numarray[0];
        unique[count++] = element;
        for (int i = 1; i < numarray.length; i++)
        {
            if (element == numarray[i])
                continue;
            else
            {
                element = numarray[i];
                unique[count++] = element;


            }
        }

    }
}
