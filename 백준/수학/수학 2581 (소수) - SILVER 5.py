def sieve(n):
    s = [True] * (n + 1)
    s[:2] = [False, False]
    for i in range(2, int(n**0.5+1.5)):
        if s[i]:
            s[i*2::i] = [False] * ((n - i) // i)
    return [i for i, n in enumerate(s) if n]

m, n = [int(input()) for _ in range(2)]
ps = [x for x in sieve(n) if x >= m]
if ps:
    print(sum(ps))
    print(ps[0])
else:
    print(-1)
