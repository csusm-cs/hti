/**
 * PlateData: Nuclear Data for one plate in an experiment
 *
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 3/8/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlateData {
	SampleData [] samples;
	String plateID;
    public NuclearData nuclearData;
	
	public PlateData(String pID, int ns) {
		plateID = pID;
		samples = new SampleData[ns];
	}
	
	public void addSample(SampleData samp, int idx) {
		samples[idx] = samp;
	}
}
