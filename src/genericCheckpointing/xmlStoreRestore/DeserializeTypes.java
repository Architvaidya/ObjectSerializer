package genericCheckpointing.xmlStoreRestore;




public class DeserializeTypes {

	
	public int deserializeInt(String value){
		int val = Integer.parseInt(value);
		return val;
		
		
	}
	public long deserializeLong(String value){
		long val = Long.parseLong(value);
		return val;
	}
	public String deserializeString(String value){
		return value; 
	}
	public boolean deserializeBoolean(String value){
		boolean val = Boolean.parseBoolean(value);
		return val;
	}
	public double deserializeDouble(String value){
		double val = Double.parseDouble(value);
		return val;
	}
	public float deserializeFloat(String value){
		float val = Float.parseFloat(value);
		return val;
	}
	public char deserializeChar(String value){
		char val = value.charAt(0);
		return val;
	}
	public short deserializeShort(String value){
		short val = Short.parseShort(value);
		return val;
	}

	

}
