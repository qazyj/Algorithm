import sys
input = sys.stdin.readline

N = int(input())
street = list(input())
dp = [1000000] * N
dp[0] = 0


def prev_position(x):
    if x == 'B':
        return 'J'
    elif x == 'O':
        return 'B'
    elif x == 'J':
        return 'O'

for i in range(1, N):
    prev = prev_position(street[i])

    for j in range(i):
        if street[j] == prev:
            dp[i] = min(dp[i], dp[j] + pow(i - j, 2))

if dp[N-1] == 1000000:
    print(-1)
else:
    print(dp[N-1])
