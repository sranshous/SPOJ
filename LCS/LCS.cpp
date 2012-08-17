#include <iostream>
#include <string>
using namespace std;

int LCSdp(string, string);
int LCSsuffixtree(string, string);
void print2D(int**, int, int);

int main() {
    string line1, line2;
    cin >> line1 >> line2;
    int LCS = LCSdp(line1, line2);
    cout << LCS << endl;

    return 0;
}

// DP solution. O(n^2) time and space complexity
int LCSdp(string first, string second) {
    int fsize = first.length() + 1;
    int ssize = second.length() + 1;

    //cout << "fsize: " << fsize << "\tssize: " << ssize << endl;

    int length = 0;

    // allocate 2d array
    int **dp = new int*[fsize];
    for(int i = 0; i < fsize; i++)
        dp[i] = new int[ssize];

    for(int i = 1; i < first.length()+1; i++) {
        for(int j = 1; j < second.length()+1; j++) {
            if(first[i] == second[j]) {
                dp[i][j] = dp[i-1][j-1] + 1;
                length = dp[i][j] > length ? dp[i][j] : length;
            }
            else {
                dp[i][j] = 0;
            }
        }
    }

    //print2D(dp, fsize, ssize);

    // deallocate 2d array
    for(int i = 0; i < fsize; i++)
        delete [] dp[i];
    delete [] dp;

    return length;
}

void print2D(int **arr, int height, int width) {
    for(int i = 0; i < height; i++) {
        for(int j = 0; j < width; j++) {
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}
