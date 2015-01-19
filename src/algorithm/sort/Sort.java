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
			pivotloc = partition(list,low,high);
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
	
	/*
	 * quick sort on a Linked list
	 * 
	 * void quickSort(LList list, LNode *low, LNode *high){
		if(low != high){
			LNode *pos = partition(list,low,high);
			quickSort(list,low,pos);
			if(pos->next != high)
				quickSort(list,pos->next->next,high);
		}
	}
		
		
	LNode* partition(LList list, LNode *low, LNode *high){
		LNode *pivot = high, *p=low, *q = low;
		
		while(p != pivot){
			if(p->data > pivot->data){
				if(q != p){
				   q->next = p->next;
				   pivot->next = p;
				   p->next = null;
				   p = q->next;
				} else{
					q = p->next;
					pivot->next = p;
					p->next = null;
					p = q;
				}
			}else{
				q = p;
				p = p->next;
			}
		}
		return q;
			
	}
	 */
}

class MergeSort extends Base{
	
// recursive version. space complexity O(n)
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
		int[] temp;
		if(s == t) b[s] = a[s];
		else {
			temp = new int[s-t+1];
			int m = (s+t)/2;
			Msort(a,temp,s,m);
			Msort(a,temp,m+1,t);
			Merge(temp,b,s,m,t);
		}
	}
	
	public void sort(int a[], int i, int j){
		Msort(a,a,i,j);
	}
	
// iterative version. space complexity: O(n)
	public void interativeMsort(int[] a){
		for(int i=1;i<=a.length/2+1;i*=2){
			for(int j=i;j<a.length;j+=2*i)
				iMerge(a,j-i,j,Math.min(j+i, a.length));
		}
	}
	
	public void iMerge(int[] a, int start, int middle, int end){
		int[] b = new int[end-start+1];
		int i=start, j=middle, k=0;
		while(i<middle && j<end){
			b[k++] = a[i]<=a[j]?a[i++]:a[j++];
		}
		while(i<middle) b[k++] = a[i++];
		while(j<end) b[k++] = a[j++];
		a = Arrays.copyOfRange(b,start,end);
	}
	
	public static void main(String args[]){
		int a[] = {5,4,1,3,10,9,7,2,1};
		MergeSort m = new MergeSort();
		m.sort(a, 0, a.length);
		System.out.println(Arrays.toString(a));
	}
}

class Heap extends Base{
	// O(n/2*logn)
	protected void buildMaxHeap(int a[]){
		for(int i=a.length/2;i>=0;--i){
			heapify(a,i,a.length);
		}
	}
	// up down way
	protected void heapify(int[] a,int i,int size){
		int largest = i;
		if( 2*i <= size && a[2*i] > a[largest]) largest = 2*i;
		if(2*i+1 <= size && a[2*i+1] > a[largest]) largest = 2*i+1;
		if (largest != i){
			swap(a,largest,i);
			heapify(a,largest,size);
		}
	}
	
	public void sort(int a[], int i, int j){
		buildMaxHeap(a);
		int size = a.length;
		for(int l=a.length;l>=0;--l){
			swap(a,0,l);
			size =size - 1;
			heapify(a,0,size);
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
	
	// unstable O(N)
	// pigeonhole sort
	public void linearSort(int[] array){
		int[] assist = new int[N];
		Arrays.fill(assist, 0);
		for(int i=0;i<array.length;++i){
			assist[array[i]]++;
		}
		int k=0;
		for(int i=0;i<N;++i){
			while(assist[i] > 0)
				array[k++] = i;
				assist[i]--;
		}
	}
	
	// stable O(N)
	// counting sort
	public int[] countSort(int[] array){
		int[] assist = new int[N];
		int[] newa = new int[array.length];
		Arrays.fill(assist, 0);
		Arrays.fill(newa, 0);
		
		for(int i=0;i<array.length;++i){
			assist[array[i]]++;
		}
		
		for(int i=1;i<N;++i){
			assist[i] = assist[i-1]+1;
		}
		
		for(int i=array.length-1;i>=0;--i){
			newa[assist[array[i]]--] = array[i];
		}
		return newa;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
