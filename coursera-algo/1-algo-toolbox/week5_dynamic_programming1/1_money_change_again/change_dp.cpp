#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

using std::vector;

int get_change(int m) {
  int coins[3] = { 1, 3, 4 };
  vector<int> amounts(m + 1, INT_MAX);
  amounts[0] = 0;
  for (auto c : coins) {
    for (int i = c; i <= m; i++) {
      amounts[i] = 1 + std::min(amounts[i], amounts[i - c]);
    }
  }
 
  return amounts[m];
}

int main() {
  int m;
  std::cin >> m;
  std::cout << get_change(m) << '\n';
}
