#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <iomanip>

using std::vector;
using std::pair;

bool compare_value_weight(pair<int, int> p1, pair<int, int> p2) {
  return (p2.first / (double) p2.second < p1.first / (double) p1.second);
}

double get_optimal_value(int capacity, vector<pair<int, int>> value_weight_pairs) {
  int n = value_weight_pairs.size();

  std::sort(value_weight_pairs.begin(), value_weight_pairs.end(), compare_value_weight);

  double value = 0.0;
  for (int i = 0; i < n; ++i) {
    double per_unit = value_weight_pairs[i].first / (double) value_weight_pairs[i].second;

    if (capacity == 0)
      return value;

    int a = std::min(value_weight_pairs[i].second, capacity);
    value += (double) a * per_unit;

    capacity -= a;
  }

  return value;
}

int main() {
  int n;
  int capacity;
  std::cin >> n >> capacity;
  vector<pair<int, int>> value_weight_pairs(n);
  for (int i = 0; i < n; i++) {
    std::pair<int, int> pair;

    std::cin >> pair.first >> pair.second;

    value_weight_pairs[i] = pair;
  }

  double optimal_value = get_optimal_value(capacity, value_weight_pairs);

  //std::cout.precision(4);
  std::cout << std::fixed;
  std::cout << std::setprecision(4) << optimal_value << std::endl;
  return 0;
}
