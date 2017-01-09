package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLSerStrategy implements SerStrategy{

	@Override
	public void ProcessInput(SerializableObject object, FileProcessor fileProcessor) {
		StringBuffer sb = new StringBuffer();
		sb.append("<DPSerialization>\n <complexType xsi:type=\"");
		String myClass = object.getClass().toString();
		myClass = myClass.replaceAll("class ", "");
		sb.append(myClass+"\">\n");
		Field[] fields = object.getClass().getDeclaredFields();
		SerializedTypes serializedTypes = new SerializedTypes();
		for(int i=0;i<fields.length;i++){
			if(fields[i].getType().equals(Integer.TYPE)){
				//StringBuffer serialize = serializedTypes.serializeInt(sb,fields[i],object);
				sb = serializedTypes.serializeInt(sb,fields[i],object);
				//sb.append(serializedTypes.serializeInt(sb,fields[i],object));

			}
			else if(fields[i].getType().equals(Long.TYPE)){
				sb = serializedTypes.serializeLong(sb,fields[i],object);
				
			}
			else if(fields[i].getType().equals(String.class)){
				sb = serializedTypes.serializeString(sb,fields[i],object);
			}
			else if(fields[i].getType().equals(boolean.class)){
				sb = serializedTypes.serializeBoolean(sb,fields[i],object);
			}
			else if(fields[i].getType().equals(double.class)){
				sb = serializedTypes.serializeDouble(sb,fields[i],object);					
			}
			else if(fields[i].getType().equals(float.class)){
				sb = serializedTypes.serializeFloat(sb,fields[i],object);

			}
			else if(fields[i].getType().equals(short.class)){
				sb = serializedTypes.serializeShort(sb,fields[i],object);
				
			}
			else if(fields[i].getType().equals(char.class)){
				sb = serializedTypes.serializeChar(sb,fields[i],object);
			}
			
		}
		sb.append(" </complexType>\n");
		sb.append("</DPSerialization>");
		fileProcessor.writeToFile(sb.toString());

	}
	
}
