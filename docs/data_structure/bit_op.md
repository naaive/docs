# Bit OP



## Summary

`&`  

| Digit1 | Digit2 | Result |
| :----- | :----- | :----- |
| 1      | 1      | 1      |
| 1      | 0      | 0      |
| 0      | 0      | 0      |
| 0      | 1      | 0      |

`|`

| Digit1 | Digit2 | Result |
| :----- | :----- | :----- |
| 1      | 1      | 1      |
| 1      | 0      | 1      |
| 0      | 0      | 0      |
| 0      | 1      | 1      |

`^` (no carry plus one)

| Digit1 | Digit2 | Result |
| :----- | :----- | :----- |
| 1      | 1      | 0      |
| 1      | 0      | 1      |
| 0      | 0      | 0      |
| 0      | 1      | 1      |

```
a^b=b^a
(a^b)^c=a^(b^c)
```



## Swap

```java
private static void swap(int[] ints, int j, int i) {
  ints[j] = ints[j] ^ ints[i];
  ints[i] = ints[j] ^ ints[i];
  ints[j] = ints[j] ^ ints[i];
}
```



## Odd Times Num

```java
public static int getOdd(int[] arr) {
  int xor = 0;
  for (int i : arr) {
    xor = xor ^ i;
  }
  return xor;
}
```



## Odd Times 2 Num

```java
public static int[] get2Odd(int[] arr) {
  int xor = 0;
  for (int i : arr) {
    xor = xor ^ i;
  }
  int firstOne = xor & (~xor + 1);
  int eor = 0;
  for (int i : arr) {
    if ((i & firstOne) == firstOne) {
      eor = eor ^ i;
    }
  }

  return new int[]{eor, eor ^ firstOne};
}
```



## M,K

```java
public static int getmk(int[] arr, int m, int k) {
  int[] byts = new int[32];
  for (int i : arr) {
    for (int j = 0; j < 32; j++) {
      byts[j] = ((i >> j) & 1) + byts[j];
    }
  }
  int ans = 0;
  for (int i = 0; i < byts.length; i++) {
    byts[i] = byts[i] % m == 0 ? 0 : 1;
    ans = ans | ((byts[i]) << i);
  }
  return ans;
}
```

