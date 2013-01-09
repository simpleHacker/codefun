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
// O(N);

int levelTravser(BTree *t){
    if(t != null){
	Queue q = createQueue();
	enQueue(&q, t);
	while(!emptyQueue(q)){
	   BTree *s = deQueue(q);
	   if(!visit(s)) return 0;
           if(s->lchild != null) enQueue(q, s->lchild);
	   if(s->rchild != null) enQueue(q, s->rchild);
	}
	return 1;
     } return 1;
}

// Create a unique tree from two ordered string***!!!!
// how to create tree from a sequence string

BTree *createTree(char *pres, char *ins){
	if(pres != null && ins != null){
		char c = *pres;
		int index = ins.indexOf(c);
		char *inslsub, *insrsub, *preslsub, *presrsub;
		if(index > 0)
			inslsub = subString(ins, 0, index-1);
		else inslsub = null;
		if(index < ins.length)
			insrsub = subString(ins,index+1;ins.length);
		else insrsub = null;
		int pos = pres.indexOf(*(pres+index-1));
		if(pos >= 1)
			preslsub = subString(pres,1, pos);
		else preslsub = null;
		if(pos < pres.length)
			presrsub = subString(pres,pos+1, pres.length);
		else presrsub = null;

		BTree *t = (BTree *) malloc(sizeOf(BTree));
		t->data = c;
		t->lchild = createTree(preslsub, inslsub);
		t->rchild = createTree(presrsub, insrsub);
	else if(pre == null & ins == null) return null
	else exit(Wrong Strings!);

}