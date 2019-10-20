
import java.util.*;

public abstract class Layer
{
	private ArrayList<Neutral> listOfNeurons;	//神经元的链表
	private int numberOfNeuronsInLayer;			//记录神经元的数量
	
	
	//双get双set
	
	public ArrayList<Neutral> getListOfNeurons(){
		return listOfNeurons;
	}
	
	public int getNumberOfNeuronsOnLayer(){
		return numberOfNeuronsInLayer;
	}
	
	public void setListOfNeurons(ArrayList<Neutral> al){
		this.listOfNeurons=al;
	}

	public void setNumberOfNeuronsInLayer(int i){
		this.numberOfNeuronsInLayer=i;
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		int i=1;
		for(Neutral n:getListOfNeurons()){
			sb.
				append("\n").
				append(i).
				append("号神经元：\n");
			if(n.getListOfWeightIn()!=null){
				sb.append("输入权值：\n");
				for(double wei:n.getListOfWeightIn()){
					sb.
						append("[").
						append(wei).
						append("],\n");
				}
			}else{
				sb.append("输入权值不存在\n");
			}

			if(n.getListOfWeightOut()!=null){
				sb.append("输出权值：\n");
				for(double wei:n.getListOfWeightOut()){
					sb.
						append("[").
						append(wei).
						append("],\n");
				}
			}else{
				sb.append("输出权值不存在\n");
			}

			i++;
		}
		return sb.toString();
	}
}
