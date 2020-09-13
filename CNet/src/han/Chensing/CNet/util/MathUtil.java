package han.Chensing.CNet.util;
import han.Chensing.CNet.thro.*;
import han.Chensing.CNet.*;

public class MathUtil
{
	
	public static double simpleCount(double x,Function.Functions f){
		switch(f){
			case Sigmoid:{
				return (1.0 / (1.0 + Math.exp(-x)));
			}
			case Linear:{
				return x;
			}
			default:{
				throw new BadFunctionException("抱歉，找不到这个函数呢QAQ");
			}
		}
	}
	
	public static double acCount(double x,Function.Functions fun){
		switch(fun){
			case Sigmoid:{
				return (x * (1.0 - x));
			}
			case Linear:{
				return 1;
			}
			default:{
				throw new BadFunctionException("抱歉，找不到这个函数，或者程序没有为这个函数实现导数计算QAQ");
			}
		}
	}
}
