/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */

/*Program to find longest compounded word*/
import java.io.*;
import java.util.*;


public class treeDemo {

    public static void main(String[] args) {
        String words[]={};
        ArrayList<String> list= new ArrayList<>();
        
        try{
            File file= new File("Input_01.txt");
            BufferedReader br= new BufferedReader(new FileReader(file));
            String s="";
            while((s=br.readLine())!=null){
                words=s.split("\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<words.length;i++){
            list.add(words[i]);
        }
        // ratcatdogcat
        String word1=longestCompound(words);
        System.out.println("Longest Compound word : " + word1);
        list.remove(word1);
        String word2=longestCompound(words);
        System.out.println("Second longest Compound word : " + word2);
        
    }
 
    
    private static String longestCompound(String[] words) {
        ArrayList<String> list2 = new ArrayList<String>();
        TreeMap<String, Integer> treeOfWords = new TreeMap<String, Integer>(new Comparator<String>() {
                public int compare(String l1, String l2) {
                return l1.length() - l2.length();
            }
        });
 
        for (int i = 0; i < words.length; i++) {
            treeOfWords.put(words[i], i);
            list2.add(words[i]);
        }
 
        System.out.println(treeOfWords);
        return findWord(treeOfWords, list2);
 
    }
    
    
 
    public static String findWord(TreeMap<String, Integer> treeOfWords, ArrayList<String> list2) {
        while (treeOfWords.size() > 0) {
            String word = treeOfWords.lastKey();
            treeOfWords.remove(word);
            list2.remove(word);
            if (isCompoundWord(word, list2))
                return word;
        }
        return "";
    }
    
    
    private static boolean isCompoundWord(String word, ArrayList<String> list2) {
        if (list2.contains(word))
            return true;
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if (isCompoundWord(prefix, list2)) {
                String remainder = word.substring(i, word.length());
                if (remainder.length() == 0)
                    return true;
                return isCompoundWord(remainder, list2);
            }
        }
        return false;
    }
}
