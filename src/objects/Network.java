package objects;

public class Network {

	private final int NUM_LAYERS = 5; //can change this, but will need to recode LAYER_SIZE
	Node[][] layers = new Node[NUM_LAYERS][0]; //will set each layer during initialization
	private final int[] LAYER_SIZE = {9,7,5,3,1}; //will need recoding if NUM_LAYERS changes
	private double[][][] weights = new double[NUM_LAYERS][0][0]; //will set each weights during initialization
	
	public Network(int numInputs, String hiddenActFun, String outputActFun) { //initialization
		
		for (int i = 0; i < NUM_LAYERS; i++) {
			layers[i] = new Node[LAYER_SIZE[i]]; //create a new layer using LAYER_SIZE
			if (i == NUM_LAYERS - 1) { //if output layer, use output function
				for (int j = 0; j < LAYER_SIZE[i]; j++) {
					layers[i][j] = new Node(outputActFun);
				}
			} else { //else if not output layer, use hidden function
				for (int j = 0; j < LAYER_SIZE[i]; j++) {
					layers[i][j] = new Node(hiddenActFun);
				}
			}
			
		}
		
		for (int i = 0; i < NUM_LAYERS; i++) {
			if (i == 0) { //use number of inputs to create weights
				weights[i] = new double[numInputs][LAYER_SIZE[i]];
			} else { //use size of last layer
				weights[i] = new double[LAYER_SIZE[i-1] + 1][LAYER_SIZE[i]];
			}
			for (int j = 0; j < weights[i].length; j++) {
				for (int k = 0; k < weights[i][j].length; k++) { 
					double weight = Math.random();
					if (Math.random() > 0.5) weight = -weight;
					weights[i][j][k] = weight;
				}
			}
		}
	}
	
	public double[][] getWeights(int layer) {
		return weights[layer];
	}
	
	public Node[] getLayer(int layer) {
		return layers[layer];
	}
	
	public void printNetwork() {
		for (int i = 0; i < layers.length; i++) {
			for (int j = 0; j < layers[i].length; j++) {
				System.out.print("(" + layers[i][j].getActFun() + ") ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void printWeights() {
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights[i].length; j++) {
				for (int k = 0; k < weights[i][j].length; k++) {
					System.out.print(weights[i][j][k] + " ");
				}
				System.out.println();
			}
		System.out.println();
		}
	}
}
