'''
use branch cutting based back tracking tecniques
void Trial(int i, int n){
    if(i>=n) output arrangement of current plate
    else for(j=1;j<n;++j){
        plate[i][j] = 1;
        if(legal(plate)) Trial(i+1,n);
        plate[i][j] = 0;
     }   
}
'''

# try each one step and check status, also each row or column only can place one queen
# can only use 1-D array, to record queen position
ColumnForRow[0:8] = 0 #is the array which stores the column number for each row

def check(row):
    for i in range(row):
        diff =  abs(ColumnForRow[i] - ColumnForRow[row])
        if diff==0 or diff == row - i:
            return false
    return true

def placeQueen(row):
    if row == 8:
        printBoard();
        return;
    for i in range(8):
        ColumnForRow[row] = i
        if check(row):
            placeQueue(row+1)
