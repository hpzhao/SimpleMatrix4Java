import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix{
    private double [][] A;
    private int m = 0, n = 0;

    /**
     * Construction Function
     * @param rows
     * @param cols
     */
    public Matrix(int rows, int cols){
        m = rows;
        n = cols;
        A = new double[m][n];
    }

    public Matrix(double [][] B){
        m = B.length;
        n = B[0].length;

        for (int i = 0; i < m; i++) {
            if (B[i].length != n) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }
        this.A = B.clone();
    }

    public Matrix (int rows, int cols, double number) {
        this.m = rows;
        this.n = cols;
        A = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = number;
            }
        }
    }
    public Matrix(int[] shape, double number){
        this.m = shape[0];
        this.n = shape[1];
        A = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = number;
            }
        }
    }


    public Matrix(List<? extends Number> list) {
        assert list.size() != 0 : "the size of data is zero!";
        m = list.size();
        n = 1;
        A = new double[m][n];
        for (int i = 0; i < m; ++i) {
            A[i][0] = list.get(i).doubleValue();
        }
    }

    public static Matrix ones(int row, int col){return new Matrix(row,col,1.0);}
    public static Matrix onesLike(Matrix x){ return new Matrix(x.shape(),1.0);}
    public static Matrix zeros(int row, int col){return new Matrix(row,col,0.0);}
    public static Matrix zerosLike(Matrix x){return new Matrix(x.shape(),0.0);}
    public static Matrix random (int m, int n) {
        Matrix A = new Matrix(m,n);
        double[][] X = A.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                X[i][j] = Math.random();
            }
        }
        return A;
    }


    public int shape(int pos){ return pos == 0 ? m : n; }
    public int[] shape(){ return new int[]{m,n}; }
    /**
     * Copy Function
     * @return
     */
    public Matrix copy () {
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j];
            }
        }
        return X;
    }

    /**
     * get,set,print function
     * @return
     */

    public double[][] getArray () { return A; }
    public void set(int row, int col, double num){ this.A[row][col] = num; }
    public void set(int[] pos,double num){A[pos[0]][pos[1]] = num;}
    public double get(int row, int col){ return this.A[row][col]; }

    public String toString(){
        String res = "[" + Arrays.toString(A[0]);
        if(m == 1) return res += "]"; else res += "\n";
        for(int i = 1; i < m - 1; ++i)
            res += " " + Arrays.toString(A[i]) + '\n';
        res += " " + Arrays.toString(A[m-1]) + "]" + "\n";
        return res;
    }

    /**
     * Linear operation
     * @param
     */

    public Matrix add(double number){
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + number;
            }
        }
        return X;
    }
    public Matrix add(Matrix B){
        checkMatrixDimensions(B);
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B.A[i][j];
            }
        }
        return X;
    }
    public Matrix addi(double number){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = A[i][j] + number;
            }
        }
        return this;
    }
    public Matrix addi(Matrix B){
        checkMatrixDimensions(B);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = A[i][j] + B.A[i][j];
            }
        }
        return this;
    }

    public Matrix sub(double number){
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - number;
            }
        }
        return X;
    }
    public Matrix sub(Matrix B){
        checkMatrixDimensions(B);
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B.A[i][j];
            }
        }
        return X;
    }
    public Matrix subi(double number){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = A[i][j] - number;
            }
        }
        return this;
    }
    public Matrix subi(Matrix B){
        checkMatrixDimensions(B);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = A[i][j] - B.A[i][j];
            }
        }
        return this;
    }

    public Matrix mul(double number){
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] * number;
        return X;
    }
    public Matrix mul(Matrix B){
        assert B.n == 1: "not a vector,please use mmul()";
        checkMatrixDimensions(B);
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] * B.A[i][j];
        return X;
    }

    public Matrix muli(double number){
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = A[i][j] * number;
        return this;
    }
    public Matrix muli(Matrix B){
        checkMatrixDimensions(B);
        assert B.n == 1: "not a vector,please use mmul()";
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = A[i][j] * B.A[i][j];
        return this;
    }

    public Matrix div(double number){
        assert !DoubleEqual(number,0) : "divisor can't be zero!";
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] / number;
        return X;
    }

    public Matrix div(Matrix B){
        checkMatrixDimensions(B);
        Matrix X = new Matrix(m,n);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++){
                assert !DoubleEqual(B.A[i][j],0) : "divisor can't be zero!";
                C[i][j] = A[i][j] / B.A[i][j];
            }

        return X;
    }

    public Matrix divi(double number){
        assert !DoubleEqual(number,0) : "divisor can't be zero!";
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = A[i][j] / number;
        return this;
    }

    public Matrix divi(Matrix B){
        checkMatrixDimensions(B);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++){
                assert !DoubleEqual(B.A[i][j],0);
                A[i][j] = A[i][j] / B.A[i][j];
            }

        return this;
    }

    public Matrix mmul(Matrix B){
        if (B.m != n) {
            throw new IllegalArgumentException("Matrix inner dimensions must agree.");
        }
        Matrix X = new Matrix(m,B.n);
        double[][] C = X.getArray();
        double[] Bcolj = new double[n];
        for (int j = 0; j < B.n; j++) {
            for (int k = 0; k < n; k++) {
                Bcolj[k] = B.A[k][j];
            }
            for (int i = 0; i < m; i++) {
                double[] Arowi = A[i];
                double s = 0;
                for (int k = 0; k < n; k++) {
                    s += Arowi[k]*Bcolj[k];
                }
                C[i][j] = s;
            }
        }
        return X;
    }

    public Matrix transpose(){
        Matrix X = new Matrix(n,m);
        double[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[j][i] = A[i][j];
            }
        }
        return X;
    }

    public double norm1 () {
        assert n == 1 || m == 1: "not a vector!";
        double f = 0;
        if(n == 1) {
            for (int i = 0; i < m; i++)
                f += Math.abs(A[i][0]);
        }else
        {
            for (int i = 0; i < n; i++)
                f += Math.abs(A[0][i]);
        }
        return f;
    }

    public double norm2 () {
        assert n == 1 || m == 1: "not a vector!";
        double f = 0;
        if(n == 1){
            for (int i = 0; i < m; i++)
                f += Math.pow(A[i][0],2);
        }else{
            for (int i = 0; i < n; i++)
                f += Math.pow(A[0][i],2);
        }
        return Math.sqrt(f);
    }

    public double squaredDistance(){
        assert n == 1 || m == 1: "not a vector!";
        double f = 0;
        if(n == 1){
            for (int i = 0; i < m; i++)
                f += Math.pow(A[i][0],2);
        }else{
            for (int i = 0; i < n; i++)
                f += Math.pow(A[0][i],2);
        }
        return f;
    }

    public double sum(){
        double f = 0;
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                f += A[i][j];
        return f;
    }
    public static Matrix max(Matrix X,Matrix Y){
        assert X.m == Y.m && X.n == Y.n : "the shape of Matrix X,Y must be same!";
        Matrix Z = new Matrix(X.m,X.n);
        for(int i = 0;i < X.m; ++i)
            for(int j = 0;j < X.n; ++j)
                Z.A[i][j] = Math.max(X.A[i][j],Y.A[i][j]);
        return Z;
    }
    public static Matrix max(Matrix B, double number){
        Matrix X = new Matrix(B.m,B.n);
        for(int i = 0;i < B.m; ++i)
            for(int j = 0;j < B.n; ++j)
                X.A[i][j] = Math.max(B.A[i][j],number);
        return X;
    }

    public static Matrix min(Matrix X,Matrix Y){
        assert X.m == Y.m && X.n == Y.n : "the shape of Matrix X,Y must be same!";
        Matrix Z = new Matrix(X.m,X.n);
        for(int i = 0;i < X.m; ++i)
            for(int j = 0;j < X.n; ++j)
                Z.A[i][j] = Math.min(X.A[i][j],Y.A[i][j]);
        return Z;
    }
    public static Matrix min(Matrix B, double number){
        Matrix X = new Matrix(B.m,B.n);
        for(int i = 0;i < B.m; ++i)
            for(int j = 0;j < B.n; ++j)
                X.A[i][j] = Math.min(B.A[i][j],number);
        return X;
    }

    public static Matrix concat(int dimension, Matrix ... toConcat){
        assert dimension >= 0:"dimension must lagger than 0";
        assert toConcat.length > 0:"the number of Matrix must lagger than 0!!";
        int m = toConcat[0].m, n=toConcat[0].n;
        int concat_dim = 0;
        for(Matrix matrix : toConcat){
            if(dimension == 0) {
                concat_dim += matrix.m;
                assert matrix.n == n : "cols of matrix must be same!!";
            }
            else{
                concat_dim += matrix.n;
                assert matrix.m == m:"rows of matrix must be same!!";
            }

        }
        if(dimension == 0){
            Matrix X = new Matrix(concat_dim,n);
            int cur_dim = 0;
            for(Matrix matrix : toConcat){
                for(int i = 0; i < matrix.m; i++) {
                    for (int j = 0; j < X.n; j++)
                        X.A[cur_dim][j] = matrix.A[i][j];
                    cur_dim += 1;
                }
            }
            return X;
        }else{
            Matrix X = new Matrix(m,concat_dim);
            int cur_dim = 0;
            for(Matrix matrix : toConcat){
                for(int j = 0; j < matrix.n; j++) {
                    for (int i = 0; i < X.m; i++) {
                        X.A[i][cur_dim] = matrix.A[i][j];
                    }
                    cur_dim += 1;
                }
            }
            return X;
        }
    }

    public double toDouble(){
        assert m == 1 && n == 1 : "can't convert this Matrix to Double";
        return A[0][0];
    }

    public double[] toDoubleVector() {
        assert m == 1 || n ==1 : "can't convert this Matrix to Double vector";
        double[] result = new double[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i*n+j] = A[i][j];
            }
        }
        return result;
    }

    private boolean DoubleEqual(double a,double b){
        return Math.abs(a-b) < 1e-8;
    }
    private void checkMatrixDimensions (Matrix B) {
        if (B.m != m || B.n != n) {
            throw new IllegalArgumentException("Matrix dimensions must agree.");
        }
    }
}

