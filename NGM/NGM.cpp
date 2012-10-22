#include <iostream>
using namespace std;

#define MAX_NUM 2000000000

int main() {
    int n;
    cin >> n;

    if(n % 10 == 0)
        cout << "2";
    else {
        cout << "1" << endl;
        cout << (n%10) << endl;
    }
}
