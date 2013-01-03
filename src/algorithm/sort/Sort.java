/**
 * 
 */
package algorithm.sort;

import java.util.Arrays;

/**
 * @author AI
 *
 */
abstract class Base {
	protected void swap(int[] list,int i, int j){
		int temp;
		temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}
	protected abstract void sort(int[] list,int i, int j);
}

class QuickSort extends Base{
	public void sort(int[] list,int low, int high){
		int pivotloc;
		if(low < high){
			pivotloc = partition(list,0,list.length-1);
			sort(list,low,pivotloc-1);
			sort(list,pivotloc+1,high);
		}
	}
	
	protected int partition(int[] list,int low, int high){
		int pivot = list[low];
		while(low < high){
			while(low<high && list[high] >= pivot) --high;
			list[low] = list[high];
			while(low < high && list[low] <= pivot) ++low;
			list[high] = list[low];
		}
		list[low] = pivot;
		return low;
	}
	public static void main(String[] args){
		int[] list = {7,3,2,6,1,8,3,4,0,5,9};
		QuickSort qs = new QuickSort();
		qs.sort(list, 0, list.length-1);
		System.out.println(Arrays.toString(list));
	}
}

class MergeSort extends Base{
	protected void Merge(int a[], int b[],int i, int m, int n){
		int j, k;
		for(j = m+1, k = i;i<=m && j<=n;++k){
			if(a[i] <= a[j]) b[k] = a[i++];
			else b[k] = a[j++];
		}
		while(i<=m) b[k++] = a[i++];
		while(j<=n) b[k++] = a[j++];
	}
	
	protected void Msort(int a[], int b[], int s, int t){
		int temp[] = new int[s-t+1];
		if(s == t) b[s] = a[s];
		else {
			int m = (s+t)/2;
			Msort(a,temp,s,m);
			Msort(a,temp,m+1,t);
			Merge(temp,b,s,m,t);
		}
	}
	
	public void sort(int a[], int i, int j){
		Msort(a,a,i,j);
	}
	
	public static void main(String args[]){
		int a[] = {5,4,1,3,10,9,7,2,1};
		MergeSort m = new MergeSort();
		m.sort(a, 0, a.length);
		System.out.println(Arrays.toString(a));
	}
}

class HeapSort extends Base{
	protected void buildMaxHeap(int a[]){
		for(int i=a.length/2;i>=0;--i){
			heapify(a,i,a.length);
		}
	}
	
	protected void heapify(int a[],int i,int size){
		int largest = i;
		if( 2*i <= size && a[2*i] > a[i]) largest = 2*i;
		if(2*i+1 <= size && a[2*i+1] > a[i]) largest = 2*i+1;
		if (largest != i){
			swap(a,largest,i);
			heapify(a,largest,size);
		}
	}
	
	public void sort(int a[], int i, int j){
		buildMaxHeap(a);
		int size = a.length;
		for(int l=a.length;l>a.length-(j-i)+1;--l){
			swap(a,0,l);
			size =size - 1;
			heapify(a,1,size);	
		}
	}
	
	public static void main(String args[]){
		int a[] = {5,4,1,3,10,9,7,2,1};
		HeapSort h = new HeapSort();
		h.sort(a, 0, 5);
		System.out.println(Arrays.toString(a));
	}
}

public class Sort {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
