package han.Chensing.CNet.thro;
import java.io.*;

public class BadDataException extends RuntimeException implements Serializable
{
	public BadDataException(){
		super();
	}
	
	public BadDataException(String message){
		super(message);
	}
}
