#include <iostream>

long long lcm_naive(int a, int b) {
  for (long l = 1; l <= (long long) a * b; ++l)
    if (l % a == 0 && l % b == 0)
      return l;

  return (long long) a * b;
}

int gcd_euklid(int a, int b) {
  if (b == 0) {
    return a;
  }

  int a1 = a % b;

  return gcd_euklid(b, a1);
}

long long lcm_by_gcd(int a, int b) {
  return (long long) (a / gcd_euklid(a, b)) * b;
}


int main() {
  int a, b;
  std::cin >> a >> b;
  std::cout << lcm_by_gcd(a, b) << std::endl;
  return 0;
}
