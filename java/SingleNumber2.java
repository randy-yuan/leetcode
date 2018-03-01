/**
Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/**
此类题目需充分挖掘条件，利用位运算特性。
只需要对每一个bit位出现1的次数计数，出现3次清零。
需要2bit存储计数状态，3个状态：00, 01, 10
首先得到: ones = ones ^ nums[i]; if (twos == 1) then ones = 0; 最终转换为: ones = (ones ^ nums[i]) & ~twos
类似地:  twos = twos ^ nums[i]; if (ones' == 1) then twos = 0; 最终转换为: twos = (twos ^ nums[i]) & ~ones
ones'就是最终的结果
*/

class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
}
