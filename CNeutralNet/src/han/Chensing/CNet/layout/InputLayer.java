package han.Chensing.CNet.layout;
import java.io.*;

public class InputLayer extends Layer implements Serializable
{

	@Override
	public LayerType getLayerType()
	{
		return LayerType.INPUT_LAYER;
	}
	
}
