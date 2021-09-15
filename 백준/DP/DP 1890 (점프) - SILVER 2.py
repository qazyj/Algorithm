import sys

input = sys.stdin.readline
n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
dp = [[0] * n for _ in range(n)]
dp[0][0] = 1

for y in range(n):
    for x in range(n):
        if y == n - 1 and x == n - 1:
            break
        if dp[y][x] >= 1:
            dy = y + arr[y][x]
            dx = x + arr[y][x]
            if 0 <= dy < n:
                dp[dy][x] += dp[y][x]
            if 0 <= dx < n:
                dp[y][dx] += dp[y][x]

print(dp[-1][-1])
