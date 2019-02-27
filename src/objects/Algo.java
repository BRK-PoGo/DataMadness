package objects;

public class Algo {
	
	double[][] inputs = {{1,1,1,0,6,0,1,0.22,0.2727,0.8,0},
						 {4,11,23,0,0,0,1,0.34,0.3485,0.87,0.0896}};
	int[] output = {40,50};
	Network network = new Network(inputs.length, "tanh", "sig");
	private final double LEARNING_RATE = 0.01;

	public void run() {
		int [] predicted_outs = new int[output.length];
		double[][] error = new double[network.getNumLayers()][0];
		//for (int y = 0; y < inputs.length; y++) {
			double[][] internalOuts = new double[network.getNumLayers()][0];
			double[] input = inputs[0];
			for (int x = 0; x < network.getNumLayers(); x++) {
				
				double[][] weights = network.getWeights(x);
				Node[] layer = network.getLayer(x);
				
				double[] sums = new double[layer.length];
				
				for (int i = 0; i < weights.length; i++) {
					for (int j = 0; j < weights[i].length; j++) {
						sums[j] = sums[j] + (weights[i][j] * input[i]);
					}
				}
				
				double[] outs = new double[sums.length + 1];
				for (int i = 0; i < sums.length; i++) {
					outs[i] = layer[i].ActivationFunction(sums[i]);
				}
				
				outs[outs.length-1] = 1;
				
				internalOuts[x] = outs;
				input = outs;
			}
			predicted_outs[0] = (int) (input[0] * 100);
			error[error.length - 1] = new double[input.length - 1];
			error[error.length - 1][0] = predicted_outs[0] - output[0];
			error[error.length - 2] = new double[network.getLayer(error.length - 2).length + 1];
			error[error.length - 2][0] = (internalOuts[error.length - 2][0]) * (1 - internalOuts[error.length - 2][0]) * (network.getWeights(error.length - 1)[0][0] * error[error.length - 1][0]);
		//}
		/*
		System.out.println("Final predictions and errors:");
		for (int i = 0; i < predicted_outs.length; i++) {
			System.out.println(predicted_outs[0] + " : " + error[0][0]);
		}
		*/
	}
}
