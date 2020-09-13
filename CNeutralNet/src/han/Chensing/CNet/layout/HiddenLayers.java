package han.Chensing.CNet.layout;
import java.util.*;
import java.io.*;

public class HiddenLayers implements Serializable
{
	ArrayList<HiddenLayer> hiddenLayers=new ArrayList<>();
	
	public void setLayers(ArrayList<HiddenLayer> hiddenLayers){
		this.hiddenLayers=hiddenLayers;
	}
	
	public ArrayList<HiddenLayer> getLayers(){
		return hiddenLayers;
	}
}
