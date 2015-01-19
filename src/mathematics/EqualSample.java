package mathematics;

public class EqualSample {
//	sample random k from large N with equal probability	
/*
 * reservoir sampling
 */	
	public void esample(int stream[],int k){
		int array[] = new int[k];
		for(int i = 0;i<k;++i)
			array[i] = stream[i];
//		 replace elements with gradually decreasing probability	
		for(int i=k;i<stream.length;++i){
			int j = (int) Math.random()*i; // important: inclusive range
			if(j<k) array[j] = stream[i];
		}
	}
}
