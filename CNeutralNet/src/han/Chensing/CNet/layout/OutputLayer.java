package han.Chensing.CNet.layout;
import han.Chensing.CNet.layout.Layer.*;
import java.io.*;

public class OutputLayer extends Layer implements Serializable
{

	@Override
	public Layer.LayerType getLayerType()
	{
		return Layer.LayerType.OUTPUT_LAYER;
	}


}
