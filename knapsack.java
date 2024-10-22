public class Knapsack {
    
    // Method to solve 0/1 Knapsack problem
    public static void sack(int[] wt, int[] val, int bwt) {
        int n = wt.length;
        int[][] dp = new int[n + 1][bwt + 1];

        // Initialize base cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0; // If the capacity is 0, value is 0
        }
        for (int j = 0; j <= bwt; j++) {
            dp[0][j] = 0; // If there are no items, value is 0
        }

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= bwt; j++) {
                int notPick = dp[i - 1][j]; // Not picking the current item
                int pick = Integer.MIN_VALUE;
                if (wt[i - 1] <= j) { // Picking the current item if it fits
                    pick = val[i - 1] + dp[i - 1][j - wt[i - 1]];
                }
                dp[i][j] = Math.max(notPick, pick); // Max of picking or not picking
            }
        }

        // Print the maximum value that can be obtained
        System.out.println("Maximum value in Knapsack = " + dp[n][bwt]);
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 5}; // Item weights
        int[] values = {3, 4, 5, 6}; // Item values
        int capacity = 5; // Maximum weight capacity of the knapsack

        sack(weights, values, capacity); // Call the sack method
    }
}

