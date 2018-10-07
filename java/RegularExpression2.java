/**
我们先来判断p是否为空，若为空则根据s的为空的情况返回结果。
当p的第二个字符为*号时，由于*号前面的字符的个数可以任意，可以为0，那么我们先用递归来调用为0的情况，就是直接把这两个字符去掉再比较，
或者当s不为空，且第一个字符和p的第一个字符相同时，我们再对去掉首字符的s和p调用递归，注意p不能去掉首字符，因为*号前面的字符可以有无限个；
如果第二个字符不为*号，那么我们就老老实实的比较第一个字符，然后对后面的字符串调用递归。
*/

class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') {
            if (isMatch(s, p.substring(2))) return true;
            return (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) 
                && isMatch(s.substring(1), p);
        } else {
            return (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) 
                && isMatch(s.substring(1), p.substring(1));
        }
  }
}
