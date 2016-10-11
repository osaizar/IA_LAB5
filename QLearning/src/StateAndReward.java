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
			state += "xincr";
		}
		else {
			state += "xdecr";
		}
		
		if (vy > 0) {
			state += "yincr";
		}
		else {
			state += "ydecr";
		}
		
		return state;
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		double abs_angle = angle;
		if (abs_angle < 0)
			abs_angle = -abs_angle;
		
		double reward = 10000;
		reward = reward / (abs_angle + 0.01);
		
		reward = reward - (10 * vx);
		reward = reward - (10 * vy);
		
		return reward;
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		// SPACE STATE TIMES POSSIBLE ACTIONS = 4*3*3*8 = 36 * 8 = 288

		String state = "";
		if (angle < -2.25 || angle > 2.25)       // Facing = south sector
			state += "south-";
		else if (angle < -0.75)   // Facing = west sector
			state += "west-";
		else if (angle < 0.75)   // Facing = north sector
			state += "north-";
		else                    // Facing = east sector
			state += "east-";
		
		// TODO: Probably more work to do here
		if (vx >= 0.5) 
			state += "xincr-";
		else if (vx > -0.5)
			state += "xstbl-";
		else 
			state += "xdecr-";
		
		if (vy >= 0.5) 
			state += "yincr-";
		else if (vy > -0.5)
			state += "ystbl-";
		else 
			state += "ydecr-";
		
		return state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		double reward;
		
		if (angle < -2.25 || angle > 2.25) {
			reward = 0;
		}
		else if (angle < -0.75) {
			reward = 50;
		}
		else if (angle < 0.75) {
			reward = 100;
			if (angle < 0.40 && angle > -0.40)
				reward = 1000;
			if ((-0.4 < vx && vx < 0.4) && (-0.4 < vy && vy < 0.4))
				reward += 1000;
			else if (-0.4 < vy && vy < 0.4)
				reward += 500;
			else if (-0.4 < vx && vx < 0.4)
				reward += 300;
		}
		else {
			reward = 50;
		}
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
