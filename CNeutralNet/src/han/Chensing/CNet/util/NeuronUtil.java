package han.Chensing.CNet.util;
import han.Chensing.CNet.layout.*;
import han.Chensing.CNet.thro.*;
import java.util.*;

public class NeuronUtil
{
	/**
	 *	注意
	 *	本方法是以判断输入权值和输出权值
	 *	链表是否为null来判断神经元
	 *	所在的层的
	 */
	public static Layer.LayerType findLayer(Neuron neuron){
		if(neuron.getInputWeights()==null)
			return Layer.LayerType.INPUT_LAYER;
		if(neuron.getOutputWeights()==null)
			return Layer.LayerType.OUTPUT_LAYER;
		if(neuron.getInputWeights()!=null&&
		   neuron.getOutputWeights()!=null){
			return Layer.LayerType.HIDDEN_LAYER;
		}
		throw new BadLayerException("嘤嘤嘤，这个层不符合逻辑QAQ");
	}

	/**
	 *	连接参数神经元的输出权值和
	 *	参数层所有神经元的输入权值 
	 *	所有权值以随机数赋值
	 */
	public static void connectOutAndIn(Neuron neuron,Layer layer){
		if(NeuronUtil.findLayer(neuron)==Layer.LayerType.OUTPUT_LAYER){
			throw new BadWeightException("输出层神经元不能有输出权值诶QAQ");
		}
		if(layer.getLayerType()==Layer.LayerType.INPUT_LAYER){
			throw new BadWeightException("不能向输入层的神经元链接输入权值诶QAQ");
		}
		ArrayList<Neuron> neuronInLayer=layer.getNeurons();
		for(Neuron n:neuronInLayer){
			Weight w=new Weight(neuron,n);
			n.getInputWeights().add(w);
			neuron.getOutputWeights().add(w);
		}
	}
}
