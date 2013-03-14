/**
 * SampleData: The Data of a single sample
 *
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 3/8/13
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;
import java.io.*;

public class SampleData {
    public ArrayList<Double> vals;
    public String id;

    ///
    SampleData(String _id) {
        this.id = _id;
        vals = new ArrayList<Double>();
    }

    public void add(double x) {
        vals.add(new Double(x));
    }

    public void print() {
        System.out.println(id+" "+vals.toString());
    }
    ///
    public String getId() {
		return id;
	}

    ///
	public void setId(String id) {
		this.id = id;
	}

}
