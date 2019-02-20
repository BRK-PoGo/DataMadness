package objects;

public class Node {

private String ActFun;
	
	public Node(String ActFun) {
		this.ActFun = ActFun;
	}

	public double ActivationFunction(double input) {
		double output = 0.0;
		if (ActFun == "step") {
			if (input > 0) output = 1.0;
		} else if (ActFun == "sig") {
			output = 1/(1 + Math.exp(-input));
		} else if (ActFun == "tanh") {
			output = (2/(1+Math.exp(-2*input)))-1;
		}
		return output;
	}
	
	public void setActFun(String ActFun) {
		this.ActFun = ActFun;
	}
	
	public String getActFun() {
		return ActFun;
	}
}
