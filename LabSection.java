package TA_Schedule_Assistant;

import java.util.ArrayList;

public class LabSection {
    private int sectionNumber;
    private ArrayList<String> candidates;
    private ArrayList<String> nominated;
    private int numberOfCandidates;
    private boolean fulfilled;

    /*********************************************************************
     *                              Setter
     *********************************************************************/
    public void setNominated(ArrayList<String> nominated){
        this.nominated = nominated;
    }

    public void setCandidates(ArrayList<String> candidates){
        this.candidates = candidates;
    }

    public void setSectionNumber(int sectionNumber){
        this.sectionNumber = sectionNumber;
    }

    public void setFulfilled(boolean fulfilled) { this.fulfilled = fulfilled; }

    /*********************************************************************
     *                              Getter
     *********************************************************************/
    public int getSectionNumber(){
        return sectionNumber;
    }

    public ArrayList<String> getCandidates(){
        return candidates;
    }

    public ArrayList<String> getNominated(){
        return nominated;
    }

    public int getNumberOfCandidates() { return candidates.size(); }

    public boolean getFulfilled() { return fulfilled; }
}
