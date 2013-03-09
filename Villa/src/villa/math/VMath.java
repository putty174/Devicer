package villa.math;

public final class VMath
{
	/**
	 * Returns the magnitude of the inputed values.
	 * 
	 * @param val values to take the magnitude of
	 * @return the magnitude
	 */
	public static double magnitude(double... val)
	{
		double magnitude = 0;
		for(int i = 0; i < val.length; i++)
		{
			magnitude += val[i] * val[i];
		}//rof
		magnitude = Math.sqrt(magnitude);
		
		return magnitude;
	}//END magnitude
}//END class VMath
