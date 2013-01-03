// need to summarize the basic concept of tree
int depth(Btree *t){
   if(t == null) return 0;
   else
     return 1+max(depth(t->lchild), depth(t->rchild));
}

// statistic of number of node on the tree   
int travelTree(Btree *t){
   if(t == null) return 0;
   else
	return travelTree(t->lchild)+travelTree(t->rchild)+1;
}

// post order traverse the tree and free all the node
int freeTree(Btree *t){
   if(t != null){
      if(freeTree(lchild))
      	if(freeTree(rchild))
      	  if(free(t)) return 1;
       return 0;
    }else return 1;
} 

// compare if two trees are identical     
int compareTree(Btree *t1, Btree *t2){
   if(t1 == null && t2 == null) return true;
   else if(t1 != null && t2 != null){
	    if(t1->data == t2->data && compareTree(t1->lchild, t2->lchild) && compareTree(t1->rchild, t2->rchild)) return true;
	     else return false;}
        else return false;
}

// return the smallest data in the sorted binary tree
int miniTree(BStree *t){
    if(t->lchild == null) return t->data;
    else return miniTree(t->lchild);
}

// mirror a tree to another copy or in place
Btree* mirrorTree(Btree *t){
   if(t != null){
	Btree *m = createNode();
	m->data = t->data; // can make it mirror in place
	m->rchild = mirrorTree(t->lchild); // t->rchild = mirrorTree(t->lchild)
	m->lchild = mirrorTree(t->rchild); // t->lchild = mirrorTree(t->rchild)
    	return m;
    }else return null;
}

// find the Nth item in a sorted binary search tree
BSTree *findN(BSTree *t, int *count, int N){
   BSTree* s;
   if(t != null){
	s = findN(t->lchild, count, N);
	if(s != null) return s;
        else {
	      *count += 1;
	      if(*count == N) return t;
              else {
		  s = findN(t->rchild, count, N);
		  if(s != null) return s;
		  else return null;
        }
    }else return null;
}
int main(){
int count = 0; s = findN(T, &count, N);
if(s != null) printNode(s);
else printf("tree size less than: %d \n"+N);
return 0;
}

// copy a tree to new place
BTree *copyTree(BTree *t){
    if(t != null){
	BTree *m = (BTree *) malloc(sizeof(BTree));
	m->data = t->data;
	m->lchild = copyTree(t->lchild);
	m->rchild = copyTree(t->rchild);
	return m;
    } else return null;
}

// check if a tree is sorted binary search tree
int checkBSTree(BTree *t){
    if(t != null){
	if(t->lchild != null && (t->lchild)->data > t->data) return 0;
	if(t->rchild != null && (t->rchild)->data < t->data) return 0;
	return checkBSTree(t->lchild) && checkBSTree(t->rchild);
    }else return 1;
}         