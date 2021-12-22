package diffie;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;
import java.util.ArrayList;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class IntruderDiffie{
    ServerSocket ss;
    Socket s;
    DataOutputStream out;
    DataInputStream in;
    public IntruderDiffie(String ip, int port1, int port2) throws Exception{
        while (true){
            ss = new ServerSocket(port1);
            Scanner sc = new Scanner(System.in);
            s = ss.accept();
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
            BigInteger q = new BigInteger(in.readUTF());
            BigInteger root = new BigInteger(in.readUTF());
            System.out.println("q: " + q);
            System.out.println("root: " + root);
            System.out.println("Enter xc: ");
            BigInteger xc = new BigInteger(sc.next());
            BigInteger yc = root.modPow(xc, q);
            BigInteger ya = new BigInteger(in.readUTF());
            out.writeUTF(yc + "");
            BigInteger key = ya.modPow(xc, q);
            System.out.println("key: " + key);
            s.close();
            ss.close();
            s = new Socket(ip, port2);
            System.out.println("blaa");
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
            out.writeUTF(q + "");
            out.writeUTF(root + "");
            System.out.println("Enter xd: ");
            BigInteger xd = new BigInteger(sc.next());
            BigInteger yd = root.modPow(xd, q);
            out.writeUTF(yd + "");
            BigInteger yb = new BigInteger(in.readUTF());
            key = yb.modPow(xd, q);
            System.out.println("key: " + key);    
        }
    }
    public static void main(String[] args) throws Exception{
        new IntruderDiffie("localhost", 3500, 4200);
    }
}