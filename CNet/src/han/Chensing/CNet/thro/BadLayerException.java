package han.Chensing.CNet.thro;
import java.io.*;

public class BadLayerException extends RuntimeException implements Serializable
{
	public BadLayerException(){
		super();
	}
	public BadLayerException(String message){
		super(message);
	}
}
