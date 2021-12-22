package com.company;

import java.util.Scanner;

// Function to get cofactor of A[p][q] in temp[][]. n is current
// dimension of A[][]
public class hill{
static void getCofactor(int A[][], int temp[][], int p, int q, int n)
        {
        int i = 0, j = 0;
        // Looping for each element of the matrix
        for (int row = 0; row < n; row++)
        {
        for (int col = 0; col < n; col++)
        {
        // Copying into temporary matrix only those element
        // which are not in given row and column
        if (row != p && col != q)
        {
        temp[i][j++] = A[row][col];

        // Row is filled, so increase row index and
        // reset col index
        if (j == n - 1)
        {
        j = 0;
        i++;
        }
        }
        }
        }
        }
public static int determinant(int A[][], int n)
        {
        int D = 0; // Initialize result

        // Base case : if matrix contains single element
        if (n == 1)
        return A[0][0];

        int [][]temp = new int[A.length][A[0].length]; // To store cofactors

        int sign = 1; // To store sign multiplier
        // Iterate for each element of first row

        for (int f = 0; f < n; f++)
        {
        // Getting Cofactor of A[0][f]
        getCofactor(A, temp, 0, f, n);
        D += sign * A[0][f] * determinant(temp, n - 1);

        // terms are to be added with alternate sign
        sign = -sign;
        }

        return D;
        }
public static int[][] transpose(int[][] a){
        int trans[][] = new int[a.length][a[0].length];
        //to flip each row to column and column to row
        for(int i=0;i<a.length;i++){
        for(int j=0;j<a[0].length;j++){
        trans[i][j] = a[j][i];
        }
        }
        return trans;
        }
public static int[][] adjoint(int A[][]) throws Non_SquareMatrixException
        {
        int [][]Dest = new int[A.length][A.length];
        if (A.length == 1)
        {
        throw new Non_SquareMatrixException("Adjoint exists only for square matrix!");
        }

        // temp is used to store cofactors of A[][]
        int sign = 1;
        int [][]temp = new int[A.length][Dest[0].length];

        for (int i = 0; i < A.length; i++)
        {
        for (int j = 0; j < A[0].length; j++)
        {
        // Get cofactor of A[i][j]
        getCofactor(A, temp, i, j, A.length);

        // sign of adj[j][i] positive if sum of row
        // and column indexes is even.
        sign = ((i + j) % 2 == 0)? 1: -1;

        // Interchanging rows and columns to get the
        // transpose of the cofactor matrix
        Dest[j][i] = (sign)*(determinant(temp, A.length-1));
        }
        }
        return Dest;
        }
public static void modifyMatrix(int A[][],int mod){
        //to keep range of values within value
        for(int i = 0;i<A.length;i++){
        for(int j=0;j<A.length;j++){
        A[i][j] = ((A[i][j]%mod)+mod)%mod;
        }
        }
        }
public static void modifyMatrix(int A[],int mod){

        for(int i=0;i<A.length;i++)A[i] = ((A[i]%mod)+mod)%mod;
        }
public static int[][] inverse(int A[][], int mod) throws DeterminantZeroException,Non_SquareMatrixException{
        int det = determinant(A, A.length);
        det = (det+mod)%mod;
        if (det == 0)
        {
        throw new DeterminantZeroException("determinant zero error -Singular matrix");
        }
        // Find adjoint
        int [][]adj = adjoint(A);

        int mui = mui(((det%mod)+mod)%mod,mod);

        scalarMultiply(adj,mui,mod);
        return adj;
        }
    public static int gcd(int a,int b){
        if (b == 0)
            return a;
        else
            return gcd(b, (a % b));
    }

    public static int mui(int a,int mod){
        for (int x = 2; x < mod; x++)
            if (((a%mod) * (x%mod)) % mod == 1)
                return x;
        return 1;
    }

