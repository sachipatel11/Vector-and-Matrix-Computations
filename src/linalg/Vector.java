package linalg;

/*** A class that represents a multidimensional real-valued (double) vector
 *   and supports various vector computations required in linear algebra.
 *   
 *   Class and method comments are in JavaDoc: https://en.wikipedia.org/wiki/Javadoc
 * 
 * @author scott.sanner@utoronto.ca, <YOUR_EMAIL> sachi.patel@mail.utoronto.ca
 *
 */

//v1.v2 - THIS is V1 ** general comment 

public class Vector {

	public int _nDim;       // Dimension of the Vector; nomenclature: _ for data member, n for integer
	public double[] _adVal; // Contents of the Vector; nomenclature: _ for data member, a for array, d for double
        //Vector v1= new Vector();
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

	/** Overrides method toString() on Object: converts the class to a human readable String
	 * 
	 *  Note 1: this is invoked *automatically* when the object is listed where a String is expected,
	 *          e.g., "System.out.println(v);" is actually equivalent to "System.out.println(v.toString());"       
	 *          
	 *  Note 2: for debugging purposes, you should always define a toString() method on a class you define
	 */
	@Override // optional annotation to tell Java we expect this overrides a parent method -- compiler will warn if not
	public String toString() {
		// We could just repeatedly append to an existing String, but that copies the String each
		// time, whereas a StringBuilder simply appends new characters to the end of the String
		StringBuilder sb = new StringBuilder();
                sb.append("[");
		for (int i = 0; i < _nDim; i++)
			sb.append(String.format(" %6.3f ", _adVal[i])); // Append each vector value in order
		sb.append(" ]");
		return sb.toString();
	}

	/** Overrides address equality check on Object: allows semantic equality testing of vectors,
	 *  i.e., here we say two objects are equal iff they have the same dimensions and values
	 *        match at all indices
	 * 
	 * Note: you should almost always define equals() since the default equals() on Object simply
	 *       tests that two objects occupy the same space in memory (are actually the same instance), 
	 *       but does not test that two objects may be different instances but have the same content
	 *       
	 * @param o the object to compare to
	 */
	@Override // optional annotation to tell Java we expect this overrides a parent method -- compiler will warn if not
	public boolean equals(Object o) {
		if (o instanceof Vector) {
			Vector v = (Vector)o; // This is called a cast (or downcast)... we can do it since we
			                      // know from the if statement that o is actually of subtype Vector
			if (_nDim != v._nDim)
				return false; // Two Vectors cannot be equal if they don't have the same dimension
			for (int index = 0; index < _nDim; index++)
				if (_adVal[index] != v._adVal[index])
					return false; // If two Vectors mismatch at any index, they are not equal
			return true; // Everything matched... objects are equal!
		} else // if we get here "(o instanceof Vector)" was false
			return false; // Two objects cannot be equal if they don't have the same class type
	}
	
	/** Get the dimension of this vector
	 * 
	 * @return: the dimensionality of this Vector
	 */
        
        //returning the dimension of the vector 
	public int getDim() {
		// TODO (this should not return -1!)
                return _nDim;
	}

	/** Returns the value of this vector at the given index (remember: array indices start at 0)
	 * 
	 * @param index
	 * @return value 
	 * @throws LinAlgException if array index is out of bounds (see throw examples above)
	 */
        //if array index in bounds, use given index and return the value of the vector 
        //at that index 
	public double get(int index) throws LinAlgException {
		// TODO (this should not return -1.0!)
                if (index >= _adVal.length) {
                    throw new LinAlgException ("array index out of bounds");
                } 
                else {
                    return _adVal[index];
                }
		
	}

