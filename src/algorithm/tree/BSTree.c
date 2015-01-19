
// if get the key, return it; no, return the inserting position
boolean searchBST(BSTree *t, int key, BSTree *p, BSTree *f){
	if(!t){
		f=p;
		return false;
	}
	else{
		if(key == t->data) {f=t; return true}
		else if(key < t->data) return searchBSTree(t->lchild,key,t,f);
		else return searchBSTree(t->rchild,key,t,f);
	}
}

boolean insertBST(BSTree *t, int key){
	BSTree *p = null;
	if(!searchBST(t,key,NULL,p)){
		BNode *s = (BNode *) malloc(sizeof(BNode));
		s->data = key;
		s->lchild = NULL;
		s->rchild = NULL;
		if(!p) t = s;
		else if(p->data > key) p->lchild = s;
		else p->rchild = s;
		return true;
	}else return false;
}

boolean deleteBST(BSTree *t, int key){
	if(!t) return false;
	else{
		if(t->data == key) {
			delete(t);
			return true;
		}
		else if(key > t->data) return deleteBST(t->rchild,key);
		else return deleteBST(t->lchild,key);
	}
}
void delete(BSTree *p){
	if(!p->rchild){
		q=p; p=p->lchild;free(q);
	}else if(!p->lchild){
		q = p; p = p->rchild;free(q);
	}else {
		q = p; s = p->lchild;
		while(!s->rchild){
			q = s;
			s = s->rchild;
		}
		p->data = s->data;
		if(q != p) q->rchild = s->lchild;
		else p->lchild = s->lchild;
		free(s); 
	}
}

// can't work for duplicates insertion
BSTree *createBST(int array[], int n ){
	BSTree *t=NULL;
	for(int i=0;i<n;++i){
		insertBST(t, array[i]); 
	}
	return t;
}



