/**
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

// 利用XOR操作的特性: A ^ A = 0 & A ^ B = B ^ A
class Solution {
    public int singleNumber(int[] nums) {
        int length = nums.length;
        int value = nums[0];
        for (int i = 1; i < length; i++) {
            value = value ^ nums[i];
        }
        
        return value;
    }
}
