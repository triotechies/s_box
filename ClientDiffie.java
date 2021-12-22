package diffie;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;
import java.util.ArrayList;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class ClientDiffie {
    static DataInputStream in;
    static DataOutputStream out;
    static Socket s;
    static ArrayList primitiveRoots(BigInteger q){
        BigInteger pi = q.subtract(BigInteger.ONE);
        ArrayList<BigInteger> powers = new ArrayList<BigInteger>();
        ArrayList<BigInteger> roots = new ArrayList<BigInteger>();
        for (int i=2; i<100; i++){
            if (BigInteger.valueOf(i).isProbablePrime(1) && pi.mod(BigInteger.valueOf(i)).compareTo(BigInteger.ZERO) == 0){
                powers.add(pi.divide(BigInteger.valueOf(i)));
            }        
        }
        for (int i=2; i<100; i++){
            int flag = 0;
            for (BigInteger j : powers){
                if (BigInteger.valueOf(i).modPow(j, q).compareTo(BigInteger.ONE) == 0){
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                roots.add(BigInteger.valueOf(i));
        }
        return roots;
    }
    public ClientDiffie(String ip, int port) throws Exception{
        Scanner sc = new Scanner(System.in);
        while (true){
            while (true){
                try{
                    s = new Socket(ip, port);
                    break;
                }
                catch(Exception e){continue;}
            }
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            System.out.println("Enter operation\n1.key exchange\n2.discrete log\n3.man in middle\n");
            int choice = sc.nextInt();
            out.writeUTF(choice + "");      
            if (choice == 1){
                BigInteger q = BigInteger.probablePrime(400, new Random());
                System.out.println("PRIME NUMBER: " + q);
                ArrayList roots = primitiveRoots(q);
                System.out.println("THE PRIMITIVE ROOTS ARE: ");
                System.out.println(roots);
                System.out.println("Select any one of the roots: ");
                BigInteger root = new BigInteger(sc.next());
                out.writeUTF(q + "");
                out.writeUTF(root + "");
                System.out.println("Enter xa: ");
                BigInteger xa = new BigInteger(sc.next());
                BigInteger ya = root.modPow(xa, q);
                out.writeUTF(ya + "");
                BigInteger yb = new BigInteger(in.readUTF());
                BigInteger key = yb.modPow(xa, q);
                System.out.println("key: " + key);    
            }
            else if (choice == 2){
                BigInteger q = BigInteger.probablePrime(400, new Random());
                // BigInteger q = new BigInteger(sc.next());
                ArrayList roots = primitiveRoots(q);
                System.out.println("Roots are: " + roots);
                BigInteger root = new BigInteger(sc.next());
                System.out.println("Enter xa: ");
                BigInteger xa = new BigInteger(sc.next());
                BigInteger ya = root.modPow(xa, q);
                out.writeUTF(ya + "");
                out.writeUTF(q + "");
                out.writeUTF(root + "");
                System.out.println("q: " + q);
                System.out.println("ya: " + ya);
            }
            else if (choice == 3){
                s.close();
                s = new Socket(ip, 3500);
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
                BigInteger q = BigInteger.probablePrime(400, new Random());
                System.out.println("PRIME NUMBER: " + q);
                ArrayList roots = primitiveRoots(q);
                System.out.println("THE PRIMITIVE ROOTS ARE: ");
                System.out.println(roots);
                System.out.println("Select any one of the roots: ");
                BigInteger root = new BigInteger(sc.next());
                out.writeUTF(q + "");
                out.writeUTF(root + "");
                System.out.println("Enter xa: ");
                BigInteger xa = new BigInteger(sc.next());
                BigInteger ya = root.modPow(xa, q);
                out.writeUTF(ya + "");
                BigInteger yc = new BigInteger(in.readUTF());
                BigInteger key = yc.modPow(xa, q);
                System.out.println("key: " + key); 
                Thread.sleep(2000); 
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new ClientDiffie("localhost", 4200);
            
    }
}
