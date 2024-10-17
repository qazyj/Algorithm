import sys

input = sys.stdin.readline
inputString = input()
array = inputString.split(' ')

typeString = array[0]
del array[0]

for s in array:
    s = s.replace(",", '').replace(";", '').replace('\n','')

    print(typeString, end='')

    for i in range(len(s) - 1, 0, -1):
        if s[i].isalpha(): continue

        if s[i] == ']':
            print('[', end='')
        elif s[i] == '[':
            print(']', end='')
        else:
            print(s[i], end='')
    print(' ', end='')

    for i in range(len(s)):
        if s[i].isalpha():
            print(s[i], end='')

    print(';')


