package han.Chensing.ShenJingChenZH;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CNeuNetwork implements Iterable<CNeuLayer> {

    private List<CNeuLayer> allCLay;

    private int layerNumbers;

    public CNeuNetwork(List<CNeuLayer> allCLay){
        this.allCLay=allCLay;
        this.layerNumbers=allCLay.size();
    }

    public CNeuNetwork(int[] allCNeuLayerCount){
        allCLay=new ArrayList<CNeuLayer>(allCNeuLayerCount.length);
        for (int i = 0; i < allCNeuLayerCount.length; i++) {
            int nc=allCNeuLayerCount[i];
            CNeuLayer cNeus=new CNeuLayer(nc);
            allCLay.add(cNeus);
        }
        layerNumbers=allCLay.size();
    }

    public int size(){
        return this.layerNumbers;
    }

    public List<CNeuLayer> getAllCLay(){
        return this.allCLay;
    }

    public void setAllCLay(List<CNeuLayer> allCLay){
        this.allCLay=allCLay;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("[");
        stringBuilder.append("\n");
        stringBuilder.append("Type:CNetwork");
        for (int i = 0; i < layerNumbers; i++) {
            stringBuilder.append("第" + i + "层神经元个数：" + allCLay.get(i).size() + "\n");
        }
        for (int i = 0; i < layerNumbers; i++) {
            stringBuilder.append(allCLay.get(i).toString() + "\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public Iterable<CNeuLayer> reverse(){
        return new Iterable<CNeuLayer>() {
            @Override
            public Iterator<CNeuLayer> iterator() {
                return new Iterator<CNeuLayer>() {
                    private int lastIndex=layerNumbers-1;
                    @Override
                    public boolean hasNext() {
                        return lastIndex>=0;
                    }

                    @Override
                    public CNeuLayer next() {
                        return allCLay.get(lastIndex--);
                    }

                    @Override
                    public void remove() {

                    }
                };
            }
        };
    }


    @Override
    public Iterator<CNeuLayer> iterator() {
        return new Iterator<CNeuLayer>() {

            private int index=0;

            @Override
            public boolean hasNext() {
                return index<layerNumbers;
            }

            @Override
            public CNeuLayer next() {
                return allCLay.get(index++);
            }

            @Override
            public void remove() {

            }
        };
    }


}
