import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StatsOperator {
    private ArrayList<LabSection> labSectionList;
    private ArrayList<TA> TAList;
    private int numberOfParticipantsRequiredForEachSection; // GTA is one while UTA is usually at least two

    /*********************************************************************
     *                              Setter
     *********************************************************************/

    public void setLabSectionList(ArrayList<LabSection> labSectionList) {
        this.labSectionList = labSectionList;
    }

    public void setTAList(ArrayList<TA> TAList) {
        this.TAList = TAList;
    }

    public void setNumberOfParticipantsRequiredForEachSection(int number){
        this.numberOfParticipantsRequiredForEachSection = number;
    }

    /*********************************************************************
     *                              Getter
     *********************************************************************/

    public ArrayList<LabSection> getLabSectionList() {
        return labSectionList;
    }

    public ArrayList<TA> getTAList() {
        return TAList;
    }

    /*********************************************************************
     *                              Operation
     *********************************************************************/

    public void beginAssignment(){
        if (numberOfParticipantsRequiredForEachSection == 1){
            // Assign GTA
            runGTAAssignment();
        }
        else {
            // Assign UTA
            runUTAAssignment();
        }
        // Sort the section list by section number
        sortSectionBySectionNumber();
        print();
    }

    private void runGTAAssignment(){
        ArrayList<String> candidates;
        TA ta;
        String nameOfTA;
        for (LabSection section : labSectionList) {
            if (!section.getFulfilled()){
                candidates = section.getCandidates();
                if (section.getNumberOfCandidates() == 1){
                    // one to one case
                    nameOfTA = candidates.get(0);
                    ta = getTA(nameOfTA);
                    assignSection(section, ta);
                }
                else {
                    // sort the candidates list and pick the one with highest priority
                    candidates = sortCandidates(candidates, section.getSectionNumber());
                    ta = getTA(candidates.get(0));
                    assignSection(section, ta);
                }
            }
        }
    }

    private void runUTAAssignment(){
        ArrayList<String> candidates;
        TA ta;
        String nameOfTA;
        int times = 0;

        while (times < numberOfParticipantsRequiredForEachSection){
            for (LabSection section : labSectionList){
                if (!section.getFulfilled()){
                    candidates = section.getCandidates();
                    if (section.getNumberOfCandidates() < numberOfParticipantsRequiredForEachSection){
                        // Assign all the available candidates to the section
                        for (String s : candidates){
                            ta = getTA(s);
                            assignSection(section, ta);
                        }
                        section.setFulfilled(true);
                    }
                    else {
                        // Section with at least required number of TAs
                        // sort the candidates list and pick the one with highest priority
                        candidates = sortCandidates(candidates, section.getSectionNumber());
                        ta = getTA(candidates.get(0));
                        assignSection(section, ta);
                        if (section.getNominated().size() == numberOfParticipantsRequiredForEachSection){
                            section.setFulfilled(true);
                        }
                    }
                }
            }
            times++;
        }
    }

    /**
     * Get TA from the TA List
     * @param nameOfTA: name of TA
     * @return found TA
     */
    private TA getTA(String nameOfTA){
        int indexOfTA = getIndexOfTA(nameOfTA);
        return TAList.get(indexOfTA);
    }

    /**
     * Get index of a TA in TA list
     * @param name: name of the TA
     * @return the index
     */
    private int getIndexOfTA(String name){
        int index = 0;
        for (TA ta : TAList){
            if (ta.getName().equals(name)){
                break;
            }
            index++;
        }
        return index;
    }

    /**
     * Sort candidates by the following priority setting
     * 0 sections assigned is the top priority
     * fewer sections assigned is the higher priority
     * if all candidates assigned same number of sections, pick the one assigned earliest section
     * @param candidates
     * @param sectionNumber
     * @return
     */
    private ArrayList<String> sortCandidates(ArrayList<String> candidates, int sectionNumber){
        Collections.sort(candidates, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2){
                TA t1 = getTA(s1);
                TA t2 = getTA(s2);

                if ((t1.getNumberOfSectionsAssigned() == t2.getNumberOfSectionsAssigned()) && t1.getNumberOfSectionsAssigned() != 0){
                    // Compare the gap btw the prev last assigned section with the current section
                    ArrayList<Integer> assignedSectionList = t1.getAssignedSectionList();
                    // get the last section assigned to the ta
                    int sectionNumberOfLastSection =assignedSectionList.get(t1.getNumberOfSectionsAssigned()-1);
                    // calculate gap for TA a
                    int gap_a = Math.abs(sectionNumber - sectionNumberOfLastSection);
                    // calculate gap for TA b
                    assignedSectionList = t2.getAssignedSectionList();
                    sectionNumberOfLastSection = assignedSectionList.get(t2.getNumberOfSectionsAssigned()-1);
                    int gap_b = Math.abs(sectionNumber - sectionNumberOfLastSection);
                    return gap_b - gap_a;
                }
                else {
                    // Compare number of sections assigned
                    int number1 = t1.getNumberOfSectionsAssigned();
                    int number2 = t2.getNumberOfSectionsAssigned();
                    return number1 - number2;
                }
            }
        });

        return candidates;
    }

    /**
     * Sort the lab section list by its section number
     */
    private void sortSectionBySectionNumber(){
        Collections.sort(labSectionList, new Comparator<LabSection>() {
            @Override
            public int compare(LabSection section1, LabSection section2) {
                return section1.getSectionNumber()-section2.getSectionNumber();
            }
        });
    }

    /**
     * Assign the ta to the section
     * @param section: lab section to be assigned
     * @param ta: ta to be assigned
     */
    private void assignSection(LabSection section, TA ta){
        ArrayList<String> nominated;
        ArrayList<Integer> assignedSectionList;

        // update info for section
        nominated = section.getNominated();
        nominated.add(ta.getName());
        section.setNominated(nominated);

        // update info for ta
        assignedSectionList = ta.getAssignedSectionList();
        assignedSectionList.add(section.getSectionNumber());
        if (assignedSectionList.size() > 1){
            // sort the assignedSectionList by its assigned section number
            Collections.sort(assignedSectionList, new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    return a - b;
                }
            });
        }
        ta.setAssignedSectionList(assignedSectionList);
    }


    /*********************************************************************
     *                            Test Only
     *********************************************************************/

    private void print(){
        System.out.println("\n %%%%%%%%%%%%%%%% final schedule \n");
        ArrayList<String> nominated;
        for (LabSection section : labSectionList){
            System.out.print("Section: " + section.getSectionNumber() + ": ");
            nominated = section.getNominated();
            for (String s : nominated){
                System.out.print(s + ", ");
            }
            if (section.getNominated().size() < numberOfParticipantsRequiredForEachSection){
                System.out.print("\t **Still Need More TAs");
            }
            System.out.println();
        }
    }

}
