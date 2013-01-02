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

// 
Btree* mirrorTree(Btree *t){
   if(t != null){
	Btree *m = createNode();
	m->data = t->data; // can make it mirror in place
	m->rchild = mirrorTree(t->lchild); // t->rchild = mirrorTree(t->lchild)
	m->lchild = mirrorTree(t->rchild); // t->lchild = mirrorTree(t->rchild)
    	return m;
    }else return null;
}