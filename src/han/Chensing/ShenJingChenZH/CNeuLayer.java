package han.Chensing.ShenJingChenZH;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CNeuLayer implements Iterable<CNeu> {

    private List<CNeu> allCNeu;

    private int cnuNumber=0;

    public CNeuLayer(List<CNeu> allCNeu){
        this.allCNeu=allCNeu;
        this.cnuNumber=allCNeu.size();
    }

    public CNeuLayer(int cnuNumber){
        this.allCNeu=new LinkedList<CNeu>();
        for (int i = 0; i < cnuNumber; i++) {
            CNeu cNeu=new CNeu(0.0);
            this.allCNeu.add(cNeu);
        }
        this.cnuNumber=allCNeu.size();
    }

    public int size(){
        return cnuNumber;
    }

    public List<CNeu> getAllCNeu(){
        return this.allCNeu;
    }

    public void setAllCNeu(List<CNeu> allCNeu){
        this.allCNeu=allCNeu;
    }

    @Override
    public String toString() {
        String p="[";
        for (int i = 0; i < size(); i++) {
            p+=allCNeu.get(i).toString();
            if (i<size()-1){
                p+=",";
            }
        }
        p+="]";
        return p;
    }

    @Override
    public Iterator<CNeu> iterator() {
        return new Iterator<CNeu>() {

            private int index=0;

            @Override
            public boolean hasNext() {
                return index<allCNeu.size();
            }

            @Override
            public CNeu next() {
                return allCNeu.get(index++);
            }

            @Override
            public void remove() {

            }
        };
    }
}
