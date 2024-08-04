## [Module 1 The programming challenges](https://www.coursera.org/learn/algorithmic-toolbox/home/module/1)


- [Learning Objectives](#learning-objectives)
- [Maximum Pairwise Product Programming Challenge](#maximum-pairwise-product-programming-challenge)
- [Notes](#notes)
- [Materials and resources](#materials-and-resources)
---


### Learning Objectives
- Practice implementing algorithms
- Practice testing and stress testing programs
- Compare fast and slow programs
- Practice solving programming challenges

---

### Maximum Pairwise Product Programming Challenge
Given a sequence of non-negative integers a<sub>1</sub>,...,a<sub>n</sub>, compute
max a<sub>i</sub> * a<sub>j</sub>, (1&le;i&ne;j&le;n).
Note that i and j should be different, though it may be the case that a<sub>i</sub> = a<sub>j</sub>.

**Input format.** The first line contains an integer n. The next line contains
n non-negative integers a<sub>1</sub>,...,a<sub>n</sub> (separated by spaces).

**Output format.** The maximum pairwise product.

**Constraints.** 2 j&le; n j&le; 2 * 10<sup>5</sup>; &nbsp; &nbsp; 0 &le; a<sub>1</sub>,...,a<sub>n</sub> &le; 2 * <sup>
5</sup>.

Sample
<br> Input:

```shell
10
7 5 14 2 8 8 10 1 2 3
```

Output:

```shell
140
```
Solution(slow and fast):
[Maximum Pairwise Product Problem](MaxPairwiseProduct.java)

---

### Notes

**Stress testing** is a program that generates random tests in an infinite loop, runs both your main solution
and a significantly different alternative solution on each test, and compares their results.
If the results differ, one solution is likely incorrect, allowing you to debug and fix the error.
This process ensures that mistakes are identified and corrected by comparing outputs from both solutions.
<br/>Example: [Maximum Pairwise Product Problem Stress Test](MaxPairwiseProductStressTest.java)

---

### Materials and resources

[What to do if your solution doesn’t work?](../../../assets/testing_guide.pdf)