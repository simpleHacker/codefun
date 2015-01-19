 // Print the edges' node of the tree
 void printEdges(TNode *root){
 	if(!root) return;
 	else{
 		printf("%d ", root->data);
 		printLeftEdge(root->lchild,true);
 		printRightEdge(root->rchild,true);
 	}
 }
 
 void printLeftEdges(TNode *node, boolean print){
	if(!node) return;
	else{
		if(print){
			printf("%d ", node->data);
			printLeftEdges(node->left,print);
			printRightEdges(node->right, (!print || !node->left)?true:false);
		}
	}
 }
 
 void printRightEdges(TNode *node, boolean print){
 	if(!node) return;
 	else{
 		if(print){
 			printLeftEdges(node->left, (!print || !node->right)?true:false);
 			printRightEdges(node->right,print);
 			printf("%d ",node->data);
 		}
 	}
 }

// convert a sorted linked list to a BSTree
TNode* listToTree(Node *low, Node *high){
	TNode *root = (TNode *) malloc(sizeof(TNode));
	if(low == high){
		root->data = low->data;
		root->lchild = null;
		root->rchild = null;		
	}else{
		Node *p1 = low, *p2 = p1->next, pre=p1;
		while(p2 != high && !p2){
			pre = p1;
			p1 = p1->next;
			p2 = p2->next->next;
		}
		root->data = p1->data;
		root->lchild = listToTree(low,pre);
		root->rchild = listToTree(p1->next, high);
	}
	return root;	
}



// find the largest BST in B tree
int findLBST(TNode *node, int min, int max, int *maxnode, TNode *maxTree, TNode *child){
	if(node == null) return 0;
	else{
		if(node->data >= min && node->data <= max){
			int leftnode = findLBST(node->left, min, node->data, maxnode, maxTree, child);
			TNode leftchild = (leftnode == 0)? NULL:child;
			int rightnode = findLBST(node->right, node->data, max, maxnode, maxTree, child);
			TNode rightchild = (rightnode == 0)?NULL:child;
			
			TNode *parent = (TNode *) malloc(sizeof(TNode));
			parent->left = leftchild;
			parent->right = rightchild;
			patent->data = node->data;
			
			child = parent;
			int total = leftnode + rightnode + 1;
			if(total > maxnode){
				maxTree = parent;
				*maxnode = total;
			}
			return total;
		} else{
			findLBST(node, INT_MIN, INT_MAX, maxnode, maxTree, child);
			return 0;
		}
	}
}

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
	    if(t1->data == t2->data) 
	    	return compareTree(t1->lchild, t2->lchild) && compareTree(t1->rchild, t2->rchild);
	    else return false;
	} else return false;
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

// find the Nth item in a sorted binary search tree -- in order traverse the tree
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
	int count = 0; 
	s = findN(T, &count, N);
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

// search BSTree
BSTree *searchBSTree(BSTree *t, int key){
      if(!t || t->data == key) return t;
      else if(t->data > key) return searchBSTree(t->lchild, key);
      else return searchBSTree(t->rchild, key);
}


// count total number of nodes in the tree
int count(BTree *t){
     int numl=0, numr=0;
     if(t != null){
		if(!(t->lchild) && !(t->rchild)) return 1;
		else{ 
	   		if(t->lchild != null) numl = count(t->lchild);
	   		if(t->rchild != null) numr = count(t->rchild);
	   		return numl+numr;
		}
     } else return 0;
}

// print the math expression in inorder traversal
char* expressionTree(BTree t){
	char *s, *tmp; 
	if(!t) return "";
	else{
		tmp = strcat(expressionTree(t->lchild),t->data);
		tmp = strcat(tmp,expressionTree(t->rchild));
		tmp = strcat("(",tmp); s = strcat(tmp,")");
		return s;
	}
}

// check the balancing of the tree
int maxdep(BTree *root){
	int l,r;
	if(t == null)
		return 0;
	else{
		l = maxdep(t->lchild);
		r = maxdep(t->rchild);
		return 1+(l>=r?l:r)
	}
}

int mindep(Btree *root){
	int l,r;
	if(root == null)
		return 0;
	else{
	  	l = mindep(t->lchild);
	  	r = mindep(r->rchild);
	  	return 1+(l<=r?l:r);
	}
}
boolean isBalance(Btree *root){
	if(root = null)
		return true;
	else
		return (maxdep(root) - mindep(root) < 1);
}
		
// create balanced binary tree from an sorted list, make the height minimal
BTree* createBSTree(int[] list, int low, int high){
	int mid = list[(high+low)/2];
	if(low > high)
		return null
	else{
	    BTree *node = (BTree *) malloc(sizeof(BTree));
		node->data = list[mid];
		node->lchild = createBSTree(list,low,mid-1);
		node->rchild = createBSTree(list,mid+1,high);
		return node;
	}
}

// create N lists for BSTree as level traverse
List** createLevelList(BSTree *t){
	SingleList list;
	ListList listlist;
	BSTree *pre = t, *tmp, *elem;
	initQueue(q);
	q.enQueue(t);
	
	while(!q.isEmpty()){
		tmp = q.deQueue();
		if(q.isEmpty()){
			list.next = tmp;
			list = list->next;
			q.enQueue(tmp.lchild);
			q.enQueue(tmp.rchild);
			pre = tmp.rchild;
			*(listlist++) = list;
		}else{
			if(tmp->lchild->data < pre->data){
				*(listlist++) = list;
				list = null;
			}
			list = tmp;
			q.enQueue(tmp.lchild);
			q.enQueue(tmo.rchild);
			pre = tmp.rchild;
		}
	}
	return listlist;
}

