package genericCheckpointing.util;



public class MyAllTypesSecond extends SerializableObject{
	private double myDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	
	public double getMydouble() {
		return myDoubleT;
	}
	
	public MyAllTypesSecond(){
		
	}
	
	

	
	
	public String toString() {
		String str = "[MyAllTypesSecond.myDoubleT:"+this.myDoubleT+"] "+"[MyAllTypesSecond.myFloatT:"+this.myFloatT+"] "+"[MyAllTypesSecond.myShortT:"+this.myShortT+"] "+"[MyAllTypesSecond.myCharT:"+this.myCharT+"] ";
		return str;
		
	}
	
	public MyAllTypesSecond(double myDoubleT, Float myFloatT, short myShortT, char myCharT) {
		
		this.myDoubleT = myDoubleT;
		this.myFloatT = myFloatT;
		this.myShortT = myShortT;
		this.myCharT = myCharT;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myCharT;
		long temp;
		temp = Double.doubleToLongBits(myDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloatT);
		result = prime * result + myShortT;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAllTypesSecond other = (MyAllTypesSecond) obj;
		if (myCharT != other.myCharT)
			return false;
		if (Double.doubleToLongBits(myDoubleT) != Double.doubleToLongBits(other.myDoubleT))
			return false;
		if (Float.floatToIntBits(myFloatT) != Float.floatToIntBits(other.myFloatT))
			return false;
		if (myShortT != other.myShortT)
			return false;
		return true;
	}

	public void setmyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	public double getmyDoubleT(){
		return this.myDoubleT;
	}
	public Float getmyFloatT() {
		return myFloatT;
	}
	public void setmyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	public short getmyShortT() {
		return myShortT;
	}
	public void setmyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	public char getmyCharT() {
		return myCharT;
	}
	public void setmyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	
}
