public class StateAndReward {

	
	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		//String state = "OneStateToRuleThemAll";
		String state = "";
		if (angle < -1.5) {      // Facing = south-west sector
			state += "sec1-";
		}
		else if (angle < 0.0) {  // Facing = north-west sector
			state += "sec2-";
		}
		else if (angle < 1.5) {  // Facing = north-east sector
			state += "sec3-";
		}
		else {                   // Facing = south-east sector
			state += "sec4-";
		}
		
		// TODO: Probably more work to do here
		if (vx > 0) {
			state += "xright-";
		}
		else {
			state += "xleft--";
		}
		
		if (vy > 0) {
			state += "yup--";
		}
		else {
			state += "ydown";
		}
		
		return state;
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		if (angle < -1.5) {      // Facing = south-west sector
			if (vx > 0 && vy > 0) {
				return 2;
			}
			else if (vx > 0) {
				return 3;
			}
			else if (vy > 0) {
				return 1;
			}
			else
				return 2;
		}
		else if (angle < 0.0) {  // Facing = north-west sector
			if (vx > 0 && vy > 0) {
				return 3;
			}
			else if (vx > 0) {
				return 2;
			}
			else if (vy > 0) {
				return 2;
			}
			else
				return 1;
		}
		else if (angle < 1.5) {  // Facing = north-east sector
			if (vx > 0 && vy > 0) {
				return 2;
			}
			else if (vx > 0) {
				return 1;
			}
			else if (vy > 0) {
				return 3;
			}
			else
				return 2;
		}
		else {                   // Facing = south-east sector
			if (vx > 0 && vy > 0) {
				return 1;
			}
			else if (vx > 0) {
				return 2;
			}
			else if (vy > 0) {
				return 2;
			}
			else
				return 3;
		}
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		String state = "OneStateToRuleThemAll2";
		
		return state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		double reward = 0;

		return reward;
	}

	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min,
			double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min,
			double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
