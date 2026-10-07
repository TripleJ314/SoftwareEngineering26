package smoketests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import emptyimplements.DataStoreApiImpl;
import processapi.DataInputSource;
import processapi.DataOutputDestination;
import processapi.IntegerData;

public class TestDataStoreApi {

	@Test
	public void testRead() {
		// Create process api implementation
		DataStoreApiImpl dataStore = new DataStoreApiImpl();
		
		// Create mock input source object
		DataInputSource input = mock(DataInputSource.class);
		
		// Have dataStore read the input source
		IntegerData data = dataStore.read(input);
		
		// Reading should return data
		// But with test it will fail and return null
		assertNotNull(data);
	}
	
	@Test
	public void testWrite() {
		// Create process api implementation
		DataStoreApiImpl dataStore = new DataStoreApiImpl();
		
		// Create mock output destination and integer data object
		DataOutputDestination output = mock(DataOutputDestination.class);
		IntegerData data = mock(IntegerData.class);
		// Have dataStore write to the output destination
		dataStore.write(data, output);
		
		// dataStore should write the data to the output source 
		// But with test it will fail as data is returned as null
		assertNotNull(data);
	}
}
