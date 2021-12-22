package com.information;

import java.security.MessageDigest;
import java.sql.SQLOutput;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;


public class Hash {
    static Hashtable <String, String> ht = new Hashtable<String, String>();
    static String alpha = "abcdefghijklmnopqrstuvwxyz";
    static void hashGen(MessageDigest md){
        for (int i=0; i<alpha.length(); i++){
            for (int j=0; j<alpha.length(); j++){
                for (int k=0; k<alpha.length(); k++){
                    for (int l = 0; l<alpha.length(); l++){
                        String temp = String.valueOf(alpha.charAt(i)) + String.valueOf(alpha.charAt(j)) + String.valueOf(alpha.charAt(k)) + String.valueOf(alpha.charAt(l));
                        md.update(temp.getBytes());
                        byte hash[] = md.digest();
                        ht.put(temp, new String(hash));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        hashGen(md);
        Set<String> k = ht.keySet();
        while (true) {
            System.out.print("\nEnter choice: \n0. Exit\n1. Hashing\n2. Dictionary attack\n");
            int choice = sc.nextInt();
            if (choice == 0)
                break;
            else if (choice == 1) {
                System.out.print("Enter a word to hash(length: 4)");
                String word = sc1.nextLine();
                System.out.println("word: " + word);
                System.out.println("hash: " + ht.get(word));
            }
            else if (choice == 2) {
                System.out.print("Enter hashed word: ");
                String hash = sc1.nextLine();
                for (String key : k) {
                    if (ht.get(key).equals(hash)) {
                        System.out.println("hashed word: " + hash);
                        System.out.println("de-hashed word: " + key);
                        break;
                    }
                }
            }
            else{
                System.out.println("Enter a valid choice");
            }
        }
    }
}
