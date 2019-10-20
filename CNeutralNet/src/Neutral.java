
import java.util.*;

public class Neutral
{
	private ArrayList<Double> listOfWeightIn;	//输入权值集合
	private ArrayList<Double> listOfWeightOut;	//输出权值集合
	
	//返回一个0.00-1.00的伪随机实数
	public double initNeutral(){
		return new Random().nextDouble();
	}
	
	//双get双set
	
	public ArrayList<Double> getListOfWeightOut(){
		return this.listOfWeightOut;
	}
	
	public ArrayList<Double> getListOfWeightIn(){
		return this.listOfWeightIn;
	}
	
	public void setListOfWeightIn(ArrayList<Double> al){
		this.listOfWeightIn=al;
	}
	
	public void setListOfWeightOut(ArrayList<Double> al){
		this.listOfWeightOut=al;
	}
}
