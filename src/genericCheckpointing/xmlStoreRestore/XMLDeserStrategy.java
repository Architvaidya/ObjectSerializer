package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import genericCheckpointing.util.FileProcessor;


public class XMLDeserStrategy implements DeserStrategy{

	@Override
	public Object ProcessInput(FileProcessor fileProcessor) {
		Object objectToReturn = null;
		
		List<DataMember> dataMembers = new ArrayList<DataMember>();
		String line = fileProcessor.readFromLine();
		String classLine = null;
		StringBuffer sb = new StringBuffer();
		
		while(!(line.contains("</DPSerialization"))){
			if(line.contains("<complexType")){
				classLine = line.substring(22);
				classLine = classLine.replaceAll("\"", "").replaceAll(">", "");
				sb.append(classLine+":");
				//int numberOfDataMembers = 0;
				while(!(line.equals("</complexType>"))){
					if(line.contains("xsd:")){
						//numberOfDataMembers++;
						dataMembers.add(getDataMembers(line));
						 //sb.append(getDataMembers(line));
					}
					line = fileProcessor.readFromLine();
				}
				objectToReturn = createObject(sb.toString(),dataMembers);
				//System.out.println("Buffer is: "+sb.toString());
			}
			line = fileProcessor.readFromLine();
		}
		
		
		return objectToReturn;
		
	}
	
	public DataMember getDataMembers(String line){
		StringBuffer type = new StringBuffer();
		StringBuffer value = new StringBuffer();
		StringBuffer name = new StringBuffer();
		//String objectDescription = null; 
		int typeIndex = line.indexOf("xsd:");
		DataMember dataMember = new DataMember();
		//System.out.println("Index of type is: "+typeIndex);
		int i=typeIndex+4;
		while(line.charAt(i)!=('"')){
			type.append(line.charAt(i));
			
			i++;
		}
		i = i + 2;
		while(line.charAt(i)!=('<')){
			value.append(line.charAt(i));
			i++;
		}
		i=i+2;
		while(line.charAt(i)!=('>')){
			name.append(line.charAt(i));
			i++;
		}
		i = i + 2;
		
		dataMember.setName(name.toString());
		dataMember.setType(type.toString());
		dataMember.setValue(value.toString());
		//System.out.println("Type: "+type.toString()+" Value: "+value.toString()+" Name: "+name.toString());
		//objectDescription = name.toString()+","+type.toString()+","+value.toString()+"-";
		return dataMember;
	}
	
	public Object createObject(String objectDescription, List<DataMember> dataMembers){
		Class<?> javaClass = null;
		String[] tokens = objectDescription.split(":");
		//System.out.println("Displaying the list of tokens: ");

		String className = tokens[0];
		//String[] dataMembers = tokens[1].split("-");
		
		
		//Class will be created over here
		try{
			
			javaClass = Class.forName(className);
			
			Class<?> cls = javaClass.newInstance().getClass();
			
			return deserialize(cls.newInstance(), dataMembers);
			
			
		}catch(ClassNotFoundException e){
			System.err.println("Class not found in Deserialize types");
			System.exit(0);
		} catch (InstantiationException | IllegalAccessException e) {
			System.err.println("Instantiation exception or IllegalAccessException in Deserialize types");
			System.exit(0);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return null;
	}

	private Object deserialize(Object object, List<DataMember> dataMembers) {
		Class<?> cls = object.getClass();
		DeserializeTypes deserializetypes =  new DeserializeTypes();
		
		try{
			cls.newInstance();
			for(int i=0;i<dataMembers.size();i++){
				String methodName;
				//System.out.println("Type of member is: "+dataMembers.get(i).getType().toString());
				methodName = "set"+dataMembers.get(i).getName().toString();
				//System.out.println("Method name is: "+methodName);
				if(dataMembers.get(i).getType().equals("int")){
					Method method;
					method = cls.getMethod(methodName, Integer.TYPE);
					int val = deserializetypes.deserializeInt(dataMembers.get(i).getValue());
					method.invoke(object, val);
					
				}
				else if(dataMembers.get(i).getType().equals("string")){
					
					Method method = cls.getMethod(methodName, String.class);
					String val = deserializetypes.deserializeString(dataMembers.get(i).getValue());
					method.invoke(object, val);
				}
				else if(dataMembers.get(i).getType().equals("long")){
					
					Method method = cls.getMethod(methodName, Long.TYPE);
					long val = deserializetypes.deserializeLong(dataMembers.get(i).getValue());
					method.invoke(object, val);
				}
				else if(dataMembers.get(i).getType().equals("boolean")){
					
					Method method = cls.getMethod(methodName, Boolean.TYPE);
					boolean val = deserializetypes.deserializeBoolean(dataMembers.get(i).getValue());
					method.invoke(object, val);
				}
				else if(dataMembers.get(i).getType().equals("double")){
					
					Method method = cls.getMethod(methodName, Double.TYPE);
					Double val = deserializetypes.deserializeDouble(dataMembers.get(i).getValue());
					method.invoke(object, val);
				}
				else if(dataMembers.get(i).getType().equals("char")){
					
					Method method = cls.getMethod(methodName, Character.TYPE);
					char val = deserializetypes.deserializeChar(dataMembers.get(i).getValue());
					method.invoke(object, val);
					
				}
				else if(dataMembers.get(i).getType().equals("short")){
					
					Method method = cls.getMethod(methodName, Short.TYPE);
					short val = deserializetypes.deserializeShort(dataMembers.get(i).getValue());
					method.invoke(object, val);
				}
				else if(dataMembers.get(i).getType().equals("float")){
					
					Method method = cls.getMethod(methodName, Float.TYPE);
					float val = deserializetypes.deserializeFloat(dataMembers.get(i).getValue());
					method.invoke(object, val);
				}
				
				
			}
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return object;
	
		
	}
	

}
