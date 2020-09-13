package han.Chensing.CNet;
import han.Chensing.CNet.layout.*;
import java.util.*;
import han.Chensing.CNet.util.*;
import han.Chensing.CNet.learn.*;
import java.io.*;
import java.math.*;
import java.text.*;

public class CNeutralNet implements Serializable
{
	private NetConfiguration configuration;
	private InputLayer inputLayer;
	private OutputLayer outputLayer;
	private HiddenLayers hiddenLayers;
	
	public static CNeutralNet getNet(byte[] file)throws IOException,ClassNotFoundException{
		ByteArrayInputStream bais=new ByteArrayInputStream(file);
		ObjectInputStream ois=new ObjectInputStream(bais);
		return (CNeutralNet)ois.readObject();
	}
	
	protected CNeutralNet(){}
	
	@Deprecated
	public CNeutralNet(byte[] file) throws IOException,ClassNotFoundException{
		CNeutralNet n=getNet(file);
		this.inputLayer=n.getInputLayer();
		this.outputLayer=n.outputLayer;
		this.hiddenLayers=n.hiddenLayers;
		this.configuration=n.configuration;
	}
	
	public void setNetCongratulate(NetConfiguration netConfiguration){
		this.configuration=netConfiguration;
	}
	
	public void setInputLayer(InputLayer inputLayer){
		this.inputLayer=inputLayer;
	}
	
	public void setOutputLayer(OutputLayer outputLayer){
		this.outputLayer=outputLayer;
	}
	
	public void setHiddenLayers(HiddenLayers hiddenLayers){
		this.hiddenLayers=hiddenLayers;
	}
	
	public NetConfiguration getNetConfiguration(){
		return configuration;
	}
	
	public InputLayer getInputLayer(){
		return inputLayer;
	}
	
	public OutputLayer getOutputLayer(){
		return outputLayer;
	}

	public HiddenLayers getHiddenLayers(){
		return hiddenLayers;
	}
	
	public void CREATE(){
		Log.i("Start Creating");
		NetUtil.createNet(this);
		Log.i("Create successfully");
	}
	
	public void setAllFunctions(Function.Functions fun){
		NetUtil.changeAllFunction(this,new Function(fun));
		Log.i("Function applied successfully");
	}
	
	public void TRAIN(Train.TrainData data){
		new Backpropagation().TRAIN(this,data);
	}
	
	public void TestAndPrint(Train.TrainData data){
		Backpropagation bp=new Backpropagation();
		DecimalFormat fo=new DecimalFormat("0.0000000001");
		for(int i=0;i!=data.testSet.length;i++){
			double[] outp=bp.TEST(this,data,i);
			String str="[ ";
			for(double d:outp){
				str+=fo.format(d)+" ";
			}
			str+="]";
			Log.i(str);
		}
		Log.i("Note: Results keep 10 decimal places");
	}
	
	public byte[] getNetBytes() throws IOException{
		ByteArrayOutputStream bios=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bios);
		oos.writeObject(this);
		return bios.toByteArray();
	}
	
	public static class NetConfiguration implements Serializable{
		public NetConfiguration(){}
		public int numberOfHiddenLayer=1;
		public int[] hiddenLayerNeurons=new int[]{99999};
		public int inputLayerNeurons=2;
		public int outputLayerNeurons=1;
	}
}
