package smoketests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import emptyimplements.DataStoreApiImpl;
import processapi.DataInputSource;
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
}
