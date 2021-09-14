primes = [False, False] + [True] * 1000000
    
for i in range(2, 1001):
    if primes[i] == True:
        for j in range(i + i, 1000001, i):
            primes[j] = False

while True :
    a = int(input())

    if(a == 0) :
        break
    left = 2
    right = a - 2

    check = True
    while True :
        if(primes[left] & primes[right]) :
            print(a,'=',left,'+',right)
            check = False
            break
        left += 1
        right -= 1

    if(check) :
        print("Goldbach's conjecture is wrong.")
    

