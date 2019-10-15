package han.Chensing.ShenJingChenZH;

public class CNeu {

    private double inputV;  //Input value

    private double outputV; //Output valve

    //TODO Activation function
    private ActivationFunction activationFunction;


    /**
     * Create a CNeu with a value of input value
     * @param input input value
     */
    public CNeu(double input){
        this.inputV=input;
    }

    /**
     * Get the input value
     * @return  input value (Double)
     */
    public double getInputValue(){
        return inputV;
    }

    /**
     * Set the value of input
     * @param inputV the value you want to edit
     */
    public void setInputValue(double inputV){
        this.inputV=inputV;
    }

    /**
     * Get the output value
     * @return output value (Double)
     */
    public double getOutputValue(){
        return outputV;
    }


    /**
     * Set the value of output
     * @param outputV the value of output you want to edit
     */
    public void setOutputValue(double outputV){
        this.outputV=outputV;
    }


    /**
     * Get the AF
     * @return AF
     */
    public ActivationFunction getActivationFunction(){
        return this.activationFunction;
    }


    /**
     * Set the AF
     * @param activationFunction the AF you want tp edit
     */
    public void setActivationFunction(ActivationFunction activationFunction){
        this.activationFunction=activationFunction;
    }

    @Override
    public String toString() {
        return ("[Type:CNeu ; " +
                "input"+getInputValue()+
                "; output" +getOutputValue()+"]");
    }
}
