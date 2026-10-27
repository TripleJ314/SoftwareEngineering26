package networkapi;

public class DelimitersImpl implements Delimiters {
	private final String fieldDelimiter;
	
	// Initializes the delimiter or uses default if not specified
	public DelimitersImpl(String fieldDelimiter) {
		if (fieldDelimiter == null) {
			this.fieldDelimiter = DEFAULT_FIELD;
		} else {
			this.fieldDelimiter = fieldDelimiter;
		}
	}
	
	// Return delimiter
	@Override
	public String getFieldDelimiter() {
		return fieldDelimiter;
	}

}
