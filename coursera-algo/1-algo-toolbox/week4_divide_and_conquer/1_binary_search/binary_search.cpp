#include <iostream>
#include <cassert>
#include <vector>

#include <random>
#include <algorithm>

using std::vector;

int binary_search_iter(const vector<int> &a, int left, int right, int x) {
  while (left <= right) {
    long mid = left + ((right - left) >> 1);

    if (a[mid] < x) {
      left = mid + 1;
    } else if (a[mid] > x) {
      right = mid - 1;
    } else {
      return mid;
    }
  }

  return -1;
}

int binary_search_rec(const vector<int> &a, int left, int right, int x) {
  if (left > right) 
    return left;

  int mid = left + ((right - left) >> 1);

  if (x <= a[mid]) {
    return binary_search_rec(a, left, mid - 1, x);
  } else {
    return binary_search_rec(a, mid + 1, right, x);
  }
}

int binary_search(const vector<int> &a, int x) {
  int left = 0, right = (int)a.size(); 
  
  int idx = binary_search_rec(a, left, right, x);
  if (idx >= a.size() || a[idx] != x)
    return -1;

  return idx;
}

int linear_search(const vector<int> &a, int x) {
  for (size_t i = 0; i < a.size(); ++i) {
    if (a[i] == x) return i;
  }
  return -1;
}

int main() {
  int n;
  std::cin >> n;
  vector<int> a(n);
  for (size_t i = 0; i < a.size(); i++) {
    std::cin >> a[i];
  }
  int m;
  std::cin >> m;
  vector<int> b(m);
  for (int i = 0; i < m; ++i) {
    std::cin >> b[i];
  }
  for (int i = 0; i < m; ++i) {
    std::cout << binary_search(a, b[i]) << ' ';
  }

/*
  int n = 10000;
  int max_num = 1000000000;

  std::random_device rseed;
  std::mt19937 rgen(rseed());
  std::uniform_int_distribution<int> dist(0, max_num);

  std::uniform_int_distribution<int> dist_ind(0, n - 1);

  vector<int> a(n);
  for (size_t i = 0; i < a.size(); i++) {
    a[i] = dist(rgen);
  }

  std::sort(a.begin(), a.end());

  for (size_t i = 0; i < a.size(); i++) {
    int idx = dist_ind(rgen);

    //std::cout << idx << '=' << a[idx];

    int res = binary_search(a, a[idx]);
    int res_lin = linear_search(a, a[idx]);
    std::cout << res << '=' << res_lin << ' ';
  }
  
*/
  return 0;
}
