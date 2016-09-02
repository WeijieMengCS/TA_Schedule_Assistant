package TA_Schedule_Assistant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class CSVFileReader {
    private static final String COMMA_DELIMITER = ",";
    private String fileHeader;

    /*********************************************************************
     *                          Access Data
     *********************************************************************/

    /**
     * Read and access data from the file
     * @param filePath: file path of the file
     * @return the voted table excluding the file header
     */
    public ArrayList<ArrayList<String>> readFile(String filePath){
        BufferedReader fileReader = null;
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
            String line;

            // Create the file reader
            fileReader = new BufferedReader(new FileReader(filePath));

            // Save the first row of the file as the header of the file, header contains names of TAs
            fileHeader = fileReader.readLine();

            // Save the rest of rows to the list, each row is a participant
            while((line = fileReader.readLine()) != null) {
                // Convert following rows into arraylist
                list.add(stringToList(line));
            }
            //System.out.println("Get the data from file successfully!");
        }
        catch(Exception e){
            System.out.println("Error in CsvFileReader!!");
            e.printStackTrace();
        }
        finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            }
            catch (IOException e){
                System.out.println("Error in closing fileReader!!");
                e.printStackTrace();
            }
        }
        print(list);
        return list;
    }

    /*********************************************************************
     *                          Modify Data
     *********************************************************************/

    private ArrayList<String> stringToList(String longString){
        String[] items = longString.split(COMMA_DELIMITER);
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(items));
        return list;
    }

    /*********************************************************************
     *                           Getter
     *********************************************************************/

    public ArrayList<String> getNameList(){
        return stringToList(fileHeader);
    }

    /*********************************************************************
     *                          Test Use
     *********************************************************************/

    private void print(ArrayList<ArrayList<String>> data){
        for (ArrayList<String> row : data){
            for (String s : row){
                System.out.print(s + ", ");
            }
            System.out.println();
        }
    }
}
