package pict_encrypt;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class main {
	
	    public static void main(String[] args){
	    	
	    		BufferedImage img = null;
//		    int number;
	    		
//	    		System.out.println("poop");
//	    		
//	        Scanner scan = new Scanner(System.in);
//	        
//	        String fileName = scan.next();
	        
	        File  file = new File ("/Users/maxurbas/Desktop/stevejobs.jpg");
//	        String path = file.getAbsolutePath();
	     
	        //Checking type of File input.
	        try {
	        		img = ImageIO.read(file);
	        }
	        catch(Exception e){
	        		System.out.println(e);
	        }
	        
	       TransCypher(img,5000);
	        return;
	        
	    }
	    	
		public static void TransCypher(BufferedImage img, int key) {
			
			int w = img.getWidth();
			int h = img.getHeight();
			int total =  w*h;
			int counter = 0;
			int[] OneArrayValues = new int[total];
			
			for(int row=0; row < h; row++) {
				for(int column=0; column < w; column++) {
					OneArrayValues[counter++] = img.getRGB(column, row);
				}
			}	
			BufferedImage output = new BufferedImage(w, h, img.getType());
			
			counter = 0;
			int i = 1;		
			for(int row=0; row < h; row++) {
				for(int column=0; column < w; column++) {
					if(counter>= total) {
						counter = i++;
					}
					output.setRGB(column, row, OneArrayValues[counter]);
					counter+=key;
					
				}	
			}
			
//			try {
//				ImageIO.write(output, "jpg", new File("/Users/maxurbas/Desktop/name"));
//			}
//			catch(Exception e) {
//				System.out.println(e);
//			}
	    TransDeCypher(output,key);
		}
		
	public static void TransDeCypher(BufferedImage img, int key) {
		
		int w = img.getWidth();
		int h = img.getHeight();
		int total =  w*h;
		int counter = 0;
		int[] OneArrayValues = new int[total];
		
		for(int row=0; row < h; row++) 
			for(int column=0; column < w; column++) 
				OneArrayValues[counter++] = img.getRGB(column, row);
			
	
		
		BufferedImage output = new BufferedImage(w, h, img.getType());
		
		int step = total/key;
		int i = 0;
		for(int row=0; row < h; row++) 
			for(int column=0; column < w; column++) {
				if(counter>=total) {
					counter = i++;
					}
				output.setRGB(column, row, OneArrayValues[counter]);
				counter+=step;
				
			}
		try {
			ImageIO.write(output, "jpg", new File("/Users/maxurbas/Desktop/decrypted"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		}
}
	

