/**
 * NuclearData: nuclear attributes of samples in an HTI experiment
 *
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 3/8/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.HashMap;
import java.util.ArrayList;

public class NuclearData {
    public int numPlates;
    public int numRows;
    public int numCols;
    public ArrayList<PlateData> plates;
    public HashMap<String, Integer> plateIds;
    public ArrayList<String> attributes;

    ///
    public NuclearData(int nRows, int nCols) {
        numRows = nRows;
        numCols = nCols;
        numPlates =  0;
        attributes = new ArrayList<String>();
        plates = new ArrayList<PlateData>();
        plateIds = new HashMap<String, Integer>();
    }

    /**
     * 
     */
    public NuclearData() {
        this(32, 48);
    }

    /**
     * 
     * @param samp
     * @param plateName
     * @param row
     * @param col
     */
    public void addSample(SampleData samp, String plateName, int row, int col) {
    	int pid = getPlateId(plateName);
    	int sid = (row*numCols + col);
    	plates.get(pid).addSample(samp, sid);
    }
    
    private int getPlateId(String name) {
    	if(plateIds.containsKey(plateIds)) {
    		return plateIds.get(name).intValue();
    	} else {
    		return addPlate(name);
    	}
    }
    
    private int addPlate(String name) {
    	int size = plateIds.size();
		plateIds.put(name, size);
        PlateData plate = new PlateData(name, numRows*numCols);
        plate.nuclearData = this;
		plates.add(plate);
		return size;
    }
}
