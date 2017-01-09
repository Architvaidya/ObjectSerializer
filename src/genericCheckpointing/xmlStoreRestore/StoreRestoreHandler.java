package genericCheckpointing.xmlStoreRestore;

import java.io.FileNotFoundException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;


	
/*The job of InvocationHandler is to respond to any method calls on the proxy*/
public class StoreRestoreHandler implements InvocationHandler{
	FileProcessor fileProcessor;
	@Override
	public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
		//objects = myFirst, "XML" when ((StoreI) cpointRef).writeObj(myFirst, "XML") is called
		//objects = mySecond, "XML" when ((StoreI) cpointRef).writeObj(mySecond, "XML") is called 
		Object objectToReturn = null;

		String methodName = method.getName().toString();
		
		Class<?> parameterTypes[] = method.getParameterTypes();
		String wireFormat = null;
		for(int i=0;i<parameterTypes.length;i++){
			String typeName = parameterTypes[i].getSimpleName();
			if(typeName.equals("String")){
				wireFormat = objects[i].toString();
			}
		}
		//System.out.println("Method is: "+method.getName());
		if(methodName.equals("writeObj")){
			if(wireFormat.equals("XML")){
				serializeData((SerializableObject) objects[0], new XMLSerStrategy(),fileProcessor);
			}
		}else if(methodName.equals("readObj")){
			if(wireFormat.equals("XML")){
				objectToReturn = this.DeserializeData(new XMLDeserStrategy(), fileProcessor);
			}
		}

		//method can be readObj and writeObj
		return objectToReturn;
	}

	
	public FileProcessor openFile(String inputFileName, String outputFileName, String mode){
			try {
				this.fileProcessor = new FileProcessor(inputFileName, outputFileName);
				fileProcessor.open(mode);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.fileProcessor;
	}
	public void closeFile(){
		fileProcessor.close();
	}
	
	public void serializeData(SerializableObject object, XMLSerStrategy strategy, FileProcessor fileProcessor ){
		strategy.ProcessInput(object, fileProcessor);
	}
	
	public Object DeserializeData(XMLDeserStrategy xmlDeserStrategy, FileProcessor fileProcessor) {
		return xmlDeserStrategy.ProcessInput(fileProcessor);
		
	}
	
	
	


}
