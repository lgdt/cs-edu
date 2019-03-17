#include <algorithm>
#include <iostream>
#include <vector>

using std::vector;

int count_element(vector<int> &a, int left, int right, int key) {
    int count = 0;
    for (int i = left; i < right; ++i) {
      if (a[i] == key) count++;
    }
    return count;
}

int get_majority_element(vector<int> &a, int left, int right) {
  if (left == right) return -1;
  if (left + 1 == right) return a[left];

  int half_size = (right - left) >> 1;
  int mid = left + half_size;

  int m1 = get_majority_element(a, left, mid);
  int m2 = get_majority_element(a, mid, right);

  if (m1 != -1) {
    if (count_element(a, left, right, m1) > half_size)
      return m1;
  }
  if (m2 != -1) {
    if (count_element(a, left, right, m2) > half_size)
      return m2;
  }

  return -1;
}

int main() {
  int n;
  std::cin >> n;
  vector<int> a(n);
  for (size_t i = 0; i < a.size(); ++i) {
    std::cin >> a[i];
  }
  std::cout << (get_majority_element(a, 0, a.size()) != -1) << '\n';
}
