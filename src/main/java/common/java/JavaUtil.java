package common.java;

import static java.lang.System.arraycopy;
import static java.util.Arrays.*;

public class JavaUtil {

	public static <T> T[] concat(T[] first, T[] second) {
		T[] result = copyOf(first, first.length + second.length);
		arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	public static <T> T[] flatten(T[][] array) {
		int length = 0;
		for (T[] subArray : array) {
			length += subArray.length;
		}
		T[] result = copyOf(array[0], length);
		int offset = array[0].length;
		for (int i = 1; i < array.length; i++) {
			arraycopy(array[i], 0, result, offset, array[i].length);
			offset += array[i].length;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> Pair<Integer, T>[] enumerate(T[] array) {
		Pair<Integer, T>[] result = new Pair[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = new Pair<>(i, array[i]);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> Triple<Integer, Integer, T>[] enumerate(T[][] array) {
		Triple<Integer, Integer, T>[] result = new Triple[array.length * array[0].length];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				result[index++] = new Triple<>(i, j, array[i][j]);
			}
		}
		return result;
	}

	public static class Pair<A, B> {
		public A a;
		public B b;

		public Pair(A a, B b) {
			this.a = a;
			this.b = b;
		}

		public A a() {
			return a;
		}

		public B b() {
			return b;
		}

		public A x() {
			return a;
		}

		public B y() {
			return b;
		}

		public A first() {
			return a;
		}

		public B second() {
			return b;
		}

		public A left() {
			return a;
		}

		public B right() {
			return b;
		}

	}

	public static class Triple<A, B, C> {
		public A a;
		public B b;
		public C c;

		public Triple(A a, B b, C c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public A a() {
			return a;
		}

		public B b() {
			return b;
		}

		public C c() {
			return c;
		}

		public A x() {
			return a;
		}

		public B y() {
			return b;
		}

		public C z() {
			return c;
		}

		public A first() {
			return a;
		}

		public B second() {
			return b;
		}

		public C third() {
			return c;
		}

		public A left() {
			return a;
		}

		public B middle() {
			return b;
		}

		public C right() {
			return c;
		}

	}

}
