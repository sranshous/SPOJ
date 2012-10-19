#include <stdio.h>

int main() {
    int num_cases, i;
    long long mod_by = 24*1000000007L;
    scanf("%d", &num_cases);

    long long n;
    long long answer;
    for(i = 0; i < num_cases; i++) {
        scanf("%lld", &n);
        long long x = (n*(n-1)) % (mod_by);
        long long y = (x*(n-2)) % (mod_by);
        long long z = (y*(n-3)) % (mod_by);
        answer = z / 24;
        printf("%lld\n", answer);
    }

    return 0;
}
