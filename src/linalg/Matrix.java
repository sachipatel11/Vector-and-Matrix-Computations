
package linalg;
import java.util.*;

/*** A class that represents a two dimensional real-valued (double) matrix
 *   and supports various matrix computations required in linear algebra.
 *   
 *   Class and method comments are in JavaDoc: https://en.wikipedia.org/wiki/Javadoc
 * 
 * @author scott.sanner@utoronto.ca, <YOUR_EMAIL> sachi.patel@mail.utoronto.ca
 *
 */
public class Matrix {

	public int _nRows; // Number of rows in this matrix; nomenclature: _ for data member, n for integer
	public int _nCols; // Number of columns in this matrix; nomenclature: _ for data member, n for integer
	// TODO: add your own data member to represent the matrix content
	//       you could use a 2D array, or an array of Vectors (e.g., for each row)
	public double [][] values; //creating empty matrix
	/** Create a new matrix of the given row and column dimensions
	 * 
	 * @param rows
	 * @param cols
	 * @throws LinAlgException if either rows or cols is <= 0
	 */
        //constructor : create matrix with dimension as long as rows&cols are > 0
        //assign variables to rows and columns
	public Matrix(int rows, int cols) throws LinAlgException {
		// TODO: hint: see the corresponding Vector constructor
                if (rows <=0 || cols <=0){
                    throw new LinAlgException("Matrix dimension cannot be less than 1");
                }
                _nRows = rows;
                _nCols = cols;
                values = new double [rows][cols];
                
                    
	}
	
	/** Copy constructor: makes a new copy of an existing Matrix m
	 *                    (note: this explicitly allocates new memory and copies over content)
	 * 
	 * @param m
	 */
        //constructor: creates copy of matrix m by looping  
        //and assigning each value from m = to new matrix (empty at first) 
	public Matrix(Matrix m) {
		// TODO: hint: see the corresponding Vector "copy constructor" for an example
                _nRows = m._nRows;
                _nCols = m._nCols;
                        
                values = new double[m._nRows][m._nCols];
                for (int i=0;i<m._nRows;i++){
                    for (int j=0;j<m._nCols;j++){
                        values[i][j]= m.values[i][j];
                    }
                }
	}

	/** Constructs a String representation of this Matrix
	 * 
	 */
        //empty string, append elements from matrix to empty string 
        //using a loop to create string version of matrix
	public String toString() {
		// Optional: hint: see Vector.toString() for an example
                StringBuilder sb = new StringBuilder();
    
                for (int i = 0; i < _nRows; i++){
                    sb.append("[");
          
                    for (int j=0; j< _nCols; j++){
                    sb.append(String.format(" %6.3f ", values[i][j])); // Append each vector value in order
                }
                    sb.append(" ]\n");
                }
		return sb.toString();

	}

	/** Tests whether another Object o (most often a matrix) is a equal to *this*
	 *  (i.e., are the dimensions the same and all elements equal each other?)
	 * 
	 * @param o the object to compare to
	 */
        //if the dimensions are equal loop through the rows and cols
        //if each value from the object and matrix is not same, return false
        //otherwise return true
	public boolean equals(Object o) {
		// Optional: hint: see Vector.equals(), you can also use Vector.equals() for checking equality 
		//             of row vectors if you store your matrix as an array of Vectors for rows
		
		// Optional: this should not always return 
                
                Matrix g = (Matrix) o;
                if ((_nRows!= g.getNumRows()) || (_nCols != g.getNumCols())){
                    return false;
                }
                for (int i=0; i<_nRows; i++){
                    for (int j=0; j<_nCols; j++){
                        if (values[i][j]!= g.values[i][j]){
                            return false;
                        }   
                    }    
                }
                return true;
                }

          

		// This should not always return false!
	
                
	/** Return the number of rows in this matrix
	 *   
	 * @return _nRows - # of rows 
	 */
        //return # of rows 
	public int getNumRows() {
		// TODO (this should not return -1!)
		return _nRows;
	}

	/** Return the number of columns in this matrix
	 *   
	 * @return _nCols - # of cols 
	 */
        //return # of columns
	public int getNumCols() {
		// TODO (this should not return -1!)
		return _nCols;
	}

	/** Return the scalar value at the given row and column of the matrix
	 * 
	 * @param row
	 * @param col
	 * @return value - (scalar value at given row and col of matrix)
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
        //as long as indices are in bound, return the value at given row & column
	public double get(int row, int col) throws LinAlgException {
		// TODO (this should not return -1!)
                //check conditions 
                if ((row<0 || row>=_nRows) || (col<0 ||_nCols<=col )){
                    throw new LinAlgException("indices are out of bounds");
                }
		return values[row][col] ;
	}
	
	/** Return the Vector of numbers corresponding to the provided row index
	 * 
	 * @param row
	 * @return vec - (vector that contains elements from the row of the matrix)
	 * @throws LinAlgException if row is out of bounds
	 */
        //create new vector called vec with initial dimension = to row (index)
        //as long as given row is in bounds, loop through and set new vector
        //equal to values from the given index of the row
	public Vector getRow(int row) throws LinAlgException {
		// TODO (this should not return null!)
                Vector vec = new Vector(row);
                if (0<=row && row<=_nRows){
                    throw new LinAlgException("row is out of bounds");
                }else {
                    for (int i=0; i<=row;i++){
                        vec.set(row,values[row][i]); 
                    } return vec;
                }
		
	}

