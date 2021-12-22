package diffie;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;
import java.util.ArrayList;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerDiffie {
    ServerSocket ss;
    Socket s;
    DataOutputStream out;
    DataInputStream in;
    public ServerDiffie(int port) throws Exception{
        Scanner sc = new Scanner(System.in);
        ss = new ServerSocket(port);
        while (true){
            s = ss.accept();
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
            int choice = Integer.parseInt(in.readUTF());
            if (choice == 1){
                BigInteger q = new BigInteger(in.readUTF());
                BigInteger root = new BigInteger(in.readUTF());
                System.out.println("q: " + q);
                System.out.println("root: " + root);
                System.out.println("Enter xb: ");
                BigInteger xb = new BigInteger(sc.next());
                BigInteger yb = root.modPow(xb, q);
                BigInteger ya = new BigInteger(in.readUTF());
                out.writeUTF(yb + "");
                BigInteger key = ya.modPow(xb, q);
                System.out.println("key: " + key);
            }  
            else if (choice == 2){
                BigInteger ya = new BigInteger(in.readUTF());
                BigInteger q = new BigInteger(in.readUTF());
                BigInteger root = new BigInteger(in.readUTF());
                int flag = 0; 
                for (BigInteger i= BigInteger.ZERO; i.compareTo(q) == -1; i = i.add(BigInteger.ONE)){
                    if (root.modPow(i, q).compareTo(ya) == 0){
                        System.out.println("xa: " + i);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0)
                    System.out.println("None");
            }
            else if (choice == 3){
                s = ss.accept();
                out = new DataOutputStream(s.getOutputStream());
                in = new DataInputStream(s.getInputStream());
                BigInteger q = new BigInteger(in.readUTF());
                BigInteger root = new BigInteger(in.readUTF());
                System.out.println("q: " + q);
                System.out.println("root: " + root);
                System.out.println("Enter xb: ");
                BigInteger xb = new BigInteger(sc.next());
                BigInteger yb = root.modPow(xb, q);
                BigInteger yd = new BigInteger(in.readUTF());
                out.writeUTF(yb + "");
                BigInteger key = yd.modPow(xb, q);
                System.out.println("key: " + key);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new ServerDiffie(4200);
    }
    
}
