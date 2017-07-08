/**
 * Created by yrafiyev on 2017-07-08.
 * 576. Out of Boundary Paths
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or
 * cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times.
 * Find out the number of paths to move the ball out of grid boundary.
 * The answer may be very large, return it after mod 10^9 + 7.
 */

/* Runtime: O(N*m*n), Space: O(N*m*n), Space efficiency can be improved to O(m*n) since only previous grid state
  needed at each step */

public class OutOfBoundary {
    public int findPaths(int m, int n, int N, int i, int j) {
        int [][][] grid = new int[N+1][m][n]; // N - moves remaining, m - current row, n - current column

        if(N == 0) return 0;

        //Base case
        for(int k = 0; k < m; k++){
            for(int l = 0; l < n; l++){
                grid[1][k][l] = 0;
                if(k == 0)
                    grid[1][k][l] += 1;
                if(k == (m-1))
                    grid[1][k][l] += 1;
                if(l == (n-1))
                    grid[1][k][l] += 1;
                if(l == 0)
                    grid[1][k][l] += 1;

            }
        }

        for(int s = 2; s <= N; s++){
            for(int k = 0; k < m; k++){
                for(int l = 0; l < n; l++){
                    //Corner case
                    grid[s][k][l] = 0;
                    if(k != 0){
                        grid[s][k][l] += grid[s-1][k-1][l] ;
                        grid[s][k][l] = grid[s][k][l] % (1000000007);
                    }
                    if(l != 0){
                        grid[s][k][l] += grid[s-1][k][l-1] ;
                        grid[s][k][l] = grid[s][k][l] % (1000000007);
                    }
                    if(l != (n-1)){
                        grid[s][k][l] += grid[s-1][k][l+1];
                        grid[s][k][l] = grid[s][k][l] % (1000000007);
                    }
                    if(k != (m-1)){
                        grid[s][k][l] += grid[s-1][k+1][l];
                        grid[s][k][l] = grid[s][k][l] % (1000000007);
                    }
                }
            }
        }

        int sum = 0;
        for(int move = 1; move < N + 1; move++){
            sum += grid[move][i][j] ;
            sum = sum % (1000000007);
        }
        return sum;

    }
}