    public static int[] multiply(int[][] b, int[] a){
        int ans[] = new int[b.length];
        for(int i=0;i<a.length;i++){
        for(int j=0;j<b.length;j++){
        ans[i] += a[j]*b[i][j];
        }
        }
        return ans;
        }

public static int[][] multiply(int a[][],int[][] b){
        int[][] ans = new int[a.length][b[0].length];
        if(a[0].length!=b.length)return  ans;
        for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < b.length; k++)
        ans[i][j] += a[i][k] * b[k][j];
        }
        }
        return ans;
        }

public static void scalarMultiply(int[][] a,int scalar){
        for(int i=0;i<a.length;i++){
        for(int j=0;j<a[0].length;j++){
        a[i][j] = a[i][j]*scalar;
        }
        }
        }
public static void scalarMultiply(int[][] a,int scalar,int mod){
        scalarMultiply(a,scalar);
        modifyMatrix(a,mod);
        }
float[][] inverse(int A[][]) throws DeterminantZeroException,Non_SquareMatrixException
        {
        float [][]inverse = new float[A.length][A.length];
        // Find determinant of A[][]
        int det = determinant(A, A.length);
        if (det == 0)
        {
        throw new DeterminantZeroException("determinant zero error -Singular matrix");
        }
        // Find adjoint
        int [][]adj = adjoint(A);


        // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
        for (int i = 0; i < A.length; i++)
        for (int j = 0; j < A.length; j++)
        inverse[i][j] = adj[i][j]/(float)det;

        return inverse;
        }
public static void printMatrix(int a[][]){
        //Outer loop through rows
        //inner loop through columns
        for(int i=0;i<a.length;i++){
        for(int j=0;j<a[0].length;j++){
        System.out.print(a[i][j]+" ");
        }
        System.out.println();
        }
        System.out.println();
        }
