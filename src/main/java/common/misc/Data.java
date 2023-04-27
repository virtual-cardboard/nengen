package common.misc;

public class Data<T> {

	public String name;
	public Class<T> type;
	public T value;

	public Data(String name, Class<T> type) {
		this.name = name;
		this.type = type;
	}

	public Data(String name, Class<T> type, T value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String name() {
		return name;
	}

	public Class<T> type() {
		return type;
	}

	public T get() {
		return value;
	}

	@SuppressWarnings("unchecked")
	public void set(Object value) {
		verifyType(value);
		this.value = (T) value;
	}

	private void verifyType(Object value) {
		if (!type.isInstance(value)) {
			throw new IllegalArgumentException("Invalid type for " + name + ": " + value.getClass().getName());
		}
	}

}
