
public class Main
{
	public static void main(String[] args){
		NeutralNet neutralNet=new NeutralNet();
		neutralNet=neutralNet.initNeutralNet(
				3,
				0,
				0,
				1
		);

		p("初始化后");

		neutralNet.printThisNet();

		NeutralNet trainedNet;

		neutralNet.setTrainSet(
				new double[][]{
						{1,0,0},
						{1,0,1},
						{1,1,0},
						{1,1,1}
				}
		);
		neutralNet.setRealOutputSet(new double[]{0,0,0,1});
		neutralNet.setMaxEpochs(10);
		neutralNet.setTargetError(0.002);
		neutralNet.setLearningRate(1);
		neutralNet.setTrainingTypesENUM(Training.TrainingTypesENUM.PERCEPTRON);
		neutralNet.setActivationFncENUM(Training.ActivationFncENUM.STEP);

		trainedNet=neutralNet.trainNet(neutralNet);

		p("训练后");

		trainedNet.printThisNet();

		p("测试");

		neutralNet.printTrainedNetResult(trainedNet);
	}

	public static void p(CharSequence charSequence){
		System.out.println(charSequence);
	}
}
