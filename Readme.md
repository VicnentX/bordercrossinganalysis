# Border Crossing Analysis

## Table of Contents
1. [Code Clarification](README.md#code clarification)
1. [Time and Space Complexity Analysis](README.md#Time and Space Complexity analysis)

## Code Clarification
My solution is using basic data structure : map and list beside creating a new class named Item to store elements

**In my code**
1. in the first loop: get the value of each key of (border, measure and date combination)
2. in the second loop: put element of map into list
3. sort the list
4. in the third loop: set average of elements
5. sort the list against requirement
6. in the four loop: write into the report.csv

## Time and Space Complexity Analysis

assume m = # of input line
       n = # of size of map


1. in the first loop: O(m)
2. in the second loop: O(n)
3. sort the list: O(n*logn)
4. in the third loop: O(n)
5. sort the list against requirement: O(n*logn)
6. in the four loop: O(n)

Total time complexity = max(O(m) , n * logn)

Total space complexity = O(list size) = O(n)

https://www.dropbox.com/s/pbriwcovvk7s12u/Screenshot%202019-10-29%2023.59.21.png?dl=0
