
package genericCheckpointing.driver;



import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

// import the other types used in this file

public class Driver {
    
	//(0) mode, (1) NUM_OF_OBJECTS_OF_ONE_KIND and (2) output file name. 
    public static void main(String[] args) {
    
    // FIXME: read the value of checkpointFile from the command line
    /*try{
    	FileProcessor fileProcessor = new FileProcessor(null,args[2]);
    }catch(FileNotFoundException e){
    	System.err.println("Unable to open the file");
    	System.exit(0);
    }*/
    Object[] myObjectarray = new Object[args.length];
    String argument = args[2];
    Object obj = argument;
    //System.out.println("Object is "+obj.toString());
    
    myObjectarray[0] = obj;
    ProxyCreator pc = new ProxyCreator();
	// create an instance of StoreRestoreHandler (which implements
	// the InvocationHandler
	//StoreRestoreHandler handler = new StoreRestoreHandler();
	InvocationHandler handler = new StoreRestoreHandler();
	
	// create a proxy
	StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
								 new Class[] {
								     StoreI.class, RestoreI.class
								 }, 
								 handler
								 );
		
	
	
	
	// FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file
	try {
		((StoreRestoreHandler)handler).openFile(args[2],args[2],args[0]);
		//handler.invoke(cpointRef, null,myObjectarray);
	} catch (Exception e) {
		System.err.println("File not found");
		System.exit(0);
	}
	MyAllTypesFirst myFirst;
	MyAllTypesSecond  mySecond;
	int j = 0;
	// Use an if/switch to proceed according to the command line argument
	// For deser, just deserliaze the input file into the data structure and then print the objects
	// The code below is for "serdeser" mode
	// For "serdeser" mode, both the serialize and deserialize functionality should be called.

	// create a data structure to store the objects being serialized
	
	List<SerializableObject> serializableObjectListSer = new ArrayList<SerializableObject>(); 
	SerializableObject myRecordRet;
	List<SerializableObject> serializableObjectListDeser = new ArrayList<SerializableObject>(); 
	int numberOfMissMatchObjects = 0;
	String seed = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
	if(args[0].equals("serdeser")){
		for (int i=1; i<=Integer.parseInt(args[1]); i++) {
			Float myFloat = 10.0f;
			short myShort = 1;
			Random randomGenerator = new Random();
			int beginIndex = randomGenerator.nextInt(seed.length());
			
		    // FIXME: create these object instances correctly using an explicit value constructor
		    // use the index variable of this loop to change the values of the arguments to these constructors
		    myFirst = new MyAllTypesFirst(i,i*10000000,seed.substring(beginIndex),true);
		    mySecond = new MyAllTypesSecond(Math.random()*i,myFloat,myShort,'a');
		    serializableObjectListSer.add(myFirst);
		    serializableObjectListSer.add(mySecond);
		    // FIXME: store myFirst and mySecond in the data structure
		    ((StoreI) cpointRef).writeObj(myFirst, "XML");
		    ((StoreI) cpointRef).writeObj(mySecond, "XML");

		}
		for (j=0; j<2*Integer.parseInt(args[1]); j++) {
			myRecordRet = ((RestoreI) cpointRef).readObj("XML");
		    serializableObjectListDeser.add(myRecordRet);
		    // FIXME: store myRecordRet in the vector
		}
		
		for(int i=0;i<serializableObjectListDeser.size();i++){
			
			if(!(serializableObjectListDeser.get(i).equals(serializableObjectListSer.get(i))||serializableObjectListDeser.get(i).hashCode()!=serializableObjectListSer.get(i).hashCode())){
				numberOfMissMatchObjects++;
			}
		}
		System.out.println(numberOfMissMatchObjects+" mismatch objects");
		
	}
	
	else if(args[0].equals("deser")){
		try{
			for (j=0; j<Integer.parseInt(args[1]); j++) {
				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			    serializableObjectListDeser.add(myRecordRet);
			    // FIXME: store myRecordRet in the vector
			}
			
		}catch(NullPointerException e){
			//System.err.println("Only "+j+" objects created");
			System.out.println();
		}

		for(int i=0;i<serializableObjectListDeser.size();i++){
			try{
				System.out.println(serializableObjectListDeser.get(i).toString());
			}catch(NullPointerException e){
				System.err.println("null");
			}
		}
	}

        

	



	//System.out.println("Printing the object details  in the driver");
	
	//System.out.println("Size of serializableObjectListDeser "+serializableObjectListDeser.size());
	//System.out.println("Size of serializableObjectListSer "+serializableObjectListSer.size());
	

	
	// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
	try {
		((StoreRestoreHandler)handler).closeFile();
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
	// The comparison should use the equals and hashCode methods. Note that hashCode 
	// is used for key-value based data structures
	
	
	
	/*for(int i=0;i<serializableObjectListDeser.size()|| i<serializableObjectListSer.size();i++){
		if((serializableObjectListDeser.get(i).equals(serializableObjectListSer.get(i)))){
			numberOfMissMatchObjects++;
		}
	}*/
	//System.out.println(numberOfMissMatchObjects+" mismatch objects");

    
    }
}