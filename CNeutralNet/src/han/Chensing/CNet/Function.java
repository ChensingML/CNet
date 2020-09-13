package han.Chensing.CNet;
import han.Chensing.CNet.util.*;
import java.io.*;

public class Function implements Serializable
{
	private Functions function;
	
	public Function(Functions fun){
		setFunctionType(fun);
	}
	
	public double count(double x){
		return MathUtil.simpleCount(x,function);
	}
	
	public void setFunctionType(Functions fun){
		this.function=fun;
	}
	
	public Functions getFunctionType(){
		return function;
	}

	public enum Functions{
		Sigmoid,Linear
	}
}
