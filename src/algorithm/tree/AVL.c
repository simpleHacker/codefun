// balanced binary tree
// number of node in left is about equal to number of node in right
// depth difference is only within 1

typedef struct BSTNode{
	elemtype data;
	int bf;
	struct BSTNode *lchild, *rchild;
}BSTNode, *BSTree;

void R_Rotate(BSTree &p){
	BSTNode *lc = p->lchild;
	p->lchild = lc->rchild;
	lc->rchild = p;
	p = lc;
}

void L_Rotate(BSTree &p){
	BSTNode *rc = p->rchild;
	p->rchild = rc->lchild;
	lc->lchild = p;
	p = rc;
}

void LeftBalance(BSTree T){
	BSTNode *lc = T->lchild;
	swich(lc->bf){
		case 1: T->bf = lc->bf = 0;
			R_Rotate(T); break;
		case -1: rd = lc->rchild;
			swich(rd->bf){
				case 1: T->bf = -1; lc->bf = 0; break;
				case 0: T->bf = lc->bf = 0; break;
				case -1: T->bf = 0; lc->bf = 1; break;
			}
			rd->bf = 0;
			L_Rotate(lc);
			R_Rotate(T);
	}
}

void Rightbalance( BSTree T){
	
}

Status insertAVL(BSTree &T, ElemType e, Boolean &taller){
	if(!T){
		T = (BSTree) malloc(sizeof(BSTNode));
		T->data = e;
		T->bf = 0;
		T->lchild = T->rchild = NULL;
	}else{
		if(T->data == e){
			taller = false; 
			return 0;
		}else if(e<T->data){
			if(!insertAVL(T->lchild,e,taller)) return 0;
			if(taller)
				swich(T->bf){
					case 1: LeftBalance(T), taller = false; break;
					case 0: T->bf = 1; taller = true; break;
					case -1: T->bf = 0; taller = false; break;
				}
		}else{
			if(!insertAVL(T->rchild,e,taller)) return 0;
			if(taller)
				swich(T->bf){
					case 1: T->bf = 0; taller = false; break;
					case 0: T->bf = -1; taller = true; break;
					case -1: RightBalance(T); taller = false; break;
				}
		}// else					
	}//else
	return 1;
}
