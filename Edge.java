

public class Edge {
	private Integer v, w; //vertices of an edge
	private int weight;// 1 or 0 or MAX_VALUE or something in between for weight
	
	public Edge(Integer first, Integer second, int edgeWeight){
		v=first;
		w=second;
		weight = edgeWeight;
	}
	public  int getWeight(){
		return weight;
	}
	public Integer getV(){
		return v;
	}
	public Integer getW(){
		return w;
	}
	
	

}
