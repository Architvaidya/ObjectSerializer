package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import genericCheckpointing.util.SerializableObject;

public class SerializedTypes {
	StringBuffer serializeInt(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}
	
	StringBuffer serializeLong(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}
	StringBuffer serializeString(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType().getSimpleName().toLowerCase()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}
	StringBuffer serializeBoolean(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}
	StringBuffer serializeFloat(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}
	StringBuffer serializeDouble(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}
	StringBuffer serializeChar(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}
	StringBuffer serializeShort(StringBuffer sb, Field field, SerializableObject object){
		sb.append(" <"+field.getName()+" xsi:type=\"xsd:").append(field.getType().toString().toLowerCase()+"\">");
		sb = appendBuffer(field, sb, object);
		return sb;
	}

	
	public StringBuffer appendBuffer(Field field, StringBuffer sb,Object object){

		//field.setAccessible(true);
		try{
			//System.out.println("Field type is: "+field.getType().getSimpleName());
			String methodName = "get"+field.getName();
			Method m = object.getClass().getMethod(methodName);
			Object fieldValue = m.invoke(object);
			sb.append(fieldValue);
			sb.append("</"+field.getName()+">\n");
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Unable to populate the field in SerializedTypes");
			System.exit(0);
		}
		return sb;
	}

}
