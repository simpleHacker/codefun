'''
back tracking with branch cut
void Trial(int i, int n){
// when into this call, on n*n chessboard, i-1 rows has i-1 chesspieces all good 
    if(i>n) printout(chessboard layout);
    else for (j=1;j<=n;++j){
        cb[i][j] = 1;
        if(legal(layout, cb[][])) Trial(i+1,n);
        cb[i][j] = 0;
    }
}
'''

'''
by filter the possibility:
1, constrains each queen to a single column or row - 8^8
2, check the diagonal attack - 8!
'''
from itertools import permutations

n = 8
cols = range(n)
for vec in permutations(cols):
    if(n == len(set(vec[i]+i for i in cols))
        == len(set(vec[i]-i for i in cols))):
        print vec
        
'''
This heuristic solves N queens for any N ≥ 4. 
It forms the list of numbers for vertical positions (rows) of queens 
with horizontal position (column) simply increasing. N is 8 for eight queens puzzle.

   1 If the remainder from dividing N by 6 is not 2 or 3 then the list is simply all even numbers followed by all odd numbers ≤ N
   2 Otherwise, write separate lists of even and odd numbers (i.e. 2,4,6,8 - 1,3,5,7)
   3 If the remainder is 2, swap 1 and 3 in odd list and move 5 to the end (i.e. 3,1,7,5)
   4 If the remainder is 3, move 2 to the end of even list and 1,3 to the end of odd list (i.e. 4,6,8,2 - 5,7,9,1,3)
   5 Append odd list to the even list and place queens in the rows given by these numbers, from left to right (i.e. a2, b4, c6, d8, e3, f1, g7, h5)

For N = 8 this results in the solution shown above. A few more examples follow.

    14 queens (remainder 2): 2, 4, 6, 8, 10, 12, 14, 3, 1, 7, 9, 11, 13, 5.
    15 queens (remainder 3): 4, 6, 8, 10, 12, 14, 2, 5, 7, 9, 11, 13, 15, 1, 3.
    20 queens (remainder 2): 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 3, 1, 7, 9, 11, 13, 15, 17, 19, 5.

'''