
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Training {

    private int epochs;//训练迭代次数
    private double error;//（估计值与真实值比较的）训练误差
    private double mse;//均方误差

    public enum TrainingTypesENUM{//枚举列出了两种训练类型：自适应线性元、感知机
        ADALINE, PERCEPTRON
    }

    public enum ActivationFncENUM{//枚举列出了几种激活函数
        STEP,       //阈值函数
        LINEAR,     //纯线性
        SIGLOG,     //S函数
        HYPERTAN    //双曲正切函数
    }

    public NeutralNet train(NeutralNet neutralNet){

        ArrayList<Double> inputWeightIn;//输入权值

        int rows=neutralNet.getTrainSet().length;//训练集合的行数
        int cols=neutralNet.getTrainSet()[0].length;//训练集合的列数

        while (this.getEpochs()< neutralNet.getMaxEpochs()){//重复执行直到达到训练的迭代次数达到
            double estimatedOutput=0.0;//实际输出
            double realOutput=0.0;//期望输出

            //遍历训练集合
            for (int i=0;i<rows;i++){//遍历行数

                double netValue=0.00;//权值

                for (int j=0;j<cols;j++){//遍历列数
                    inputWeightIn= neutralNet.getInputLayer()/*神经网络的输入层*/
                            .getListOfNeurons().get(j)/*的所有神经的第j个*/
                            .getListOfWeightIn();/*获取这个神经的输入权值表*/
                    double inputWeight=inputWeightIn.get(0);//获取输入权值表的第1个数
                    netValue=netValue+inputWeight* neutralNet.getTrainSet()[i][j];//更新权值（具体原理实在不明白）
                }

                estimatedOutput=activationFnc(neutralNet.getActivationFncENUM(),netValue);//计算函数以获取新权值

                realOutput= neutralNet.getRealOutputSet()[i];//获取期望输出

                setError(realOutput-estimatedOutput);//计算误差

                if (Math.abs(/*绝对值*/getError())> neutralNet.getTargetError()){//如果现在的误差大于目标误差（误差过大）
                    //就重新训练输入层

                    InputLayer inputLayer=new InputLayer();//创建一个输入层
                    inputLayer.setListOfNeurons(
                            teachNeutralOfLayer(//训练这个层
                                    cols,//训练集合的列数作为输入层的神经元数量
                                    i,//在训练集的行数
                                    neutralNet,//将要被更新的神经网络
                                    netValue//权值
                            )
                    );
                    neutralNet.setInputLayer(inputLayer);//重新设置输入层
                }
            }

            setMse(Math.pow(realOutput-estimatedOutput,2.0));//设置均方误差
            neutralNet.getListOfMSE().add(getMse());//将这次均方误差加入神经网络的均方误差列表

            setEpoch(getEpochs()+1);//然后增加一次（已）迭代次数
        }

        neutralNet.setTrainingError(getError());//设置神经网络的误差为当前训练误差

        return neutralNet;//最后，返回神经网络
    }

    private ArrayList<Neutral> teachNeutralOfLayer(
            int numberOfInputNeurons,   //输入层的神经元数量
            int line,                   //训练集的行数（第几行）
            NeutralNet neutralNet,      //将要被更新的神经网络
            double netValue             //权值
    ){
        ArrayList<Neutral> listOfNeutrals=new ArrayList<>();//神经元列表
        ArrayList<Double> inputWeightsInNew=new ArrayList<>();//新权值列表
        ArrayList<Double> inputWeightInOld;//老权值列表

        for (int i=0;i<numberOfInputNeurons;i++){//重复执行直到目标神经元数量遍历完
            inputWeightInOld= neutralNet.getInputLayer()/*获取原来的输入层*/.
                    getListOfNeurons()/*的神经元列表*/
                    .get(i)/*的第i项*/
                    .getListOfWeightIn()/*的输入权值列表*/;
            double inputWeightOld=inputWeightInOld.get(0);//获取老输入权值


            inputWeightsInNew.add(  /*在新权值列表里添加*/
                calcNewWeight(//计算新权值
                        neutralNet.getTrainingTypesENUM(),//训练类型
                        inputWeightOld,//老权值
                        neutralNet,//神经网络
                        getError(),//误差
                        neutralNet.getTrainSet()[line][i],//训练集的第line行的第i列
                        netValue//权值
                )
            );

            Neutral neutral=new Neutral();//创建新神经元
            neutral.setListOfWeightIn(inputWeightsInNew);//将这个新权值表设为这个神经元的权值表
            listOfNeutrals.add(neutral);//然后将这个神经元加入神经元列表
            inputWeightsInNew=new ArrayList<>();//清空新权值表
        }

        return listOfNeutrals;//最后，返回神经元列表
    }

    private double calcNewWeight(
            TrainingTypesENUM trainingTypesENUM,    //计算类型
            double inputWeightOld,  //老权值
            NeutralNet neutralNet,  //神经网络
            double error,           //误差值
            double trainSample,     //训练样本
            double netValue         //
    ){
        switch (trainingTypesENUM){
            case PERCEPTRON:
                return inputWeightOld+ neutralNet.getLearningRate()*error*trainSample;
            case ADALINE:
                return inputWeightOld+ neutralNet.getLearningRate()*error*trainSample*derivativeActivationFnc(neutralNet.getActivationFncENUM(),netValue);
            default:
                throw new NullPointerException("No this enum");
        }
    }

    public double derivativeActivationFnc(ActivationFncENUM activationFncENUM,double value){//计算激活函数的导数
        switch (activationFncENUM){
            case LINEAR:
                return derivativeFncLinear();
            case HYPERTAN:
                return derivativeFncHyperTan(value);
            case SIGLOG:
                return derivativeFncSigLog(value);
            default:
                throw new NullPointerException("No this function");
        }
    }

    public double activationFnc(ActivationFncENUM activationFncENUM,double value){//计算激活函数
        switch (activationFncENUM){
            case STEP:
                return fncStep(value);
            case LINEAR:
                return fncLinear(value);
            case SIGLOG:
                return fncSigLog(value);
            case HYPERTAN:
                return fncHyperTan(value);
            default:
                throw new NullPointerException("No this enum");
        }
    }

    public void printTrainedNetResult(NeutralNet neutralNet){

        int rows=neutralNet.getTrainSet().length;//获取训练集合的行数
        int cols=neutralNet.getTrainSet()[0].length;//获取训练集合的列数

        ArrayList<Double> inputWeightIn=new ArrayList<>();

        for (int i=0;i<rows;i++){//遍历训练集合的行数

            double netValue=0;//权值

            for (int j=0;j<cols;j++){//遍历每个训练集合的行数的每一列
                inputWeightIn=neutralNet//获取神经网络的
                /*输入层*/             .getInputLayer()
                /*的神经元列表*/        .getListOfNeurons()
                /*的第j位*/            .get(j)
                /*的输入权值*/         .getListOfWeightIn();

                double inputWeight=inputWeightIn.get(0);//输入权值
                netValue=netValue+inputWeight*neutralNet.getTrainSet()[i][j];//计算权值
                p(neutralNet.getTrainSet()[i][j]+"\t");
            }

            double estimatedOutput=activationFnc(neutralNet.getActivationFncENUM(),netValue);//实际输出

            p("网络输出："+estimatedOutput+"\t");
            p("期望输出："+neutralNet.getRealOutputSet()[i]+"\t");

            double error=estimatedOutput-neutralNet.getRealOutputSet()[i];//误差
            p("误差："+error+"\n");
        }
    }

    public static void p(CharSequence charSequence){
        System.out.println(charSequence);
    }

    private double fncStep(double value){
        if (value>=0){
            return 1.0;
        }
        return 0.0;
    }

    private double fncLinear(double value){
        return value;
    }

    private double fncSigLog(double value){
        return 1.0/(1.0+Math.exp(-value));
    }

    private double fncHyperTan(double value) {
        return Math.tanh(value);
    }

    private double derivativeFncLinear() {
        return 1.0;
    }

    private double derivativeFncSigLog(double value) {
        return value*(1.0-value);
    }

    private double derivativeFncHyperTan(double value) {
        return (1.0 / Math.pow(Math.cosh(value), 2.0));
    }

    public int getEpochs() {
        return epochs;
    }

    public double getMse() {
        return mse;
    }

    public double getError() {
        return error;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }

    public void setError(double error) {
        this.error = error;
    }

    public void setEpoch(int epochs) {
        this.epochs = epochs;
    }
}
