/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 3/8/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;

public class CSVDataParser {

    int[] attrIndex;    // indices of columns that represent features (index starts at 0)
    String[] attrNames;
    int i_row, i_col, i_plate;	// index for row, column and plateID
    int i_comp;                 // index of compound ID
    int i_invalid;
    
    public CSVDataParser() {
        attrIndex = new int[4];
        attrIndex[0] = 2; attrIndex[1] = 3; attrIndex[2] = 4; attrIndex[3] = 5;
    }

    public NuclearData readFile(String fileName) {
        NuclearData nucData = new NuclearData();
        //TODO: Implement reading the attribute file
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            // read the first header line
            String header = in.readLine();
            attrNames = header.split(",");
            for (int i = 0; i < attrNames.length; i++) {
                if(attrNames[i].equals("PlateAssayID")) {
                    i_plate = i;
                } else if (attrNames[i].equals("WellRow")) {
                    i_row = i;
                } else if (attrNames[i].equals("WellCol")) {
                    i_col = i;
                } else if (attrNames[i].equals("CompoundID")) {
                    i_comp = i;
                } else if (attrNames[i].equals("IsInvalidated")) {
                    i_invalid = i;
                }
                System.out.println(attrNames[i]);
            }
            System.out.println("i_row = "+Integer.toString(i_row)+", i_col = "+Integer.toString(i_col)+", i_plate = "+Integer.toString(i_plate));
            // add the list of attributes 
            for(int i = 0; i < attrIndex.length; i++) {
            	nucData.attributes.add(attrNames[i]);
            }
            
            // read the data lines
            String line;
            while ((line = in.readLine()) != null) {
            	// parse the line.
                String[] fields = line.split(",");
                String plateID = fields[i_plate];
                int row = Integer.parseInt(fields[i_row]) - 1;      // row and col index starts at 0
                int col = Integer.parseInt(fields[i_col]) - 1;
                int invalid = Integer.parseInt(fields[i_invalid]);
                if (invalid == 0) {         // not invalidated
                    SampleData samp = parseLine(line);
                    if(samp != null) nucData.addSample(samp, plateID, row, col);
                }
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nucData;
    }

    /**
     * Parse one sample line in the CSV file
     * @param line
     * @return
     */
    private SampleData parseLine(String line) {
        String[] fields = line.split(",");
        SampleData samp = new SampleData(fields[i_comp]);
        for (int i = 0; i < attrIndex.length; i++) {
            try {
                samp.add(Double.parseDouble(fields[attrIndex[i]]));
            } catch (NumberFormatException e) {
                System.err.println("Exception for "+samp.getId());
                e.printStackTrace();
                return null;
            }

        }
        return samp;
    }
}
