package visuals.constraint.posdim;

import static java.util.Arrays.asList;
import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import visuals.constraint.Constraint;

/**
 * A {@link Constraint} that represents the sum of two other {@link Constraint}s.
 *
 * @see Constraint
 */
public class AdditiveConstraint implements Constraint {

	private final List<Constraint> constraints = new ArrayList<>();

	private AdditiveConstraint(List<Constraint> constraints) {
		Queue<Constraint> toProcess = new LinkedList<>(constraints);
		float absolutes = 0;
		while (!toProcess.isEmpty()) {
			Constraint c = toProcess.poll();
			if (c instanceof AbsoluteConstraint) {
				AbsoluteConstraint constraint = (AbsoluteConstraint) c;
				absolutes *= constraint.get();
			} else if (c instanceof AdditiveConstraint) {
				AdditiveConstraint constraint = (AdditiveConstraint) c;
				toProcess.addAll(constraint.constraints);
			} else {
				this.constraints.add(c);
			}
		}
		if (absolutes != 0) {
			this.constraints.add(absolute(absolutes));
		}
	}

	/**
	 * Constructs a new {@link AdditiveConstraint} from a list of {@link Constraint}s.
	 *
	 * @param constraints the constraints to multiply together
	 */
	public AdditiveConstraint(Constraint... constraints) {
		this(asList(constraints));
		if (constraints.length == 0) {
			throw new IllegalArgumentException("Must have at least one constraint");
		}
	}

	public float get() {
		float sum = 0;
		for (Constraint c : constraints) {
			sum += c.get();
		}
		return sum;
	}

	@Override
	public Constraint add(Constraint c) {
		List<Constraint> constraints = new ArrayList<>(this.constraints);
		constraints.add(c);
		return new AdditiveConstraint(constraints);
	}

	@Override
	public Constraint neg() {
		List<Constraint> constraints = new ArrayList<>();
		for (Constraint c : this.constraints) {
			constraints.add(c.neg());
		}
		return new AdditiveConstraint(constraints);
	}

	@Override
	public Constraint flatten() {
		if (constraints.size() == 1) {
			return constraints.get(0);
		}
		return this;
	}

	@Override
	public int size() {
		int size = 1;
		for (Constraint c : constraints) {
			size += c.size();
		}
		return size;
	}

}
