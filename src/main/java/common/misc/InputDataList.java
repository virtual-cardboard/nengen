package common.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InputDataList<T extends Data, R> {

	private final DataList<T> dataList;
	private final boolean[] completed;
	private final R orginal;
	private final Consumer<DataList<T>> onCompletion;

	public InputDataList(DataList<T> dataList, R orginal, Consumer<DataList<T>> onCompletion) {
		this.dataList = dataList;
		completed = new boolean[dataList.parameters.size()];
		this.orginal = orginal;
		this.onCompletion = onCompletion;
	}

	public InputDataList<T, R> set(String name, Object value) {
		Integer index = dataList.parameterIndices.get(name);
		if (index == null) {
			throw new RuntimeException("Could not find parameter " + name + " in " + dataList);
		}
		Data<?> parameter = dataList.parameters.get(index);
		parameter.set(value);
		completed[index] = true;
		return this;
	}

	public R complete() {
		verifyCompleted();
		onCompletion.accept(dataList);
		return orginal;
	}

	private void verifyCompleted() {
		List<Data<?>> incomplete = new ArrayList<>();
		for (int i = 0, m = completed.length; i < m; i++) {
			if (!completed[i]) {
				incomplete.add(dataList.parameters.get(i));
			}
		}
		if (!incomplete.isEmpty()) {
			System.out.println("Not all parameters have been set: "
					+ incomplete.stream()
					.map(Object::toString)
					.reduce((a, b) -> a + ", " + b)
					.get());
		}
	}

}
