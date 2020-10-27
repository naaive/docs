# Sort

![big-o-cheat-sheet-poster](./big-o-cheat-sheet-poster.png)

## 冒泡排序

### 代码
```c++
vector<int> sortArray(vector<int> &nums) {
    auto len = nums.size();
    for (int i = 0; i < len; ++i) {
        bool flag = true;
        for (int j = 0; j < len - i - 1; ++j) {
            if (nums[j] > nums[j + 1]) {
                swap(nums, j, j + 1);
                flag = false;
            }
        }
        if (flag) {
            break;
        }
    }
    return nums;
}

void swap(vector<int> &nums, int i, int j) {
    auto tmp = nums[j];
    nums[j] = nums[i];
    nums[i] = tmp;
}
```
### 分析
- 时间复杂度：O(n^2)
- 空间复杂度：O(1)
- 稳定性：是（相等数据先后顺序不变）

## 插入排序

### 代码
```c++
vector<int> sortArray(vector<int> &nums) {
    auto len = nums.size();
    for (int i = 1; i < len; ++i) {
        insert(nums, 0, i - 1);
    }
    return nums;
}

/// 插入
/// \param vector
/// \param from 已排序区间from
/// \param to 已排序区间to
void insert(vector<int> &vector, int from, int to) {
    int newItem = vector[to + 1];
    for (int i = to; i >= from; --i) {
        if (newItem >= vector[i]) { // 稳定性
            vector[i+1] = newItem;
            break;
        }
        vector[i + 1] = vector[i];
    }
}

void swap(vector<int> &nums, int i, int j) {
    auto tmp = nums[j];
    nums[j] = nums[i];
    nums[i] = tmp;
}
```

### 分析

- 时间复杂度：O(n^2)
- 空间复杂度：O(1)
- 稳定性：是（相等数据先后顺序不变）

### vs 冒泡排序

```
//冒泡                swap(nums, j, j + 1);

//插入                vector[i + 1] = vector[i];
```
所以，插入排序比冒泡排序更好

## 选择排序

### 代码
```c++
vector<int> sortArray(vector<int> &nums) {
    auto len = nums.size();
    for (int i = 0; i < len; ++i) {
        int index = minValIndex(nums, i);
        swap(nums, i, index); //稳定性
    }
    return nums;
}

int minValIndex(vector<int> &vector, int from) {
    auto to = vector.size() - 1;
    int minIndex = from;
    for (int i = from + 1; i <= to; ++i) {
        if (vector[i] < vector[minIndex]) {
            minIndex = i;
        }
    }
    return minIndex;
}

void swap(vector<int> &nums, int i, int j) {
    auto tmp = nums[j];
    nums[j] = nums[i];
    nums[i] = tmp;
}
```

### 分析

- 时间复杂度：O(n^2)
- 空间复杂度：O(1)
- 稳定性：否（相等数据先后顺序改变）