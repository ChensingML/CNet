package han.Chensing.ShenJingChenZH;

public class Main {

    public static void main(String[] args) {
        CNeuNetwork cNeuLayers=new CNeuNetwork(new int[]{2,4,6});
        System.out.println(cNeuLayers.toString());
    }

    public static double leakyrelu(double x){
        return Math.max(x*0.01,x);
    }
}
