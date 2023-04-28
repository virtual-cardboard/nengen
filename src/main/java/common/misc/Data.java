package common.misc;

import static nengen.EngineConfiguration.DEBUG;

public class Data<T> {

	protected String name;
	protected T value;

	private transient String className;

	public Data(String name) {
		this.name = name;
	}

	public Data(String name, T value) {
		this(name);
		safeSet(value);
	}

	public Data(String name, Class<T> clazz) {
		this(name);
		if (DEBUG) {
			className = clazz.getName();
		}
	}

	public Data(String name, T value, Class<T> clazz) {
		this(name, clazz);
		safeSet(value);
	}

	public String name() {
		return name;
	}

	public Object get() {
		return value;
	}

	public void safeSet(T value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public void set(Object value) {
		try {
			this.value = (T) value;
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid type for " + name + ": " + value.getClass().getName());
		}
	}

	@Override
	public String toString() {
		String valueString = value == null ? "" : (" = " + value);
		String typeString = className == null ? "" : (" (" + className + ")");
		return "[" + name + ":" + typeString + valueString + "]";
	}

}
