package han.Chensing.CNet.learn;
import han.Chensing.CNet.*;
import java.io.*;

public interface Train
{
	//训练一次
	public void TRAIN(CNeutralNet net,TrainData data);
	
	//测试一次
	public double[] TEST(CNeutralNet net,TrainData data,int po);
	
	public static class TrainData{
		public double[][] trainSet;
		public double[][] wantsOutput;
		public double wantsMse;
		public double lambda;//λ 就是学习率啦
		public int trainTimes;
		public double[][] testSet;
		public double[][] testWantsOutput;
	}
}
