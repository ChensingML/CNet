
import java.util.*;

public class InputLayer extends Layer
{
	public InputLayer initLayer(InputLayer la){
		ArrayList<Double> temp=new ArrayList<Double>();			//暂存神经元的输入权值表
		ArrayList<Neutral> neutrals=new ArrayList<Neutral>();	//即将被存进Layer的神经元表
		for(int i=0;i<la.getNumberOfNeuronsOnLayer();i++){
			/*
			 *	设置一个i为0
			 *	遍历InputLayer(Layer)的神经元表，以用户设置的大小为准
			 *	直到遍历结束
			 */
			 Neutral n=new Neutral();	//创建一个神经元
			 
			 temp.add(n.initNeutral());	//用temp一个伪随机实数，作为这个神经元的权值
			 
			 n.setListOfWeightIn(temp);	//设置这个神经元的权值表为temp，即这个伪随机实数
			 
			 neutrals.add(n);			//把这个神经元加到神经元表里
			 
			 temp=new ArrayList<Double>();	//最后，重新创建temp，以供下次使用
		}
		
		la.setListOfNeurons(neutrals);	//循环结束后，把刚刚生成的神经元链表设置到传进来的InputLayer里
		
		return la;						//并返回这个InputLayer
	}

	public void printThisLayer(){
		System.out.println(toString());
	}
	
}
