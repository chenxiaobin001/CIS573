public class EdgeLink
{
	public String sData;   	// name of neighbor
	public int iData;
	public double weight; 	// weight of edge connecting to neighbor
	public EdgeLink next;   // next link in list
//	public EdgeLink pre;
	// -------------------------------------------------------------
	public EdgeLink(String id, int index, double w,  EdgeLink newNext) // constructor
	{
		sData = id;
		weight = w;
		next = newNext;
		iData = index;
//		pre = newPre;
	}
	// -------------------------------------------------------------
	public void displayLink()      // display ourself
	{
		System.out.print("{" + sData + " " + weight + "} ");
	}
}  // end class EdgeLink