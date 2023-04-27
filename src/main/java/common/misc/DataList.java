package common.misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class providing runtime type information for a list of input parameters.
 * <br>
 * <br>
 * This is not a performant class, and should not be used in performance-critical code.
 */
public class DataList {

	private final Map<String, Integer> parameterIndices;
	private final List<Data<?>> parameters;
	private final boolean[] completed;

	public DataList(List<Data<?>> parameters) {
		this.parameters = parameters;
		completed = new boolean[parameters.size()];
		parameterIndices = new HashMap<>(parameters.size());
		for (int i = 0, m = parameters.size(); i < m; i++) {
			Data<?> parameter = parameters.get(i);
			parameterIndices.put(parameter.name(), i);
		}
	}

	public DataList set(String name, Object value) {
		int index = parameterIndices.get(name);
		Data<?> parameter = parameters.get(index);
		try {
			parameter.set(value);
		} catch (Exception e) {
			throw new RuntimeException("Could not set parameter " + name + " to " + value, e);
		}
		completed[index] = true;
		return this;
	}

	public boolean completed() {
		for (boolean b : completed) {
			if (!b) {
				return false;
			}
		}
		return true;
	}

}
