#include <iostream>

int get_change(int m) {
  int coins[] = { 10, 5, 1 };

  int n = 0;
  int rem = m;
  for (int i = 0; i < 3; ++i) {
    n += rem / coins[i];
    rem %= coins[i];
  }
  
  return n;
}

int main() {
  int m;
  std::cin >> m;
  std::cout << get_change(m) << '\n';
}
