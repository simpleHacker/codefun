package mathematics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * quadratic function
 * feature:
 *  1, symetric axle: -b/2a
 *  2, extreme pole: P(-b/2a，(4ac-b^2)/4a)
 *  3, direction: a>0, up; a<0, down;
 *  4, join: b^2-4ac>0, two; b^2-4ac=0, one; b^2-4ac<0, zore
 *  
 * @author AI
 *
 */

public class QuadraticF {
	
/**
 * question1: 
 * Input: A list X of n numbers sorted in non descending order, and the parameters a, b and c.
 * Output: A sorted list of W=a*X2 + b*X + c.
 */	
	private int special;//the symetric element
    private int a=0,b=0,c=0;
//compute the value of a*X2 + b*X + c      
    public int function(int A,int a,int b,int c){
  	  return a*(A*A)+b*A+c;
    }
    
//  read arry A[] from the file and form the arry W[n]= a*X[n]2 + b*X[n] + c,return the symetric element position in W[]
    public int arryW(int[] W,int[] A,String filename){
  	  String line = null;
  	  int flag = 0;
  	  int i=0,j=0;
  	  int single = 0;
  	  try{
  		  BufferedReader in = new BufferedReader(new FileReader(filename));
  		  while((line=in.readLine())!=null){
  			  if(i==0){//read the first line in the file
  				  StringTokenizer t = new StringTokenizer(line," ");
  				  a = Integer.valueOf(t.nextToken()).intValue();
  				  b = Integer.valueOf(t.nextToken()).intValue();
  				  c = Integer.valueOf(t.nextToken()).intValue();
  				  i++;
  			  }
  			  else {
  				  A[j] = Integer.valueOf(line).intValue();
  				  W[j] = function(A[j],a,b,c);
  				  //find the element that is close to the symmetry axis, and record it
  				  if (flag == 0 && a!=0){ special = A[j]; single = j; flag=1;}
  				  else {
  					  if(a>0 && Math.abs(A[j]+b/(2*a))<Math.abs(special+b/(2*a))){
  						  special = A[j];
  						  single = j;
  					  }
  				  }
  				  j++;
  			  }
  		  }
  		  
  	  }catch(FileNotFoundException e){
  		  System.err.println("file not found");
  	  }catch(IOException e){
  		  System.err.println("IO has problem");
  	  }
  	  return single;
    }
    
//	the main function of the sort algorithm
    public void sort(int[] W,int[] T,int n,int single){
  	  int i=single,j=single+1,k;
  	  if(a<0){
  		 k = n-1; 
  	     for(;i>=0 && j<n;--k){
  		    if(W[i]>W[j])
  			   T[k]=W[i--];
  		    else 
  			   T[k]=W[j++];
  	     }
  	     for(;i>=0;--k) T[k]=W[i--];
  	     for(;j<n;--k) T[k]=W[j++];
  	  }else if(a>0){
  		  k = 0;
  		  for(;i>=0 && j<n;++k){
    		    if(W[i]<W[j])
    			   T[k]=W[i--];
    		    else 
    			   T[k]=W[j++];
    	     }
    	     for(;i>=0;++k) T[k]=W[i--];
    	     for(;j<n;++k) T[k]=W[j++];
  	  }else{ 
  		  if(b>=0) for(k=0;k<n;++k) T[k] = W[k];
  		  if(b<0) for(i=0,k=n-1;i<n;--k) T[k]=W[i++]; 
  	  }  
  	  
    }

}
