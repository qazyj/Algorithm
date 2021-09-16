from collections import deque
import sys
input = sys.stdin.readline

dx = [-1,1,0,0]
dy = [0,0,-1,1]

def bfs(i, j, char):
    queue = deque()
    queue.append([i, j])
    chain.append([i, j])
    while queue:
        x, y = queue.popleft()
        for k in range(4):
            r = x + dx[k]
            c = y + dy[k]
            if r < 0 or r >= 12 or c < 0 or c >= 6 : continue
            if check[r][c] or s[r][c] != char: continue

            check[r][c] = True
            queue.append([r,c])
            chain.append([r,c])
def down():
    for i in range(6):
        for j in range(10, -1, -1):
            for k in range(11, j, -1):
                if s[j][i] != "." and s[k][i] == ".":
                    s[k][i] = s[j][i]
                    s[j][i] = "."
                    break

s = [list(input().strip()) for i in range(12)]
result = 0

while True:
    isChain = True
    check = [[False] * 6 for i in range(12)]
    for i in range(12):
        for j in range(6):
            if s[i][j] == "." or check[i][j]: continue

            check[i][j] = True
            chain = []
            bfs(i, j, s[i][j])

            if len(chain) > 3:
                isChain = False
                for x, y in chain:
                    s[x][y] = "."
    if isChain:
        break
    down()
    result += 1
print(result)
