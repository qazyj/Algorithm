s = input()
lst = []
leng = len(s)

for i in range(0,leng):
    lst.append(s[i:leng])

lst.sort()

for i in range(0,leng):
    print(lst[i])
