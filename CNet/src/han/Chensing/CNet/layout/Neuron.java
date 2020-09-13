package han.Chensing.CNet.layout;
import java.util.*;
import han.Chensing.CNet.*;
import java.io.*;

public class Neuron implements Serializable
{
	private ArrayList<Weight> inputWeights=new ArrayList<>();
	private ArrayList<Weight> outputWeights=new ArrayList<>();
	private Function function;
	private double error;
	private double output;
	private double sens;
	
	public void setInputWeights(ArrayList<Weight> inputWeights){
		this.inputWeights=inputWeights;
	}
	
	public void setOutputWeights(ArrayList<Weight> outputWeights){
		this.outputWeights=outputWeights;
	}
	
	public void setOutput(double output){
		this.output=output;
	}
	
	public void setFunction(Function function){
		this.function=function;
	}
	
	public void setError(double error){
		this.error=error;
	}
	
	public void setSens(double sens){
		this.sens=sens;
	}
	
	public ArrayList<Weight> getInputWeights(){
		return inputWeights;
	}
	
	public ArrayList<Weight> getOutputWeights(){
		return outputWeights;
	}
	
	public double getOutput(){
		return output;
	}
	
	public Function getFunction(){
		return function;
	}
	
	public double getError(){
		return error;
	}
	
	public double getSens(){
		return sens;
	}
}
