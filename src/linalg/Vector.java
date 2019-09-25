package linalg;

/*** A class that represents a multidimensional real-valued (double) vector
 *   and supports various vector computations required in linear algebra.
 *   
 *   Class and method comments are in JavaDoc: https://en.wikipedia.org/wiki/Javadoc
 * 
 * @author scott.sanner@utoronto.ca, <YOUR_EMAIL>
 *
 */
public class Vector {

	private int _nDim;       // Dimension of the Vector; nomenclature: _ for data member, n for integer
	private double[] _adVal; // Contents of the Vector; nomenclature: _ for data member, a for array, d for double

	/** Constructor: allocates space for a new vector of dimension dim
	 * 
	 * @param dim
	 * @throws LinAlgException if vector dimension is < 1
	 */
	public Vector(int dim) throws LinAlgException {
		if (dim <= 0)
			throw new LinAlgException("Vector dimension " + dim + " cannot be less than 1");
		_nDim = dim;
		_adVal = new double[dim]; // Entries will be automatically initialized to 0.0
	}
	
	/** Copy constructor: makes a new copy of an existing Vector v
	 *                    (note: this explicitly allocates new memory and copies over content)
	 * 
	 * @param v
	 */
	public Vector(Vector v) {
		_nDim = v._nDim;
		_adVal = new double[_nDim]; // This allocates an array of size _nDim
		for (int index = 0; index < _nDim; index++)
			_adVal[index] = v._adVal[index];
	}

	/** Constructor: creates a new Vector with dimension and values given by init
	 * 
	 * @param init: a String formatted like "[ -1.2 2.0 3.1 5.8 ]" (must start with [ and end with ])
	 * @throws LinAlgException if init is not properly formatted (missing [ or ], or improperly formatted number)
	 */
	public Vector(String init) throws LinAlgException {
		
		// The following says split init on whitespace (\\s) into an array of Strings
		String[] split = init.split("\\s");  
		// Uncomment the following to see what split produces
		// for (int i = 0; i < split.length; i++)
		// 		System.out.println(i + ". " + split[i]);

		if (!split[0].equals("[") || !split[split.length-1].equals("]"))
			throw new LinAlgException("Malformed vector initialization: missing [ or ] in " + init);

		// We don't count the [ and ] in the dimensionality
		_nDim = split.length - 2;
		_adVal = new double[_nDim];
		
		// Parse each number from init and add it to the Vector in order (note the +1 offset to account for [)
		for (int index = 0; index < _nDim; index++) {
			try {
				set(index, Double.parseDouble(split[index + 1]));
			} catch (NumberFormatException e) {
				throw new LinAlgException("Malformed vector initialization: could not parse " + split[index + 1] + " in " + init);
			}
		}
	}
	
	/** Get the dimension of this vector
	 * 
	 * @return: the dimensionality of this Vector
	 */
	public int getDim() {
		// TODO (this should not return -1!)
		return -1;
	}

	/** Returns the value of this vector at the given index (remember: array indices start at 0)
	 * 
	 * @param index
	 * @return
	 * @throws LinAlgException if array index is out of bounds (see throw examples above)
	 */
	public double get(int index) throws LinAlgException {
		// TODO (this should not return -1.0!)
		return -1.0;
	}

	/** Set the value val of the vector at the given index (remember: array indices start at 0)
	 * 
	 * @param index
	 * @param val
	 * @throws LinAlgException if array index is out of bounds (see throw examples above)
	 */
	public void set(int index, double val) throws LinAlgException {
		// TODO
	}

	/** This adds a scalar d to all elements of *this* Vector
	 *  ... (should modify *this*). The following is merely a representation of 
	 *  ... how this method should behave. 
	 *	a => [[1,2,3],[4,5,6]]
	 *  a.scalarAddInPlace(1)
	 * 	a => [[2,3,4],[5,6,7]] 
	 * 
	 * @param d
	 */
	public void scalarAddInPlace(double d) {
		for (int index = 0; index < _nDim; index++)
			_adVal[index] += d;
	}
	
	/** This creates a new Vector with the same dimention of *this*, 
	 *  ... and copy all element of *this* to new vector object. Add scalar to every element in new vector
	 *  ... and returns it
	 *  (should not modify *this*)
	 * 
	 * @param d
	 * @return new Vector after scalar addition
	 */
	public Vector scalarAdd(double d) {
		// TODO (this should not return null!)
		return null;
	}
	
	/** This multiplies a scalar d by all elements of *this* Vector
	 *  (should modify *this*)
	 * 
	 * @param d
	 */
	public void scalarMultInPlace(double d) {
		// TODO
	}
	
	/** This creates a new Vector, multiplies it by a scalar d, and returns it
	 *  (should not modify *this*)
	 * 
	 * @param d
	 * @return new Vector after scalar addition
	 */
	public Vector scalarMult(double d) {
		// TODO (this should not return null!)
		return null;
	}

	/** Performs an elementwise addition of v to *this*, modifies *this*
	 * 
	 * @param v
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public void elementwiseAddInPlace(Vector v) throws LinAlgException {
		// TODO
	}

	/** Performs an elementwise addition of *this* and v and returns a new Vector with result
	 * 
	 * @param v
	 * @return
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public Vector elementwiseAdd(Vector v) throws LinAlgException {
		// TODO (this should not return null!)
		return null;
	}
	
	/** Performs an elementwise multiplication of *this* and v, modifies *this*
	 * 
	 * @param v
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public void elementwiseMultInPlace(Vector v) throws LinAlgException {
		// TODO
	}

	/** Performs an elementwise multiplication of *this* and v and returns a new Vector with result
	 * 
	 * @param v
	 * @return
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public Vector elementwiseMult(Vector v) throws LinAlgException {
		// TODO (this should not return null!)
		return null;
	}

	/** Performs an inner product of Vectors v1 and v2 and returns the scalar result
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @throws LinAlgException
	 */
	public static double InnerProd(Vector v1, Vector v2) throws LinAlgException {
		// TODO (this should not return -1.0!)
		return -1.0;
	}
}
