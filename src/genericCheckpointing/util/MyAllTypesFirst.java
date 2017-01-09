package genericCheckpointing.util;



public class MyAllTypesFirst extends SerializableObject{
	
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	
	


	public String toString() {
		String str = "[MyAllTypesFirst.myInt:"+this.myInt+"] "+"[MyAllTypesFirst.myLong:"+this.myLong+"] "+"[MyAllTypesFirst.myBool:"+this.myBool+"] "+"[MyAllTypesFirst.myString:"+this.myString+"] ";
		return str;
		
	}
	
	
	public MyAllTypesFirst(int myInt, long myLong, String myString, boolean myBool) {
		
		this.myInt = myInt;
		this.myLong = myLong;
		this.myString = myString;
		this.myBool = myBool;
	}
	
	public MyAllTypesFirst(){
		
	}
	public int getmyInt() {
		return myInt;
	}
	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}
	public long getmyLong() {
		return myLong;
	}
	public void setmyLong(long myLong) {
		this.myLong = myLong;
	}
	public String getmyString() {
		return myString;
	}
	public void setmyString(String myString) {
		this.myString = myString;
	}
	public boolean getmyBool() {
		return myBool;
	}
	public void setmyBool(boolean myBool) {
		this.myBool = myBool;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (myBool ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result + ((myString == null) ? 0 : myString.hashCode());
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
		MyAllTypesFirst other = (MyAllTypesFirst) obj;
		if (myBool != other.myBool)
			return false;
		if (myInt != other.myInt)
			return false;
		if (myLong != other.myLong)
			return false;
		if (myString == null) {
			if (other.myString != null)
				return false;
		} else if (!myString.equals(other.myString))
			return false;
		return true;
	}
	







}
