package han.Chensing.CNet.layout;
import han.Chensing.CNet.layout.Layer.*;
import java.io.*;

public class HiddenLayer extends Layer implements Serializable
{

	private int num;
	
	@Override
	public Layer.LayerType getLayerType()
	{
		return Layer.LayerType.HIDDEN_LAYER;
	}

	public void setNum(int num){
		this.num=num;
	}
	
	public int getNum(){
		return num;
	}
}
