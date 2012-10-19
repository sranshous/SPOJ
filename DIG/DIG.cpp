#include <iostream>
using namespace std;

int main() {
    int num_cases;
    long long mod_by = 24*1000000007L;
    cin >> num_cases;

    long long n;
    long long answer;
    for(int i = 0; i < num_cases; i++) {
        cin >> n;
        long long x = (n*(n-1)) % (mod_by);
        long long y = (x*(n-2)) % (mod_by);
        long long z = (y*(n-3)) % (mod_by);
        answer = z / 24;
        cout << answer << endl;
    }

    return 0;
}
