// Hanoi tower, x,y,z can be in stack type
void hanoi(int n, char x, char y, char z){
	if(n == 1)
		move(n,x,z);
	else{
		hanoi(n-1,x,z,y);
		move(n,x,z);
		hanoi(n-1,y,x,z);
	}
}