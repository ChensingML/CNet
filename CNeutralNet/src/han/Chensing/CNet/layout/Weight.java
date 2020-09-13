package han.Chensing.CNet.layout;
import java.util.*;
import java.io.*;

public class Weight implements Serializable
{
	private double value;
	private Neuron leftNeuron;
	private Neuron rightNeuron;
	
	public Weight(double value){
		this.value=value;
	}
	
	public Weight(double value,Neuron leftNeuron,Neuron rightNeuron){
		this.value=value;
		this.leftNeuron=leftNeuron;
		this.rightNeuron=rightNeuron;
	}
	
	public Weight(Neuron leftNeuron,Neuron rightNeuron){
		this.leftNeuron=leftNeuron;
		this.rightNeuron=rightNeuron;
		this.value=new Random().nextDouble();
	}
	
	public void setValue(double value){
		this.value=value;
	}
	
	public void setLeftNeuron(Neuron leftNeuron){
		this.leftNeuron=leftNeuron;
	}
	
	public void setRightNeuron(Neuron rightNeuron){
		this.rightNeuron=rightNeuron;
	}
	
	public double getValue(){
		return value;
	}
	
	public Neuron getLeftNeuron(){
		return leftNeuron;
	}
	
	public Neuron getRightNeuron(){
		return rightNeuron;
	}
}