// find the next in BST
BSTree* findNext(BSTree *node){
    q = node-rchild
	if(q != null)
		while(q->lchild != null) q=q->lchild
		return q
	p = node->parent; q=node;
	if(p == null) return null;
	while(p->rchild == q) {q = p; p = p->patent;}
	if(p->parent == null) return null;
	else return p->parent;
}

// find common ansistor of two node on a binary tree
// use exclusion way to solve the problem, good
BSTree* findCommon(BSTree *t, BSTree *n1, BSTree *n2){
    if(t == null) return null;
    else{
		if(cover(t->lchild, n1) && cover(t->lchild,n2))
			return findCommon(t->lchild,n1,n2);
		else if(cover(t->rchild, n1) && cover(t->rchild,n2))
			return findCommon(t->rchild,n1,n2);
		else return t;
	}
}
boolean cover(BSTree *t, BSTree *n){
	if(t == null) return false;
	else{ 
		if(t == n)
			return true;
		else 
			return cover(t->lchild,n) || cover(t->rchild,n);
	}
} 

// match the sub tree, O(nm)
// or we can form the preorder and inorder sequence of two tree, then match s2 
// sequence in s1 the one sequences.
BSTree* matchSubTree(BSTree *s1, BSTree *s2){
	initQueue(q);
	q.add(s1);
	while(!q.isEmpty()){
		t = q.deQueue();
		if(match(t,s2)) return t;
		q.enQueue(t->lchild);
		q.enQueue(t->rchild);
	}
	return null;
}

boolean match(BSTree *t1, BSTree *t2){ // depth: O(m) in stack
	if(t1 == null && t2 == null){
	return true;
	}else if(t1 != null && t2 == null)
		return false;
	else if(t2 != null && t1 == null)
		return false;
	else{
		if(t1->data == t2->data)
			return match(t1->lchild, t2->lchild) && match(t1->rchild,t2->rchild);
		else
			return false;
	}
}

// Find the most frequently occurring element in a BST. 
// In this BST we can have leftnode<=rootnode<rightnode.
//!!! It's good habit to check the question with a small instance on board

Approach:
use in-order travesal, the same elem in in-order visit close to each other
so one pass with recursive also with recording so-far max info


void findMaxOccu(BSTree t, int *max, BSNode *maxp, int *count, BSNode * p){
	if(!t){
			return;
	}else{
		findMaxOccu(t->lchild, max, maxp, count, p);
		if(p == null) {
			p = t;
			count = 1;
		}
		else if(t->data != p->data && *count > *max){
			*max = *count;
			maxp = p;
			count = 1;
			p = t;
		}else{
			if(t->data == p->data)
				*count += 1; 
			else{
				p = t;
				*count = 0;
			}
		}
		findMaxOccu(t->rchild, max, maxp, count,p)
	}
	
// find the path between two nodes
// answer, find their lowest common ancester, then find the path to each node
BSNode* findCommAnc(BSTree T, BSNode *p1, BSNode *p2){
	if(!T) return null;
	else{
		if(cover(T->lchild,p1) && cover(T->lchild,p2)) 
			return findCommAnc(T->lchild,p1,p2);
		else if(cover(T->rchild,p1) && cover(T->rchild,p2))
			return findCommAnc(T->rchild,p1,p2);
		else return T;
	}
}

Stack findPath(BSTree T, BSNode *p1, BSNode *p2){
	BSNode *p = findCommAnc(T,p1,p2);
	Stack Q1 = path(p->lchild,p1,p2);
	Queue Q2 = path(p->rchild,p1,p2);
	if(!Q2.isEmpty())
		Q2.deQueue();
	Q1.push(p);
	Q1.push(Q2);
	return Q1;
}

// Approach II: Need some data structure assistant with pointer to parents
// check() can use binary search from when array sorted
void findPath2(BSNode *p1, BSNode *p2){
	int a1[n], a2[n];
	int i=0, j=0;
	
	while(p1 && p2){
		index = check(p1->data, a2);
		if(index >=0){
			print(a1, a2[index..0])
			break;
		}
		index = check(p2->data,a1);
		if(index >=0){
			print(a2, a1[index..0])
			break;
		}	
		a1[i++] = p1->data;
		a2[j++] = p2->data;

		p1 = p1->parent;
		p2 = p2->parent;
	}
}

TNode* findRightMCousin(Tree t, TNode *n)
{
	struct Queue q = initQueue();
	TNode *tag = (TNode *) malloc(sizeOf(TNode));
	tag->data = NULL;
	tag->rchild = NULL;
	tag->lchild = NULL;
	
	if(t == n) return NULL;
	enQueue(q,t); enQueue(q,tag);
	
	TNode *p;
	while(isEmpty(q)){
		p = deQueue(q);
		
		if(p == n){
			while(p != tag){
				p = deQueue(q);
			}
			return p;	
		} else{
			enQueue(q,p->lchild);
			enQueue(q,p->rchild);
			if(p == tag) enQueue(q,p);
		}
	}
	
	return NULL; // do not find the node n
}

BTree createBTree(int[] array, int low, int high){
	if(low<=high){
		int mid = (low+high)/2;
		TNode *node = (TNode *) malloc(sizeOf(TNode));
		node.data = array[mid];
		node.lchild = createBtree(array,low,mid-1);
		node.rchild = createBtree(array,mid+1,high);
		return node;
	} else{
		return null;
	}
}

