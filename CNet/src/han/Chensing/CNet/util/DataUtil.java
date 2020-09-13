package han.Chensing.CNet.util;
import han.Chensing.CNet.learn.*;
import java.io.*;
import java.util.*;

public class DataUtil
{
	public static Train.TrainData loadSetFromFile(String path,String spi,int ig) throws IOException{
		File f=new File(path);
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		ArrayList<String> lines=new ArrayList<>();
		String line;
		int len=0;
		while((line=br.readLine())!=null){
			lines.add(line);
			len++;
		}
		line=lines.get(0);
		String[] sptemp=line.split(";");
		double[][] trainSet=new double[len][sptemp[0].split(spi).length-ig];
		double[][] wantSet=new double[len][sptemp[1].split(spi).length];
		int i=0;
		for(String s:lines){
			String[] set,want;
			String[] sp=s.split(";");
			set=sp[0].split(spi);
			want=sp[1].split(spi);
			double[] dtemp=new double[set.length-ig];
			for(int j=0;j!=set.length-ig;j++)
				dtemp[j]=Double.parseDouble(set[j+ig]);
			trainSet[i]=dtemp;
			double[] wtemp=new double[want.length];
			for(int j=0;j!=want.length;j++)	
				wtemp[j]=Double.parseDouble(want[j]);
			wantSet[i]=wtemp;
			i++;
		}
		Train.TrainData data=new Train.TrainData();
		data.trainSet=trainSet;
		data.wantsOutput=wantSet;
		return data;
	}
}