	/** Set the row and col of this matrix to the provided val
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
        //as long as index in bounds, set element at given row/col to given value
	public void set(int row, int col, double val) throws LinAlgException {
		// TODO
	 if ((row<0 || row>=_nRows) || (col<0 || col>=_nCols )){
        throw new LinAlgException("indices are out of bounds");
         } else{
             values[row][col]=val;
         }
            
}
	
	/** Return a new Matrix that is the transpose of *this*, i.e., if "transpose"
	 *  is the transpose of Matrix m then for all row, col: transpose[row,col] = m[col,row]
	 *  (should not modify *this*)
	 * 
	 * @return transpose
	 * @throws LinAlgException
	 */
	public Matrix transpose() throws LinAlgException {
		Matrix transpose = new Matrix(_nCols, _nRows);
		for (int row = 0; row < _nRows; row++) {
			for (int col = 0; col < _nCols; col++) {
				transpose.set(col, row, get(row,col));
			}
		}
		return transpose;
	}

	/** Return a new Matrix that is the square identity matrix (1's on diagonal, 0's elsewhere) 
	 *  with the number of rows, cols given by dim.  E.g., if dim = 3 then the returned matrix
	 *  would be the following:
	 *  
	 *  [ 1 0 0 ]
	 *  [ 0 1 0 ]
	 *  [ 0 0 1 ]
	 * 
	 * @param dim
	 * @return myMatrix - (identity matrix of a given dimension)
	 * @throws LinAlgException if the dim is <= 0
	 */
        //create new matrix 
        //as long as dim>0, using loop, fill in new matrix (if row=col, put 1 there)
        //otherwise put a 0 at all other positions
	public static Matrix GetIdentity(int dim) throws LinAlgException {
		// TODO: this should not return null!
                // using constructor to create new matrix object
                Matrix myMatrix = new Matrix(dim,dim);
		if (dim<=0){
                    throw new LinAlgException("dimension can't be 0 or less than 0");
                }else{
                    for (int i=0;i<dim;i++){
                        for (int j=0;j<dim;j++){
                          if (i==j){
                              myMatrix.values[i][j]=1;
                          }else {
                              myMatrix.values[i][j]=0;
                          }  
                        }  
                    }return myMatrix; 
                }
        }
       
                
	
	/** Returns the Matrix result of multiplying Matrix m1 and m2
	 *  (look up the definition of matrix multiply if you don't remember it)
	 * 
	 * @param m1
	 * @param m2
	 * @return m3 (matrix that is a result of multiplying 2 matrices
	 * @throws LinAlgException if m1 columns do not match the size of m2 rows
	 */
        //create new matrix
        //as long as m1 col match size of m2 rows
        //loop through and make new matrix elements = m1*m2 (val1*val2 + val3*val4...)
	public static Matrix Multiply(Matrix m1, Matrix m2) throws LinAlgException {
		// TODO: this should not return null!
                Matrix m3= new Matrix(m1._nRows,m2._nCols);
                if (m1._nCols != m2._nRows){
                    throw new LinAlgException("m1 columns don't match the size of m2 rows");
                }else{
                for (int i =0; i<m1._nRows;i++){
                    for (int j=0;j<m2._nCols ;j++){
                        for (int k=0; k<m1._nCols; k++){
                            m3.values[i][j]+=m1.values[i][k]*m2.values[k][j];
                        } 
                    }
                }return m3;
                }
                
		
	}
		
	/** Returns the Vector result of multiplying Matrix m by Vector v (assuming v is a column vector)
	 * 
	 * @param m
	 * @param v
	 * @return vec -(new vector that is a result of matrix multiplied by given vector)
	 * @throws LinAlgException if m columns do match the size of v
	 */
        //create new vector
        //if colums match size of v
        //use a loop and a variable val = 0
        //val = matrix values * vector value of first plus previous position
        //using set method, set vector at that row to value then move to next row
	public static Vector Multiply(Matrix m, Vector v) throws LinAlgException {
		// TODO: this should not return null!
                Vector vec = new Vector(m._nRows);
                if (m._nCols != v._nDim){
                    throw new LinAlgException("m columns don't match the size of v");
                }else{
                    for (int i=0;i<m._nRows;i++){
                        double val=0;
                        for (int j=0;j<m._nCols; j++){
                            val+=m.values[i][j]*v._adVal[j];
                            
                        }vec.set(i,val);
                    }return vec;
                }
		
	}

}
