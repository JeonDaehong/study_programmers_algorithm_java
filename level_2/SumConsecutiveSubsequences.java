package level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    프로그래머스 Level 2 : 연속된 부분 수열의 합

    날짜 : 2024-03-04

    [설명]
    비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
        - 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
        - 부분 수열의 합은 k입니다.
        - 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
        - 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
    수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때,
    위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return 하는 solution 함수를 완성해주세요.
    이때 수열의 인덱스는 0부터 시작합니다.


    [ 입출력 예시 ]
    [1, 2, 3, 4, 5]에서 합이 7인 연속된 부분 수열은 [3, 4]뿐이므로 해당 수열의 시작 인덱스인 2와 마지막 인덱스 3을 배열에 담아 [2, 3]을 반환합니다.


    [ 풀이 시간 ]
    총 풀이시간 : 2035 ~ 2109
        - 문제 분석 ( 2035 ~ 2051 )
        - 손 코딩 ( X )
        - 슈도코드 [ 원초적 설계 -> 알고리즘 ] ( 2051 ~ 2101 )
        - 코드 구현 ( 2102 ~ 2109 )


    [ 사용한 알고리즘 ]
    투 포인터 알고리즘을 사용하여 풀이하였음.

 */

public class SumConsecutiveSubsequences {
    public static int[] solution(int[] sequence, int k) {
        int lt = 0;
        int rt = 0;
        int sum = 0;
        int interval = 0;
        int minInterval = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while ( lt < sequence.length ) {
            if ( sum == k ) {
                if ( minInterval > interval ) {
                    left = lt;
                    right = rt;
                    minInterval = interval;
                }
            }
            if ( sum < k && rt < sequence.length ) {
                sum += sequence[rt++];
                interval++;
            } else {
                sum -= sequence[lt++];
                interval--;
            }
        }
        return new int[]{left, right-1};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];
        for ( int i=0; i<n; i++ ) {
            sequence[i] = Integer.parseInt(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());
        int[] answer = solution(sequence, k);
        for ( int x : answer ) {
            System.out.print(x + " ");
        }
    }
}
