/*********************************************************************
 *                           @author: Mx
 *********************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class StatsCollector {
    private ArrayList<LabSection> labSectionList;
    private ArrayList<TA> TAList;
    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> nameList;


    /*********************************************************************
     *                              Setter
     *********************************************************************/

    public void setData(ArrayList<ArrayList<String>> data){
        this.data = data;
    }

    public void setNameList(ArrayList<String> nameList){
        this.nameList = nameList;
    }


    /*********************************************************************
     *                              Getter
     *********************************************************************/

    public ArrayList<LabSection> getLabSectionList(){
        return labSectionList;
    }

    public ArrayList<TA> getTAList(){
        return TAList;
    }

    /*********************************************************************
     *                              Operation
     *********************************************************************/

    public void collectStats(){
        if (data != null){
            // get info for TA list
            getTAInfo();
            // get info for each Section
            getSectionsInfo();

            // sort the section by order where section with fewest candidates is at top
            sortSectionByNumberOfCandidates();
            print();
        }
    }


    private void getTAInfo(){
        TAList = new ArrayList<>();
        TA ta;
        ArrayList<Integer> doableSectionList;
        for (int i = 1; i < data.get(0).size(); i++){
            ta = new TA();
            doableSectionList = new ArrayList<>();
            for (int j = 1; j < data.size(); j++){
                if (data.get(j).get(i).equals("y")){
                    doableSectionList.add(j);
                }
            }
            // set the associated info for a ta
            ta.setName(nameList.get(i));
            ta.setDoableSectionList(doableSectionList);
            ta.setAssignedSectionList(new ArrayList<>());
            TAList.add(ta);
        }

        /*for (ArrayList<String> row : data){
            ta = new TA();
            doableSectionList = new ArrayList<>();
            for (int i = 1; i < row.size(); i++){
                if (row.get(i).equals("y")){
                    doableSectionList.add(i);
                }
            }
            // set the associated info for a ta
            ta.setName(row.get(0));
            ta.setDoableSectionList(doableSectionList);
            ta.setAssignedSectionList(new ArrayList<>());
            TAList.add(ta);
        }*/
    }

    private void getSectionsInfo(){
        labSectionList = new ArrayList<>();
        LabSection section;
        ArrayList<String> candidates;
        for (int i = 0; i < data.size(); i++){
            section = new LabSection();
            candidates = new ArrayList<>();
            for (int j = 1; j < data.get(i).size(); j++){
                if (data.get(i).get(j).equals("y")){
                    candidates.add(nameList.get(j));
                }
            }
            section.setSectionNumber((i+1));
            section.setCandidates(candidates);
            section.setNominated(new ArrayList<>());
            section.setFulfilled(false);
            labSectionList.add(section);
        }

        /*for (int i = 1; i < data.get(0).size()-1; i++){
            section = new LabSection();
            candidates = new ArrayList<>();
            for (int j = 0; j < data.size(); j++){
                if (data.get(j).get(i).equals("y")){
                    candidates.add(data.get(j).get(0));
                }
            }
            section.setSectionNumber(i);
            section.setCandidates(candidates);
            section.setNominated(new ArrayList<>());
            labSectionList.add(section);
        }*/
    }

    private void sortSectionByNumberOfCandidates(){
        Collections.sort(labSectionList, new Comparator<LabSection>() {
            @Override
            public int compare(LabSection section1, LabSection section2) {
                return section1.getNumberOfCandidates()-section2.getNumberOfCandidates();
            }
        });
    }

    /*********************************************************************
     *                            Test Only
     *********************************************************************/

    private void print(){
        System.out.println("\n################ Print Collected Stats\n");
        // print info for each section

        System.out.println("**************** ta ");
        for (TA ta : TAList){
            System.out.print("TA " + ta.getName() + ": ");
            System.out.println(Arrays.toString(ta.getDoableSectionList().toArray()));
        }

        System.out.println("**************** lab sections");
        for (LabSection section : labSectionList){
            System.out.println("Section " + section.getSectionNumber() + ": ");
            System.out.println(Arrays.toString(section.getCandidates().toArray()));
        }
    }

}
