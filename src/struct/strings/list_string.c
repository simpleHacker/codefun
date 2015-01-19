// remove space
void remove(char *str){
	int index = 0;
	int p = index;
	while(str[index] != '\0'){
		if(str[index] == ' '){
			while(str[index] == ' ') index++;
			while(p < index && str[index] != ' ' && str[indx] != '\0'){
				str[p] = str[index];
				p++;
				index++;
			}
		} else{
			index++;
			p++;
		}
	}
	str[p] = '\0';
}

// insert into a cyclic linked sorted list	
		void insert(Node *aNode, int x) {
			  if (!aNode) {
			    aNode = new Node(x);
			    aNode->next = aNode;
			    return;
			  }
			 
			  Node *p = aNode;
			  Node *prev = NULL;
			  do {
			    prev = p;
			    p = p->next;
			    if (x <= p->data && x >= prev->data) break;   // For case 1)
			    if ((prev->data > p->data) && (x < p->data || x > prev->data)) break; // For case 2)
			  } while (p != aNode);   // when back to starting point, then stop. For case 3)
			 
			  Node *newNode = new Node(x);
			  newNode->next = p;
			  prev->next = newNode;
		}

void reverseStr(char *s, int pos){
// if the last char is '\0', should skip it
	int len = strlen(s);
	if(pos < len/2){
		char ch = s[pos]; // *(s+pos)
		s[pos] = s[len-pos-1]; // s[len-pos-2] for last char as '\0'
		s[len-pos-1] = ch;
		reverseStr(pos+1);
	}
}

LinkList reverseList(LinkList t){
	if(t->next){
		p = reverseList(t->next);
		t->next->next = t;
		return p;
	} else return t;
}

void switchList(LinkedList *t){
	if(t && t->next){
		p = t->next;
		temp = t->data;
		t->data = p->data;
		p->data = temp;
		switchList(p->next);
	}
}

// write a function to sum two single linked lists that represents binaries numbers
// need to align the p1 and p2 to make the after list has equal len.
int sum(int *p1, int *p2){
	if(!p1->next && !p2->next){
		p2->data = (p1->data + p2->data) %2;
		retrun (p1->data + p2->data)/2;
	} else{
		int up;
		if(!p1->next) up = sum(p1,p2->next);
		else if(!p2->next) up = sum(p2->next,p1);
		else up = sum(p1->next,p2->next,carry);
		p2->data = (p1->data + p2->data+up)%2;
		return (p1->data + p2->data+up)/2;
	}
}

// find all combination of "{", "}" -- can adopts to such of this problems
void combination(int count, int left, int right, char ch, char *com){
        com[count] = ch;
        if(left > 0){
           combination(count+1,left-1, right, '{', com);
           if(right>left)
                combination(count+1,left, right-1, '}', com);
        } else {
   			if(right > 0)
                combination(count+1,left, right-1, '}', com);
            else {
					printf("one option: %s\n",com);
					return;
			}
        }
}


void main(){
        int n = 4;
        char *com = (char *) malloc(2*n*sizeof(char));
        combination(n, 1,0,'{',com);
        free(com);
        return;
}

// find Y shapes: (this is a wrong one, need to correct!)
Linkedlist *findComm(LinkedList *l1, LinkedList *l2){
	len1 = strLen(l1);
	len2 = strLen(l2);
	p1 = l1; p2 = l2;
	Linkedlist *com = (int *) malloc(sizeof(int));
	p = com;
	if(len1>=len2)
		while(P1&&p2){
			if(p1->data == p2->data){
				Linkedlist *tmp = (int *) malloc(sizeof(int));
				tmp->data = p1->data;
				p->next = tmp;
				p = tmp;
				p1 = p1->next;
			}
			p2 = p2->next;
		}
	else
		while(P1&&p2){
			if(p1->data == p2->data){
				Linkedlist *tmp = (int *) malloc(sizeof(int));
				tmp->data = p2->data;
				p->next = tmp;
				p = tmp;
				p2 = p2->next;
			}
			p1 = p1->next;
		}
	if(strLen(com) == 1) return null;
	else return com;
}

// merge two sorted linked list
LList mergeLists(LList l1, LList l2){
	LNode *p1, *p2, *p, *head, * tmp;
	p1 = l1; p2 = l2;
	if(p1->data <= p2->data){ 
		head = l1;
		p = p1;
		p1 = p1->next;
	}else{
		head = l2;
		p = p2;
		p2 = p2->next;
	}
	while(p1 != null && p2 != null){
		if(p1->data <= p2->data){
			p->next = p1;
			p = p1;
			p1 = p1->next;
		}else{
			p->next = p2;
			p = p2;
			p2 = p2->next;
		}
	}
	if(p1 != null) p->next = p1;
	if(p2 != null) p->next = p2;
	return head;
}

void recur_merge(LNode *p1, LNode *p2, LNode *p){
	if(!p1){
		p->next = p2;
		return;
	} 
	if(!p2){
		p->next = p1;
		return;
	}
	
	if(p1->data <= p2->data){
		p->next = p1;
		p = p1;
		p1 = p1->next;
	} else{
		p->next = p2;
		p = p2;
		p2 = p2->next;
	}
	recur_merge(p1,p2,p);
}