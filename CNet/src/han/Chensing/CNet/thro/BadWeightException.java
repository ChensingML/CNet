package han.Chensing.CNet.thro;
import java.io.*;

public class BadWeightException extends RuntimeException implements Serializable
{
	public BadWeightException(){
		
	}
	
	public BadWeightException(String message){
		super(message);
	}
}
