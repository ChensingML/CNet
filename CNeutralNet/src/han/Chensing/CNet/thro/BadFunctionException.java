package han.Chensing.CNet.thro;
import java.io.*;
public class BadFunctionException extends RuntimeException implements Serializable
{
	public BadFunctionException(){
		super();
	}
	public BadFunctionException(String message){
		super(message);
	}
}
