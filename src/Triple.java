package net.tofweb.starlite;

public class Triple {
	int x ;
	int y ; 
	int z ; 
	public Triple(int x , int y , int z) {
		this.x=x; 
		this.y= y; 
		this.z= z ;
	}
	 public Triple(Triple T) {
		this.x=T.x;
		this.y= T.y;
		this.z=T.z; 
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Triple == false)
			return false;
		Triple t = (Triple)arg0 ;
		return t.x==this.x && t.y==this.y && t.z == this.z ;
		
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return x+y+z;
	}

}

