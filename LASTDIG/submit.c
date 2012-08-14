#include <stdio.h>

int main() {
  int n, b, i, j, l, ldh;
  long in;
  scanf("%d", &n);
  for(i = 0; i < n; i++) {
    scanf("%d%ld", &b, &in);
    l = b % 10;
    if(in == 0) {
      printf("1\n");
      continue;
    }
    else if(in == 1) {
      printf("%d", l);
      continue;
    }
    j = 2;
    while(in > 1) {
      ldh = l;
      if(l == 0 || l == 1) {
        break;
      }
      else {
        if(in % j == 0) {
          in /= j;
          for(; j > 1; j--) {
            l *= ldh;
            l %= 10;
          }
          j = 2;
        }
        else {
          j++;
        }
      }
    }
    printf("%d\n", l);
  }
  return 0;
}
