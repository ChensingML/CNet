package han.Chensing.CNet;
import han.Chensing.CNet.layout.*;
import han.Chensing.CNet.learn.*;
import han.Chensing.CNet.util.*;
import java.io.*;

public class TestMain{
	public static void main(String[] args){
		CNeutralNet n=new CNeutralNet();
		CNeutralNet.NetConfiguration config=new CNeutralNet.NetConfiguration();
		config.inputLayerNeurons=4;
		config.outputLayerNeurons=3;
		config.numberOfHiddenLayer=2;
		config.hiddenLayerNeurons=new int[]{4,3};
		n.setNetCongratulate(config);
		n.CREATE();
		n.setAllFunctions(Function.Functions.Sigmoid);
		LayerUtil.changeFunction(n.getOutputLayer(),new Function(Function.Functions.Linear));
		Train.TrainData data=null;
		try{
			data=DataUtil.loadSetFromFile("/storage/emulated/0/BaiduNetdisk/iris.csv",",",1);
		}catch(Exception e){
			e.printStackTrace();
		}
		assert data==null;
		data.trainTimes=3000;
		data.lambda=0.01;
		data.wantsMse=0;
		data.testSet=new double[][]{
			{5.0,3.3,1.4,0.2},
			{5.7,2.8,4.1,1.3},
			{5.9,3.0,5.1,1.8}
		};
		data.testWantsOutput=new double[][]{
			{1,0,0},
			{0,1,0},
			{0,0,1}
		};
		n.TRAIN(data);
		n.TestAndPrint(data);
		try{
			byte[] bs=n.getNetBytes();
			File f=new File("/storage/emulated/0/AppProjects/Cnet.net");
			FileOutputStream fos=new FileOutputStream(f);
			fos.write(bs);
			fos.flush();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
