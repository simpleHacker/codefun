int preoder(BTree *t){
   if(t != null){
	if(visit(t))
	  if(preorder(t->lchild))
	     if(preorder(t->rchild))
		return 1;
	return 0; // this is one is just for judge if visit is sucess
    } else return 1;
}

int inorder(BTree *t){
    if(t != null){
	if(inorder(t->lchild))
	  if(visit(t))
	     if(inorder(t->rchild)
		return 1;
	return 0;
     } else return 1;
}

int proorder(BTree *t){
    if(t != null){
	if(proorder(t->lchild))
	  if(proorder(t->rchild)
	     if(visit(t))
		return 1;
	return 0;
     } else return 1;
}
O(N);