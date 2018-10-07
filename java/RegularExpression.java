/**
https://leetcode-cn.com/problems/regular-expression-matching/description/
*/

class Solution {
    enum Mode {
        NORMAL,
        ANY,
        MULTI
    }
    class MatchedData {
        int pStart;
        int pEnd;
        int sStart;
        int sEnd;
        Mode mode;
        public MatchedData(int ps, int pe, int ss, int se, Mode mode) {
            this.pStart = ps;
            this.pEnd = pe;
            this.sStart = ss;
            this.sEnd = se;
            this.mode = mode;
        }
        
        public int matchedLength() {
            return sEnd - sStart;
        }
    }

    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
          return s.length() == 0;
        }

        List<MatchedData> mList = new ArrayList();
        MatchedData mData = new MatchedData(0, 0, 0, 0, Mode.NORMAL);
        char pa[] = p.toCharArray();
        char sa[] = s.toCharArray();
        int paIndex = 0;
        int saIndex = 0;
        while (paIndex < pa.length) {
          if (pa[paIndex] == '*') {
            if (paIndex == 0 || mData.mode != Mode.NORMAL) {
              return false;
            }
            Mode m = (pa[paIndex - 1] == '.') ? Mode.ANY : Mode.MULTI;
            if (mData.matchedLength() > 1) {
              mData.sEnd--;
              mList.add(mData);
              mData = new MatchedData(paIndex - 1, paIndex + 1, saIndex - 1, saIndex, m);
            } else {
              mData.mode = m;
              mData.pEnd = paIndex + 1;
            }
            for (;saIndex < sa.length; saIndex++) {
              if (m == Mode.MULTI && sa[saIndex] != pa[mData.pStart]) break;
            }
            mData.sEnd = saIndex;
            paIndex++;
          } else {
            if (mData.mode != Mode.NORMAL) {
              mList.add(mData);
              mData = new MatchedData(paIndex, paIndex, saIndex, saIndex, Mode.NORMAL);
            }
            if (saIndex < sa.length && (pa[paIndex] == '.' || pa[paIndex] == sa[saIndex])) {
              paIndex++;
              saIndex++;
              mData.pEnd++;
              mData.sEnd++;
            } else if ((paIndex + 1) < pa.length && pa[paIndex + 1] == '*') {
              Mode m = (pa[paIndex] == '.') ? Mode.ANY : Mode.MULTI;
              if (mData.matchedLength() > 0) {
                mList.add(mData);
                mData = new MatchedData(paIndex, paIndex + 2, saIndex, saIndex, m);
              } else {
                mData.mode = m;
                mData.pEnd = paIndex + 2;
              }
              paIndex += 2;
            } else {
              int i = mList.size() - 1;
              for (; i >= 0; i--) {
                mData = mList.remove(i);
                if (mData.mode != Mode.NORMAL && mData.matchedLength() > 0) {
                  paIndex = mData.pEnd;
                  saIndex = mData.sEnd - 1;
                  mData.sEnd--;
                  break;
                }
              }
              if (i == -1) {
                return false;
              }
            }
          }
        }

        return paIndex == pa.length && saIndex == sa.length;
  }
}
