package han.Chensing.CNet.layout;
import java.util.*;
import java.io.*;

public abstract class Layer implements Serializable
{
	private ArrayList<Neuron> neurons=new ArrayList<>();
	
	public void setNeurons(ArrayList<Neuron> neurons){
		this.neurons=neurons;
	}
	
	public ArrayList<Neuron> getNeurons(){
		return neurons;
	}
	
	public abstract LayerType getLayerType();
	
	public enum LayerType{
		INPUT_LAYER,HIDDEN_LAYER,OUTPUT_LAYER
	}
}

