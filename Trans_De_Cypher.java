import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class Trans_De_Cypher {
	
	    public static void main(String[] args){
	    	
	    		BufferedImage img = null;
	        
	        File  file = new File ("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram.png");
	        try {
	        		img = ImageIO.read(file);
	        }
	        catch(Exception e){
	        		System.out.println(e);
	        }
	        
		   TransCypher(img,2145);
		   BufferedImage toDecrypt = null;
		   try{
		   toDecrypt = ImageIO.read(new File ("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram_Crypted.jpg"));
		   }catch(Exception e){
			   System.out.println(e);
		   }
		   TransDeCypher(toDecrypt,2145);
	    	return;
	        
	    }
	    	
	  	 public static int HashPassword (String password) {
	    		int passwordValue = 0;
	    	
	    		for(int i=0; i<password.length(); i++)
	    			passwordValue += password.charAt(i)* Math.pow(31.0, (double)(password.length()-1-i));
	    		
	    		return passwordValue;
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
			
			try {
				ImageIO.write(output, "jpg", new File("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram_Crypted.jpg"));
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
		int rest = total % key;
		BufferedImage output = new BufferedImage(w, h, img.getType());
		boolean devisable = false; 
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
		System.out.println(total);
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
			ImageIO.write(output, "jpg", new File("C:\\Users\\Carl\\Desktop\\Carl_ElKhoury_SequenceDiagram1.jpg"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("The Operation is a success");
	}
}
	