public static void printMatrix(int a[]){
        for(int i=0;i<a.length;i++){
        System.out.print(a[i]+" ");
        }
        System.out.println();
        }
    //mod value and numbering of alphabets from diff
    private static int mod = 26,diff=1;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;//condition for while loop if true exit
        System.out.println("1.Encryption\n2.Decryption\n3.Plain cipher Attack");
        while(true) {
            try {
                if (exit) break;
                System.out.print("choice :");
                int choice = scan.nextInt();//get the choice from user
                scan.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Key:");
                        String key = scan.nextLine().toLowerCase(); //get key and convert to lower case
                        System.out.print("Plain Text:");
                        String pt = scan.nextLine().toLowerCase();  //get plain text and convert to lower case
                        System.out.println(encrypt(key, pt));       //start enryption of plaintext with key
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("Key:");                   //get key and convert to lower case
                        key = scan.nextLine().toLowerCase();
                        System.out.print("Cipher Text:");
                        String ct = scan.nextLine().toLowerCase();  //get cipher text and convert to lower case
                        System.out.println(decrypt(key, ct));
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("Cipher Text:");
                        ct = scan.nextLine().toLowerCase();         //get cipher text and convert to lower case
                        System.out.print("Plain Text:");
                        pt = scan.nextLine().toLowerCase();         //get plain text and convert to lower case
                        System.out.print("Key:");
                        int k[][] = knownPlainCipherAttack(pt, ct); //start KPC attack with cipher and plain text
                        String ky = "";
                        for (int i = 0; i < k.length; i++) {        //convert the key to text
                            int temp[] = new int[k.length];
                            for(int j=0;j<k.length;j++) temp[j] = k[j][i];
                            ky += charEncode(temp);
                        }
                        System.out.println(ky);
                        break;
                    case 4:
                        exit = true;                                //set exit to true to break the loop
                        break;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public static int[][] knownPlainCipherAttack(String plain,String cipher)throws Exception{
        int[][] key,pt,ct;
        pt = numkEncode(plain,true);               //convert text to its positional number to array
        ct = numkEncode(cipher,false);
        pt = inverse(pt,mod);modifyMatrix(pt,mod);      //get inverse of plain text
        key = multiply(ct,pt);                          //multiply cipher text and inverse of plain text
        modifyMatrix(key,mod);
        return key;
    }
    private static int[] numEncode(String s){
        int k=0;
        int[] key = new int[s.length()];
        for(int i=0;i<s.length();i++){
            key[i] = (s.charAt(k++)+diff-'a')%mod;  //positional number for given character
        }
        return key;
    }
    private static int[][] numkEncode(String s,boolean isKey) throws Exception{
        int len ;
        len= (s.length()<=4)?2:3;
        int x=len*len-s.length();                       //if key length is not a square of 2 or 3 throw erro
        if(x>0) throw new KeyError("Enter 4/9 letter key!");
        int[][] key = new int[len][len];
        int k=0;
        for(int i=0;i<len;i++){
            for (int j=0;j<len;j++)
                key[j][i] = (s.charAt(k++)+diff-'a')%mod;   //positional number for given character
        }
        int det = determinant(key, key.length);         //determinant of positional array

        if(isKey && !keyvalidation(key)){               //check is key is valid for findind inverse
            throw new KeyError("Inverse key cannot be found with this key,choose different One! :(");
        }

        return key;
    }
    public static boolean keyvalidation(int keymatrix[][]) {
        int d = determinant(keymatrix,keymatrix.length) % 26;
        if (d == 0) return  false;  //Invalid key!!! Key is not invertible because determinant=0...

        else if (d % 2 == 0 || d % 13 == 0) return false; //Invalid key!!! Key is not invertible because determinant has common factor with 26...

        return true;    //valid key
    }

    private static String charEncode(int[] a){
        String plain = "";
        for(int i=0;i<a.length;i++){
            plain+=(char)(((a[i]-diff)%mod+mod)%mod+'a');//get string of plain text from positional array
        }
        return plain;
    }
    public static String encrypt(String ky,String p) throws Exception{
        int[][] key = numkEncode(ky,true);                          //plain text to positional array conversion

        String Hillcipher = "";
        String pt = p;
        if(key.length==2){
            if(p.length()<2)pt+='x';                                    //padding additional characters to match the row size of key
            else for(int i=0;i<pt.length()%2;i++) pt+='x';
        }
        else if(key.length==3){
            if(p.length()<2)pt+="xx";
            else if(p.length()<3)pt+='x';
            else for(int i=0;i<pt.length()%3;i++) pt+='x';
        }
        int[] plain;
        for(int i=0;i<pt.length();i+= key.length){                      //loop through each block size
            plain = numEncode(pt.substring(i,i+ key.length));
            plain = multiply(key,plain);                                //encrypt plain to cipher text by blocks
            modifyMatrix(plain,mod);
            Hillcipher+=charEncode(plain);                              //convert ecrypted positional array block to plain text and append to cipher text
        }
        return Hillcipher;                                              //return fully incrypted text of given plain text and key
    }
    public static String decrypt(String ky,String p) throws Exception{
        int[][] key = numkEncode(ky,true);                         //plain key text to positional array conversion
        int[][] ikey = inverse(key,mod);                                //get inverse of key array
        String plainText = "";
        String pt = p;
        if(key.length==2){
            if(p.length()<2)pt+='x';                                    //padding additional characters to match the row size of key
            else for(int i=0;i<pt.length()%2;i++) pt+='x';
        }
        else if(key.length==3){
            if(p.length()<2)pt+="xx";
            else if(p.length()<3)pt+='x';
            else for(int i=0;i<pt.length()%3;i++) pt+='x';
        }
        int[] plain;
        printMatrix(ikey);
        for(int i=0;i<pt.length();i+= key.length){
            plain = numEncode(pt.substring(i,i+ key.length));
            plain = multiply(ikey,plain);                               //multiply inverse of key with given cipher text
            modifyMatrix(plain,mod);
            plainText+=charEncode(plain);
        }
        return plainText;                                               //return deprypted plain text
    }
}
class KeyError extends Exception{
    KeyError(String s){
        super(s);
    }
}

class DeterminantZeroException extends Exception{
    DeterminantZeroException(String s){
        super(s);
    }
}
class Non_SquareMatrixException extends Exception{
    Non_SquareMatrixException(String s){
        super(s);
    }
}
