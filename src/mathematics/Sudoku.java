package mathematics;

public class Sudoku {
//	 suruki validation
	public boolean isValid(char[][] board){
		boolean[][] row = new boolean[9][9];
		boolean[][] col = new boolean[9][9];
		boolean[][] block = new boolean[9][9];
		
		for(int i=0;i<9;++i)
			for(int j=0;j<9;++j){
				row[i][j] = false;
				col[i][j] = false;
				block[i][j] = false;
			}
		
		for(int i=0;i<9;++i){
			for(int j=0;j<9;++j){
				if(board[i][j] != '.'){
					int c = board[i][j] - '1';
					int bi = (i/3)*3+j/3;
					if(row[i][c] || col[j][c] || block[bi][c])
						return false;
					else
						row[i][c] = col[j][c] = block[bi][c] = true;
				}else continue;
			}
		}
		return true;
	}
	
	// generate sudoku plate - DFS
	public boolean generateSudoku(char[][] board, int index){
		if(index==81) {
			printout(board);
			return true;
		}
		else{
			int row = index/9;
			int col = index%9;
			boolean result = false;
			for(int k=1;k<=9;++k){
				char data = (char)(k+'0');
				if(isValid(board,row,col,data)){
					board[row][col] = data;
					if(generateSudoku(board,index+1))
						return true;
				}
			}
			return false;
		}
		
	}
}
