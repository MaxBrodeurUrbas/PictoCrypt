
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.lang.model.util.ElementScanner6;
import java.util.ArrayList;


public class Trans_De_Cypher {
    
    public static void main(String[] args){
        
        BufferedImage img = null;
        BufferedImage toDecrypt = null;
        String password;
        int passwordValue;
        Scanner scan = new Scanner(System.in);
        String option = "";
        System.out.print("Do you want to encrypt or decrypt a file:");
        option = scan.nextLine();
        System.out.println("Please enter a password between 5 and 20 characters long");
        password = scan.nextLine();
        scan.close();
        passwordValue = HashPassword(password);
        System.out.println(passwordValue);
        option = option.toLowerCase();
        if(option.equals("encrypt")){
            File  file = new File ("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram.jpg");
            try {
                img = ImageIO.read(file);
            }
            catch(Exception e){
                System.out.println(e);
            }
        
            TransCypher(img,passwordValue);
    }else if(option.equals("decrypt")){
            try{
                toDecrypt = ImageIO.read(new File ("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram1.jpg"));
            }catch(Exception e){
                System.out.println(e);
            }   
            TransDeCypher(toDecrypt,passwordValue);
     }else
        System.out.println("input error");   
    }

    public static int HashPassword (String password) {
        int passwordValue = 0;
        
        for(int i=0; i<password.length(); i++)
            passwordValue += password.charAt(i)* (int) Math.pow(31.0, (double)(password.length()-1-i));
        
        return passwordValue;
    }
    
    public static void TransCypher(BufferedImage img, int key) {
        
        int w = img.getWidth();
        int h = img.getHeight();
        int total =  w*h;
        int counter = 0;
        int[] OneArrayValues = new int[total];
        
        //first pixel set to contain key value for later decryption checking
        img.setRGB(0, 0, HashPassword(key+""));
        System.out.println();
        key = key % total;
      
        if(key<0)
            key = -key;
        for(int row=0; row < h; row++) {
            for(int column=0; column < w; column++) {
                OneArrayValues[counter++] = img.getRGB(column, row);
            }
        }
      
        BufferedImage output = new BufferedImage(w, h, img.getType());
        
        counter = 0;
        int i = 1;
        for(int row=0; row < h ; row++) {
            for(int column=0; column < w; column++) {
                if(counter>= total) {
                    counter = i++;
                }
                output.setRGB(column, row, OneArrayValues[counter]);
                counter+=key;
            }
        }
        
        
        try {
            ImageIO.write(output, "jpg", new File("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram1.jpg"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        // TransDeCypher(output,key);
    }
    
    public static void TransDeCypher(BufferedImage img, int key) {
        
        int w = img.getWidth();
        int h = img.getHeight();
        int total =  w*h;
        int counter = 0;
        int[] OneArrayValues = new int[total];
        BufferedImage output = new BufferedImage(w, h, img.getType());
        boolean devisable = false;
        
        // if (img.getRGB(0,0) != HashPassword(key+"")){
        //     System.out.println("Your password was incorrect please try again.");
        //     return;
        // }
        
        if(key<0)
            key = -key;
        
        key %=total;
        int rest = total % key;
        int step = total/key;
        for(int row=0; row < h; row++)
            for(int column=0; column < w; column++)
                OneArrayValues[counter++] = img.getRGB(column, row);
        
        if(rest == 0)
            devisable = true;
        else
            step++;
        int i = 1;
        counter = 0;
        for(int row=0; row < h; row++)
            for(int column=0; column < w; column++) {
                if(counter>=total) {
                    counter = i++;
                    rest = total %key;
                }
                output.setRGB(column, row, OneArrayValues[counter]);
                if(devisable || rest > 0 ){
                    counter += step;
                    rest--;
                }
                else {
                    counter += step-1;
                }
            }
        try {
            ImageIO.write(output, "jpg", new File("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram2.jpg"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}

