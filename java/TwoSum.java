/**
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

// 最直观能想到的两种方法：
// 1. 暴力解决，两层循环测试，复杂度O(N2)
// 2. 先排序，再通过前后两个index测试，复杂度等于排序的复杂度Nlog(N)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] elements = new int[length];
        System.arraycopy(nums, 0, elements, 0, length);
        Arrays.sort(elements);
        
        int start = 0;
        int end = length - 1;
        int tmp;
        while (start < end) {
            tmp = elements[start] + elements[end];
            if (tmp < target) {
                start++;
            } else if (tmp > target) {
                end--;
            } else {
                break;
            }
        }

        int[] result = new int[2];
        for (int i = 0; i < length; i++) {
            if (nums[i] == elements[start]) {
                result[0] = i;
                break;
            }
        }
        for (int j = length - 1; j > -1; j--) {
            if (nums[j] == elements[end]) {
                result[1] = j;
                break;
            }
        }
        
        return result;
    }
