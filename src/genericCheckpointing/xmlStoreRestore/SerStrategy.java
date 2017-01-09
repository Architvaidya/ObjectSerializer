package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public interface SerStrategy {
	public void ProcessInput(SerializableObject sObject,FileProcessor fileProcessor);
}
