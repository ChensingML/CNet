package han.Chensing.CNet.learn;
import han.Chensing.CNet.*;
import han.Chensing.CNet.layout.*;
import java.util.*;
import han.Chensing.CNet.util.*;
import java.math.*;
import java.io.*;
import han.Chensing.CNet.learn.Train.*;

public class Backpropagation implements Train,Serializable
{

	private CNeutralNet.NetConfiguration config;
	private CNeutralNet net;
	private TrainData data;
	
	private double errorAll=0.0;
	private double[][] trainSet;
	
	private void init(CNeutralNet net,TrainData data){
		config=net.getNetConfiguration();
		this.data=data;
		this.net=net;
		this.trainSet=data.trainSet;
	}
	
	@Override
	public void TRAIN(CNeutralNet net,TrainData data)
	{
		Log.i("START TRAINING");
		init(net,data);
		boolean isBreak=false;
		int every=data.trainTimes/10;
		int k=0;
		System.out.print("CNet:[INFO] Train progress:");
		int time=data.trainTimes+1;
		for(int i=0;i!=time;i++,k++){
			//训练次数循环
			for(int j=0;j!=trainSet.length;j++){
				//训练集每个小集循环
				forward(j,false);//前向传播
				back();
				if(Math.abs(errorAll)<data.wantsMse){
					isBreak=true;
					break;
				}
				errorAll=0;
			}
			if(k==every){
				k=0;
				System.out.print("=");
			}
			if(isBreak)break;
		}
		System.out.println();
		Log.i("Train done");
		double errorAl=0;
		for(Neuron n:net.getOutputLayer().getNeurons()){
			errorAl+=n.getError();
		}
		BigDecimal bd=new BigDecimal(errorAl/config.outputLayerNeurons);
		Log.i("Adv error:"+bd.toPlainString());
	}

	@Override
	public double[] TEST(CNeutralNet net, Train.TrainData data,int po)
	{
		init(net,data);
		forward(po,true);
		double[] outp=new double[config.outputLayerNeurons];
		for(int i=0;i!=outp.length;i++){
			outp[i]=net.getOutputLayer().getNeurons().get(i).getOutput();
		}
		return outp;
	}


	private void forward(int trainSetPo,boolean test){
		if(test)
			TrainUtil.setLayerNeuronOutput(net.getInputLayer(),data.testSet[trainSetPo]);
		else
			TrainUtil.setLayerNeuronOutput(net.getInputLayer(),trainSet[trainSetPo]);
		if(config.numberOfHiddenLayer>0){
			//如果有隐藏层
			ArrayList<HiddenLayer> hiddenLayers=net.getHiddenLayers().getLayers();
			for(HiddenLayer h:hiddenLayers){
				//遍历隐藏层
				ArrayList<Neuron> neurons=h.getNeurons();
				for(Neuron n:neurons){
					//遍历这个隐藏层的神经元
					//计算输出值
					TrainUtil.countOutput(n);
				}
			}
		}
		//计算输出层
		ArrayList<Neuron> opl=net.getOutputLayer().getNeurons();
		int oplNN=opl.size();
		for(int i=0;i!=oplNN;i++){
			Neuron n=opl.get(i);
			TrainUtil.countOutput(n);
			double error;
			if(test)
				error=data.testWantsOutput[trainSetPo][i]-n.getOutput();
			else
				error=data.wantsOutput[trainSetPo][i]-n.getOutput();
			n.setError(error);
			errorAll+=Math.pow(error,2)/2;
		}
	}
	
	private void back(){
		int hiddenS=config.numberOfHiddenLayer;
		ArrayList<Layer> hs=new ArrayList<>();
		hs.addAll(net.getHiddenLayers().getLayers());
		hs.add(net.getOutputLayer());
		if(hiddenS>0){
			for(int i=hiddenS-1;i!=-1;i--){
				//反向遍历隐藏层
				Layer h=hs.get(i);
				ArrayList<Neuron> ns=h.getNeurons();
				for(Neuron n:ns){
					//里面的所有神经元
					ArrayList<Weight> ws=n.getOutputWeights();
					//累加错误
					double temp=0;
					for(Weight w:ws){
						temp+=w.getValue()*w.getRightNeuron().getError();
					}
					n.setError(temp);
				}
			}
		}
		
		//修复权值
		//隐藏层
		ArrayList<Layer> lays=new ArrayList<>();
		if(hiddenS>0){
			lays.addAll(net.getHiddenLayers().getLayers());
		}
		
		//输出层
		lays.add(net.getOutputLayer());
		
		//修复
		for(Layer h:lays){
			ArrayList<Neuron> ns=h.getNeurons();
			for(Neuron n:ns){
				ArrayList<Weight> ws=n.getInputWeights();
				for(Weight w:ws){
					double newW=0;
					newW=w.getValue()+
						data.lambda*
						n.getError()*
						MathUtil.acCount(n.getOutput(),n.getFunction().getFunctionType())*
						w.getLeftNeuron().getOutput();
					w.setValue(newW);
				}
			}
		}
	}
}
