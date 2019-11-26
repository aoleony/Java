package matrix;

public class Matrix {

    private double[][] matrix;
    private int m;//rows 
    private int n; //columns

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        m = matrix.length; //rows
        n = matrix[0].length;//columns
    }

    public Matrix(int m, int n) {
        this.matrix = new double[m][n];
        this.m = m;
        this.n = n;
    }

    public void displayMatrix() {
        for (double[] matrix1 : matrix) {
            System.out.print("| ");
            for (int j = 0; j < matrix1.length; j++) {
                System.out.print(matrix1[j] + " ");
            }
            System.out.println("|");
        }
    }

    public void loadMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i] = matrix[i];
            }
        }
    }

    public double[][] getMatrix(Matrix matrix) {
        return matrix.matrix;
    }

    public int getRows() {
        return m;
    }

    public int getColumns() {
        return n;
    }

    public Matrix addMatrix(Matrix MatrixAdded) {
        double[][] matrixAdded = MatrixAdded.getMatrix(MatrixAdded);
        double[][] newMatrix = new double[m][n];
        boolean goodMatrixOrNot = true;

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[i].length; j++) {
                if ((m == matrixAdded.length) && (n == matrixAdded[i].length)) {
                    newMatrix[i][j] = (matrixAdded[i][j]) + (this.matrix[i][j]);
                } else {
                    goodMatrixOrNot = false;
                    break;
                }
            }
        }
        if (goodMatrixOrNot == true) {
            return new Matrix(newMatrix);
        } else {
            System.out.println("Not a good matrix to add!");
            return new Matrix(this.matrix);
        }
    }

    public Matrix subtractMatrix(Matrix matrix) {
        double[][] matrixSubtracted = matrix.getMatrix(matrix);
        double[][] newMatrix = new double[m][n];
        boolean goodMatrixOrNot = true;

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[i].length; j++) {
                if ((m == matrixSubtracted.length) && (n == matrixSubtracted[i].length)) {
                    newMatrix[i][j] = (this.matrix[i][j]) - (matrixSubtracted[i][j]);
                } else {
                    goodMatrixOrNot = false;
                    break;
                }
            }
        }
        if (goodMatrixOrNot == true) {
            return new Matrix(newMatrix);
        } else {
            System.out.println("Not a good matrix to subtract!");
            return new Matrix(this.matrix);
        }
    }

    public Matrix multiplyMatrix(Matrix matrixMultiplied) {
        double[][] matrix = matrixMultiplied.getMatrix(matrixMultiplied);//matrix that is getting multiplied
        double[][] newMatrix;
        int m2 = matrixMultiplied.getRows();// new rows
        int n2 = matrixMultiplied.getColumns();// new columns

        if (n == m2) {
            newMatrix = new double[m][n2];//if it's ok, create a matrix
            //new matrix to be formed
            for (int rows = 0; rows < m; rows++) {
                for (int cols = 0; cols < n2; cols++) {
                    for (int k = 0; k < n; k++) {
                        newMatrix[rows][cols] += this.matrix[rows][k] * matrix[k][cols];
                    }
                }
            }
            return new Matrix(newMatrix);
        } else {
            System.out.println("Not a good matrix to multiply!");
            return new Matrix(this.matrix);
        }
    }

    public Matrix rotateMatrix(double theta) {
        double angleRadians = Math.toRadians(theta);
        double[][] rotation = {{Math.cos(angleRadians), -Math.sin(angleRadians)}, {Math.sin(angleRadians), Math.cos(angleRadians)}};
        Matrix rotationMatrix = new Matrix(rotation);
        Matrix newMatrix = new Matrix(this.matrix);
        return rotationMatrix.multiplyMatrix(newMatrix);
    }

    public Matrix inverseMatrix() {
        double a = this.matrix[0][0];
        double b = this.matrix[0][1];
        double c = this.matrix[1][0];
        double d = this.matrix[1][1];
        double determinant = a * d - b * c;
        double[][] inversedMatrix = new double[2][2];

        inversedMatrix[0][0] = d / determinant;
        inversedMatrix[1][1] = a / determinant;
        inversedMatrix[0][1] = -b / determinant;
        inversedMatrix[1][0] = -c / determinant;

        return new Matrix(inversedMatrix);
    }

    public Matrix shearMatrix(double k, boolean horizontal) {
        if (horizontal) {
            double[][] shearedMatrix = {{1, k}, {0, 1}};
            Matrix m = new Matrix(shearedMatrix);
            Matrix n = new Matrix(this.matrix);
            return m.multiplyMatrix(n);
        } else {
            double[][] shearedMatrix = {{1, 0}, {k, 1}};
            Matrix m = new Matrix(shearedMatrix);
            Matrix n = new Matrix(this.matrix);
            return m.multiplyMatrix(n);
        }
    }

    public Matrix stretchMatrix(double k, boolean horizontal) {
        if (horizontal) {
            double[][] shearedMatrix = {{k, 0}, {0, 1}};
            Matrix m = new Matrix(shearedMatrix);
            Matrix n = new Matrix(this.matrix);
            return m.multiplyMatrix(n);
        } else {
            double[][] shearedMatrix = {{1, 0}, {0, k}};
            Matrix m = new Matrix(shearedMatrix);
            Matrix n = new Matrix(this.matrix);
            return m.multiplyMatrix(n);
        }
    }

    public static void main(String[] args) {
        double[][] array1 = {{2, 3, 1}, {2, -7, 4}};
        double[][] array2 = {{3, 4, 5}, {1, 1, 4}, {2, 1, 4}};
        double[][] array3 = {{3}, {4}};
        double[][] array4 = {{0, 2, 0, 2}, {0, 0, 2, 2}};
        double[][] array5 = {{1, 3, 1}, {1, 1, 3}};

        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);
        Matrix matrix3 = new Matrix(array3);
        Matrix matrix4 = null;
        Matrix matrix5 = null;
        Matrix matrix6 = new Matrix(array4);
        Matrix matrix7 = null;
        Matrix matrix8 = new Matrix(array5);
        Matrix matrix9 = null;
        Matrix matrix10 = null;

        matrix1.displayMatrix();
        System.out.println();

        matrix10 = matrix1.addMatrix(matrix8);
        matrix10.displayMatrix();
        System.out.println();

        matrix4 = matrix1.multiplyMatrix(matrix2);
        matrix4.displayMatrix();
        System.out.println();

        matrix5 = matrix3.rotateMatrix(30);
        matrix5.displayMatrix();
        System.out.println();

        matrix7 = matrix6.shearMatrix(1.5, true);
        matrix7.displayMatrix();
        System.out.println();

        matrix9 = matrix8.stretchMatrix(3, false);
        matrix9.displayMatrix();
    }
}
