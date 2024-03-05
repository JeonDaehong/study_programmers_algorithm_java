package level_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
    프로그래머스 Level 2 : 미로 탈출

    날짜 : 2024-03-05

    [설명]
    1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다.
    각 칸은 통로 또는 벽으로 구성되어 있으며, 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다.
    통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데, 이 문은 레버를 당겨서만 열 수 있습니다.
    레버 또한 통로들 중 한 칸에 있습니다.
    따라서, 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다.
    이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다.
    미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때,
    최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.
    미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때,
    미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요.
    만약, 탈출할 수 없다면 -1을 return 해주세요.


    [ 입출력 예시 ]
    ["SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"]	16
    ["LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"]	-1


    [ 풀이 시간 ]
    총 풀이시간 : 2021 ~ 2129
        - 문제 분석 ( 2021 ~ 2040 )
        - 손 코딩 ( 2041 ~ 2055 )
        - 슈도코드 [ 원초적 설계 -> 알고리즘 ] ( 2055 ~ 2107 )
        - 코드 구현 ( 2107 ~ 2129 )


    [ 사용한 알고리즘 ]
    BFS 알고리즘을 사용하여 풀이하였음.

 */

class Pos {
    int x, y;
    public Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class MazeFind {
    static char[][] map;
    static int[][] depth;
    static int width;
    static int height;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0 ,0};
    public static int solution(String[] maps) {

        int answer = 0;

        width = maps[0].length();
        height = maps.length;

        // init map
        map = new char[height][width];
        depth = new int[height][width];

        for(int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = maps[i].charAt(j);
            }
        }
        for(int i=0; i<height; i++) Arrays.fill(depth[i], -1);

        Pos startPos = findPoint('S', map);
        Pos leverPos = findPoint('L', map);
        Pos endPos = findPoint('E', map);

        bfs(startPos, leverPos);
        answer = depth[leverPos.y][leverPos.x];

        if(answer == -1) return -1;

        depth = new int[height][width];
        for(int i=0; i<height; i++) Arrays.fill(depth[i], -1);
        bfs(leverPos, endPos);

        if(depth[endPos.y][endPos.x] == -1) return -1;
        answer += depth[endPos.y][endPos.x];

        return answer;
    }
    public static void bfs(Pos start, Pos endPoint){
        Queue<Pos> q = new LinkedList<>();
        q.add(start);
        depth[start.y][start.x] = 0;

        if(start.x == endPoint.x && start.y == endPoint.y)
            return;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >= 0 && nx < width && ny >=0 && ny < height && map[ny][nx] != 'X' && depth[ny][nx] == -1){
                    q.add(new Pos(ny, nx));
                    depth[ny][nx] = depth[cur.y][cur.x] + 1;
                }

                if(endPoint.x == nx && endPoint.y == ny) return;
            }

        }
    }
    public static Pos findPoint(char findWord, char[][] map) {
        for ( int i=0; i<map.length; i++ ) {
            for ( int j=0; j<map[0].length; j++ ) {
                if ( map[i][j] == findWord ) return new Pos(i, j);
            }
        }
        return new Pos(-1, -1);
    }
    public static void main(String args[]) {
        System.out.println(solution(new String[]{"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"}));
        System.out.println(solution(new String[]{"EOOOO","XXXXX ","LOOSO","XXXXX","OOOOX"}));
    }
}