	/** Set the value val of the vector at the given index (remember: array indices start at 0)
	 * 
	 * @param index
	 * @param val
	 * @throws LinAlgException if array index is out of bounds (see throw examples above)
	 */
        //if array inbounds, set the vector value at the given index to the given value
	public void set(int index, double val) throws LinAlgException {
		// TODO
            if (index >= _adVal.length) {
                throw new LinAlgException ("array index out of bounds");
            }
            else{
                _adVal[index]=val;
            }
                        
            
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
        //loop through the vector and add each element by a given scalar
        //returns nothing
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
	 * @return myvec - new Vector after scalar addition
	 */
        //create a new vector called myvec with elements from this
        //add a scalar to the new vector without modifying "this" 
        //by using the previous method
	public Vector scalarAdd(double d) {
		// TODO (this should not return null!)
            Vector myvec = new Vector(this);
                myvec.scalarAddInPlace(d);
		return myvec;
	}
	
	/** This multiplies a scalar d by all elements of *this* Vector
	 *  (should modify *this*)
	 * 
	 * @param d
	 */
        //loop through the vector
        //modify the "this" vector by multiplying the vector with a scalar
	public void scalarMultInPlace(double d) {
		// TODO
            for (int index = 0; index < _nDim; index++)
                _adVal[index] *= d;
	}
	
	/** This creates a new Vector, multiplies it by a scalar d, and returns it
	 *  (should not modify *this*)
	 * 
	 * @param d
	 * @return myVec
	 */
        //create a new vector with elements from this
        //multiply the given scalar with that vector without modifying "this" 
        //by using previous method
	public Vector scalarMult(double d) {
		// TODO (this should not return null!)
                Vector myVec = new Vector(this);
                myVec.scalarMultInPlace(d);
		return myVec;
	}
        

	/** Performs an elementwise addition of v to *this*, modifies *this*
	 * 
	 * @param v
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
        //changes "this" by adding a vector to it as long as the dimensions match
	public void elementwiseAddInPlace(Vector v) throws LinAlgException {
		// TODO
                
             if (v._nDim != this._nDim){
                 throw new LinAlgException("dimensions don't match");
        } else{
                 for (int i=0; i<v._nDim; i++){
                    _adVal[i]= _adVal[i] + v._adVal[i] ;
                 } 
             }
	}

	/** Performs an elementwise addition of *this* and v and returns a new Vector with result
	 * 
	 * @param v
	 * @return myVec - new vector
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
        
        //doesn't change this but instead creates a new vector 
        //which contains the added values of (this + given vector)
        //as long as the dimensions match
	public Vector elementwiseAdd(Vector v) throws LinAlgException {
		// TODO (this should not return null!)
                Vector myVec= new Vector(this);
                if (v._nDim != this._nDim){
                throw new LinAlgException("dimensions do not match");
            } else{
                
                myVec.elementwiseAddInPlace(v);
		return myVec; 
                }    
	}
	//"this" is an instance
	/** Performs an elementwise multiplication of *this* and v, modifies *this*
	 * 
	 * @param v
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
        //if dimensions match, using a loop multiply "this" by a vector 
	public void elementwiseMultInPlace(Vector v) throws LinAlgException {
		// TODO
            if (v._nDim != this._nDim){
                throw new LinAlgException("dimensions do not match");
            } else{
                for (int i=0; i<v._nDim; i++){
                    _adVal[i]= _adVal[i]*v._adVal[i];
                }
            }
               
             
                
	}

	/** Performs an elementwise multiplication of *this* and v and returns a new Vector with result
	 * 
	 * @param v
	 * @return myVec - new vector 
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
        //if dimensions match, multiply new vector by given vector and return that 
        //done by using previous method and without modifying this
	public Vector elementwiseMult(Vector v) throws LinAlgException {
		// TODO (this should not return null!)
                Vector myVec= new Vector(this);
                if (v._nDim != this._nDim){
                    throw new LinAlgException("dimensions do not match");
            } else{
                myVec.elementwiseMultInPlace(v);
		return myVec;
                }
	}

	/** Performs an inner product of Vectors v1 and v2 and returns the scalar result
	 * 
	 * @param v1
	 * @param v2
	 * @return product of the 2 vectors
	 * @throws LinAlgException
	 */
        //if v1 and v2 dim are same
        //using a loop, multiply two vectors and return a scalar(product) 
	public static double InnerProd(Vector v1, Vector v2) throws LinAlgException {
		// TODO (this should not return -1.0!)
                double product =0;
                if (v1._nDim != v2._nDim){
                    throw new LinAlgException ("Cannot innerProd vectors of different dimensions");
                }
                for (int i=0;i<v1._nDim; i++){
                    product = product + (v1._adVal[i]*v2._adVal[i]);
                }
		return product;
	}
}
