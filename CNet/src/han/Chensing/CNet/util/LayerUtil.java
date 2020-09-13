package han.Chensing.CNet.util;
import han.Chensing.CNet.layout.*;
import java.util.*;
import han.Chensing.CNet.thro.*;
import han.Chensing.CNet.*;

public class LayerUtil
{
	/**
	 *	将左边层的神经元与右边层的
	 *	神经元建立全连接
	 */
	public static void connectLayers(Layer leftLayer,Layer rightLayer){
		if(leftLayer.getLayerType()==Layer.LayerType.OUTPUT_LAYER)
			throw new BadLayerException("左边的层不可以是输出层哦QAQ");
		if(rightLayer.getLayerType()==Layer.LayerType.INPUT_LAYER)
			throw new BadLayerException("右边的层不可以是输入层哦QAQ");
		ArrayList<Neuron> neuronInLeftLayer=leftLayer.getNeurons();
		for(Neuron n:neuronInLeftLayer){
			NeuronUtil.connectOutAndIn(n,rightLayer);
		}
	}
	
	/**
	 *	往指定的层内添加指定的神经元
	 */
	public static void addNeurons(Layer layer,int pcs){
		for(int i=0;i!=pcs;i++){
			layer.getNeurons().add(new Neuron());
		}
	}
	
	/**
	 *	更改一个层内所有神经元的激活函数
	 */
	public static void changeFunction(Layer layer,Function function){
		ArrayList<Neuron> neurons=layer.getNeurons();
		for(Neuron n:neurons){
			n.setFunction(function);
		}
	}
}
