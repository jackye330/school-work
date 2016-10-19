package txz;

import java.util.LinkedList;
import java.util.Queue;  
import java.util.Scanner;

public class Demo {

  /**.
 ** @param args
  **/
  static int max = 99999;
  static int m;
  static int n;
  static int[][] Map = new int [8][8];
  static boolean[][][] vis = new boolean [8][8][4];
  static boolean[][] us = new boolean[8][8];//标记箱子推过的方向  人走过的点
  static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
  static Node st = new Node();
  static Node []arr1 = new Node[max];
  static Edg []arr2 = new Edg[max];
  static Edg a = new Edg();
  static Queue<Node> q = new LinkedList<Node>();
  static Queue<Edg> qq = new LinkedList<Edg>();
  static int numb = 0;
  static int num = 0;
  
  public static void bfs1() {
    boolean[][] us = new boolean[8][8];
    for (int i = 0;i < 8; i++) {
      for (int j = 0;j < 8;j ++) {
        us[i][j] = false;
      }
    }
    qq.offer(arr2[num++]);
    int index = 0;
    while ((a = qq.poll()) != null) {
      index = index + 1;
      

      for (int i = 0;i < 4; i++) {
        int xx;
        int yy;
        xx = a.x1 + dir[i][0];
        yy = a.y1 + dir[i][1];
        if (xx >= 0 && xx < n && yy >= 0 && yy < m) {
          if (xx == st.x1 && yy == st.y1) {
            final int x1 = st.x1 + dir[i][0];
            final int y1 = st.y1 + dir[i][1];
            if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < m) {
              if (! vis[x1][y1][i] && Map[x1][y1] != 1) { //能推且到达的点不是墙
                arr1[numb].x1 = x1;
                arr1[numb].y1 = y1;
                arr1[numb].xx = xx;
                arr1[numb].yy = yy;
                arr1[numb].t1 = st.t1 + 1;
                q.offer(arr1[numb++]);
                vis[x1][y1][i] = true ;
              }
            }
          } else if ( ! us[xx][yy] && Map[xx][yy] != 1) {
            us[xx][yy] = true ;
            arr2[num].x1 = xx;
            arr2[num].y1 = yy;
            qq.offer(arr2[num++]);
          }
        }
      }
    }
  }
  
  public static void bfs() { 
    //判断箱子是否被推到目标位置
    while (!q.isEmpty()) {
      q.poll();
    }
    arr1[numb] = st;
    q.offer(arr1[numb++]);
    while (!q.isEmpty()) {
      st = q.poll();
      if (Map[st.x1][st.y1] == 3) {
        System.out.println(st.t1);
        return;
      }
      arr2[num].x1 = st.xx;
      arr2[num].y1 = st.yy;
      bfs1();
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int t1;
    Scanner in = new Scanner(System.in);
    t1 = in.nextInt();
    while (t1-- != 0) {
      m = in.nextInt();
      n = in.nextInt();
      for (int i = 0;i < n; i++) {
        for (int j = 0;j < m; j++) {
          Map[i][j] = in.nextInt();
          if (Map[i][j] == 2) {
            st.x1 = i;
            st.y1 = j;
          }
          if (Map[i][j] == 4) {
            st.xx = i;
            st.yy = j;
          }
        }
      }
      st.t1 = 0;
      for (int i = 0;i < 8; i++) {
        for (int j = 0;j < 8; j++) {
          for (int p = 0;p < 4; p++) {
            vis[i][j][p] = false;
          }
        }
      }
      for (int i = 0;i < max; i++) {
        arr1[i] = new Node();
        arr2[i] = new Edg();
      }
      bfs();
    }
    in.close();
  }
}
