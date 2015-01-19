'''
solution searching techniques:
- use backtracking way is pre-traverse a state tree of the problem

void getPowerSet(int i, List A, List &B){
    if(i > A.size()) print(B);
    else {
        x = A.get(i); 
        k = B.size();
        B.add(x);
        getPowerSet(i+1,A,B);
        B.remove(k+1,x);
        getPowerSet(i+1,A,B);
    }
}
'''
# one liner version: x = [1,2,3....] -- O(N*2^N)
f1 = lambda x: [[y for j, y in enumerate(set(x)) if (i >> j) & 1] for i in range(2**len(set(x)))]

f2 = lambda l: reduce(lambda z, x: z + [y + [x] for y in z], l, [[]])

''' Nat approach,  O(2^n)
java iterative way in growing way:
public ArrayList<String> getPowerSet(char[] list){
    StringBuffer set=new StringBuffer();
    int n = list.length;
    ArrayList<String> powerset = new ArrayList<String>(2^n);
    set.append("");
    powerset.add(set);
    int count = 1, c;
    char = a;
    for(int i=0;i<n,++i){
        a = list[i];
        c=0;
        for(int j=0;j<count;++j){
            set = powerset.get(j);
            set.append(a);
            powerset.add(set.toString());
            c++;
        }
        count += c;
    }
    return powerset;
}
'''