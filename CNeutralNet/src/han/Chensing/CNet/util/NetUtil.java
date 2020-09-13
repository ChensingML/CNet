package han.Chensing.CNet.util;
import han.Chensing.CNet.*;
import han.Chensing.CNet.layout.*;
import java.util.*;

public class NetUtil
{
	public static void createNet(CNeutralNet net){
		//TODO 数据有效检测
		CNeutralNet.NetConfiguration config=net.getNetConfiguration();
		InputLayer inp=new InputLayer();
		OutputLayer outp=new OutputLayer();
		ArrayList<HiddenLayer> hiddenLayersArray=new ArrayList<>();
		HiddenLayers hiddenLayers=new HiddenLayers();
		int numberOfHiddenLayers=config.numberOfHiddenLayer;
		
		//创建神经元
		Log.i("Creating Neurons in InputLayer");
		LayerUtil.addNeurons(inp,config.inputLayerNeurons);
		Log.i("Creating Neurons in OutputLayer");
		LayerUtil.addNeurons(outp,config.outputLayerNeurons);
		for(int i=0;i!=config.numberOfHiddenLayer;i++){
			Log.i("Creating Neurons in HiddenLayer #"+i);
			HiddenLayer hl=new HiddenLayer();
			hl.setNum(i);
			LayerUtil.addNeurons(hl,config.hiddenLayerNeurons[i]);
			hiddenLayersArray.add(hl);
		}
		//连接神经元
		Log.i("Connecting weights");
		if(numberOfHiddenLayers==0){//无隐藏层
			LayerUtil.connectLayers(inp,outp);
		}else{//有隐藏层
			LayerUtil.connectLayers(inp,hiddenLayersArray.get(0));
			for(int i=0;i!=numberOfHiddenLayers;i++){
				if(i==numberOfHiddenLayers-1){
					LayerUtil.connectLayers(hiddenLayersArray.get(i),outp);
					break;
				}
				LayerUtil.connectLayers(hiddenLayersArray.get(i),hiddenLayersArray.get(i+1));
			}
		}
		//设置层
		hiddenLayers.setLayers(hiddenLayersArray);
		net.setHiddenLayers(hiddenLayers);
		net.setInputLayer(inp);
		net.setOutputLayer(outp);
	}
	
	/**
	 *	更改这个网络所有神经元的激活函数
	 */
	public static void changeAllFunction(CNeutralNet net,Function fun){
		//TODO 数据有效检测&是否创建
		if(net.getNetConfiguration().numberOfHiddenLayer>0){
			for(HiddenLayer h:net.getHiddenLayers().getLayers()){
				LayerUtil.changeFunction(h,fun);
			}
		}
		LayerUtil.changeFunction(net.getInputLayer(),fun);
		LayerUtil.changeFunction(net.getOutputLayer(),fun);
	}
}
