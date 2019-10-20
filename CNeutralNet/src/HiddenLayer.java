
import java.util.*;

public class HiddenLayer extends Layer
{
	public ArrayList<HiddenLayer> initLayer(
		HiddenLayer hiddenLayer,					//要被初始化的HiddenLayer
		ArrayList<HiddenLayer> listOfHiddenLayer,	//要被初始化的HiddenLayer链表
		InputLayer inputLayer,						//这个神经网络的InputLayer
		OutputLayer outputLayer						//这个神经网络的OutputLayer
	){
		
		ArrayList<Double> listOfWeightIn=new ArrayList<Double>();	//在下文用来保存输入权值的链表
		ArrayList<Double> listOfWeightOut=new ArrayList<Double>();	//在下文用来保存输出权值的链表
		ArrayList<Neutral> listOfNeutral=new ArrayList<>();			//在下文用来保存即将加入到隐藏层里的神经元
		
		int numberOfHiddenLayer=listOfHiddenLayer.size();//隐藏层链表中的隐藏层的数量
		
		for(int i=0;i<numberOfHiddenLayer;i++){	//遍历整个隐藏层(HiddenLayer)链表的所有隐藏层(HiddenLayer)
			for(int j=0;j<hiddenLayer.getNumberOfNeuronsOnLayer();j++){	//然后遍历这些隐藏层(HiddenLayer)的所有神经元
				Neutral n=new Neutral();	//新建一个神经元
				
				int numberIn;	//作为神经元的输入神经元数量
				int numberOut;	//作为神经元的输出神经元数量
				
				if(i==0){//当遍历到隐藏层列表的第一层时
					numberIn=inputLayer.getNumberOfNeuronsOnLayer();//输入神经元数量就是输入层的数量，因为没有上一层隐藏层了，再上一层就是输入层
					if(numberOfHiddenLayer>1){//如果隐藏层不止一层
						numberOut=listOfHiddenLayer.get(i+1/*下一层*/).getNumberOfNeuronsOnLayer();//那么输出神经元数量是下一层神经元的数量
					}else{//如果神经元只有一层(或更少)
						numberOut=listOfHiddenLayer.get(i/*本层*/).getNumberOfNeuronsOnLayer();//那么输出神经元数量就是这一层神经元的数量
					}
				}else if(i==numberOfHiddenLayer-1){//当遍历到隐藏层列表的最后一层时
					numberIn=listOfHiddenLayer.get(i-1/*上一层*/).getNumberOfNeuronsOnLayer();//那么输入神经元数量就是上一层神经元的数量
					numberOut=outputLayer.getNumberOfNeuronsOnLayer();//输出神经元的数量就是输出层的数量，因为没有下一层隐藏层了，再下一层就是输出层
				}else{//当遍历到隐藏层列表中间（即不是第一层，也不是最后一层）时
					numberIn=listOfHiddenLayer.get(i-1).getNumberOfNeuronsOnLayer();//输入神经元的数量就是上一层隐藏层的神经元数量
					numberOut=listOfHiddenLayer.get(i+1).getNumberOfNeuronsOnLayer();//同理，输出神经元的数量就是下一层隐藏层的神经元数量
				}
				for(int k=0;k<numberIn;k++){//遍历神经元，将伪随机数初始化到神经元中，这个循环是初始化神经元的输入值
					listOfWeightIn.add(n.initNeutral());
				}
				for(int k=0;k<numberOut;k++){//遍历神经元，同上，不过这个循环是初始化神经元的输入权值的
					listOfWeightOut.add(n.initNeutral());
				}
				
				n.setListOfWeightIn(listOfWeightIn);	//设置这个神经元的输入权值
				n.setListOfWeightOut(listOfWeightOut);	//设置这个神经元的输出权值
				
				listOfNeutral.add(n);					//将这个神经元加入到列表中
				
				listOfWeightIn=new ArrayList<>();		//重新设置输入权值表
				listOfWeightOut=new ArrayList<>();		//重新设置输出权值表
			}
			
			listOfHiddenLayer.get(i).setListOfNeurons(listOfNeutral);//将保存神经元的列表设置到这个隐藏层中
			
			listOfHiddenLayer=new ArrayList<>();	//清空用来保存神经元的链表
		}
		
		return listOfHiddenLayer;	//最后，返回这个隐藏层列表
	}

	public void printThisLayer(){
		System.out.println(toString());
	}
}
