package han.Chensing.CNet.util;
import han.Chensing.CNet.layout.*;
import java.util.*;
import han.Chensing.CNet.thro.*;

public class TrainUtil
{
	/**
	 *	将一个数组上的数据依次
	 *	设置到某层的神经元上，
	 *	一般用于给输入层设置训练集
	 *	当数据数量与神经元数量不符时报错
	 */
	public static void setLayerNeuronOutput(Layer layer,double[] set){
		int size=set.length;
		ArrayList<Neuron> neurons=layer.getNeurons();
		if(size!=neurons.size())
			throw new BadDataException("数据数量与神经元数量不相等哦，请仔细检查QAQ");
		for(int i=0;i!=size;i++){
			neurons.get(i).setOutput(set[i]);
		}
	}
	
	/**
	 *	本方法现将先计算该神经元的加权总和
	 *	即 前一个神经元的输出*与当前神经元连接的权值+...
	 *	然后根据该神经元的激活函数计算输出
	 *	一定要保证该神经元已经被连接（否则会出大问题 滑稽）
	 */
	public static void countOutput(Neuron neuron){
		ArrayList<Weight> weights=neuron.getInputWeights();
		double jqzh=0;
		for(Weight w:weights){
			jqzh+=w.getLeftNeuron().getOutput()*w.getValue();
		}
		neuron.setOutput(neuron.getFunction().count(jqzh));
	}
}
