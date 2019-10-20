
import java.util.*;

public class NeutralNet
{
	//一般属性
	private InputLayer inputLayer;
	private HiddenLayer hiddenLayer;
	private ArrayList<HiddenLayer> listOfHiddenLayer;
	private OutputLayer outputLayer;

	//训练属性
	private double[][] trainSet;//训练集合
	private double[] realOutputSet;//目标输出集合
	private int maxEpochs;//训练周期的次数
	private double learningRate;//学习率
	private double targetError;//目标误差
	private double trainingError;//训练误差
	private Training.TrainingTypesENUM trainingTypesENUM;//训练类型枚举
	private Training.ActivationFncENUM activationFncENUM;//激活函数枚举
	private ArrayList<Double> listOfMSE=new ArrayList<>();//每次训练的均方误差的列表

	public NeutralNet initNeutralNet(
		int numberOfInputNeutrals,			//输入层的神经元数量
		int numberOfHiddenLayers,			//隐藏层的数量
		int numberOfNeutralInHiddenLayers,	//每个隐藏层的神经元数量
		int numberOfOutputNeutrals)			//输出层的神经元数量
	{
		inputLayer=new InputLayer();//创建输入层
		inputLayer.setNumberOfNeuronsInLayer(numberOfInputNeutrals);//设置输入层的神经元数量

		listOfHiddenLayer=new ArrayList<>();//创建隐藏层列表
		for(int i=0;i<numberOfHiddenLayers;i++){//创建目标数量的隐藏层
			hiddenLayer=new HiddenLayer();//新建隐藏层
			hiddenLayer.setNumberOfNeuronsInLayer(numberOfNeutralInHiddenLayers);//设置隐藏层数量
			listOfHiddenLayer.add(hiddenLayer);//将该隐藏层加入隐藏层列表
		}

		outputLayer=new OutputLayer();//添加输出层
		outputLayer.setNumberOfNeuronsInLayer(numberOfOutputNeutrals);//设置输出层的神经元数量

		inputLayer=inputLayer.initLayer(inputLayer);//初始化输入层

		if(numberOfHiddenLayers>0){//如果有隐藏层
			listOfHiddenLayer=hiddenLayer.initLayer(
					hiddenLayer,
					listOfHiddenLayer,
					inputLayer,
					outputLayer);//那就初始化隐藏层
		}

		outputLayer=outputLayer.initLayer(outputLayer);//初始化输出层

		NeutralNet neutralNet=new NeutralNet();//创建神经网络，准备返回
		neutralNet.setInputLayer(inputLayer);//设置输入层
		neutralNet.setHiddenLayer(hiddenLayer);//设置隐藏层
		neutralNet.setListOfHiddenLayer(listOfHiddenLayer);//设置隐藏层列表
		neutralNet.setOutputLayer(outputLayer);//设置输出层

		return neutralNet;//返回神经网络
	}

	public NeutralNet trainNet(NeutralNet neutralNet){
		NeutralNet neutralNet1;

		switch (neutralNet.trainingTypesENUM){
			case PERCEPTRON:
				Perceptron perceptron=new Perceptron();
				neutralNet1= perceptron.train(neutralNet);
				return neutralNet1;
			default:
				throw new NullPointerException("No this enum");
		}
	}

	public void printThisNet(){
		System.out.println(toString());
	}

	public void printTrainedNetResult(NeutralNet neutralNet){
		switch (neutralNet.trainingTypesENUM){
			case PERCEPTRON:
				Perceptron perceptron=new Perceptron();
				perceptron.printTrainedNetResult(neutralNet);
				break;
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("***输入层***");
		stringBuilder.append("\n");
		stringBuilder.append(getInputLayer().toString());
		stringBuilder.append("\n\n");
		stringBuilder.append("***隐藏层***");
		stringBuilder.append("\n");
		int len=getListOfHiddenLayer().size();
		for(int i=0;i!=len;i++){
			stringBuilder.append("第");
			stringBuilder.append(i);
			stringBuilder.append("层");
			stringBuilder.append("\n");
			stringBuilder.append(getListOfHiddenLayer().get(i).toString());
			stringBuilder.append("\n\n");
		}
		stringBuilder.append("\n\n");
		stringBuilder.append("***输出层***");
		stringBuilder.append("\n");
		stringBuilder.append(getOutputLayer().toString());
		return stringBuilder.toString();
	}

	public ArrayList<Double> getListOfMSE() {
		return listOfMSE;
	}

	public HiddenLayer getHiddenLayer() {
		return hiddenLayer;
	}

	public InputLayer getInputLayer() {
		return inputLayer;
	}

	public ArrayList<HiddenLayer> getListOfHiddenLayer() {
		return listOfHiddenLayer;
	}

	public OutputLayer getOutputLayer() {
		return outputLayer;
	}

	public double[] getRealOutputSet() {
		return realOutputSet;
	}

	public double[][] getTrainSet() {
		return trainSet;
	}

	public int getMaxEpochs() {
		return maxEpochs;
	}

	public Training.ActivationFncENUM getActivationFncENUM() {
		return activationFncENUM;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public double getTargetError() {
		return targetError;
	}

	public Training.TrainingTypesENUM getTrainingTypesENUM() {
		return trainingTypesENUM;
	}

	public double getTrainingError() {
		return trainingError;
	}

	public void setTargetError(double targetError) {
		this.targetError = targetError;
	}

	public void setHiddenLayer(HiddenLayer hiddenLayer) {
		this.hiddenLayer = hiddenLayer;
	}

	public void setInputLayer(InputLayer inputLayer) {
		this.inputLayer = inputLayer;
	}

	public void setListOfHiddenLayer(ArrayList<HiddenLayer> listOfHiddenLayer) {
		this.listOfHiddenLayer = listOfHiddenLayer;
	}

	public void setOutputLayer(OutputLayer outputLayer) {
		this.outputLayer = outputLayer;
	}

	public void setMaxEpochs(int maxEpochs) {
		this.maxEpochs = maxEpochs;
	}

	public void setRealOutputSet(double[] realOutputSet) {
		this.realOutputSet = realOutputSet;
	}

	public void setTrainSet(double[][] trainSet) {
		this.trainSet = trainSet;
	}

	public void setActivationFncENUM(Training.ActivationFncENUM activationFncENUM) {
		this.activationFncENUM = activationFncENUM;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public void setTrainingTypesENUM(Training.TrainingTypesENUM trainingTypesENUM) {
		this.trainingTypesENUM = trainingTypesENUM;
	}

	public void setListOfMSE(ArrayList<Double> listOfMSE) {
		this.listOfMSE = listOfMSE;
	}

	public void setTrainingError(double trainingError) {
		this.trainingError = trainingError;
	}
}
