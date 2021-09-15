N=int(input())
list=[]
for i in range(N):
    list.append(input())

list.sort(key=len)
answer=0
for i in range(N):
    prefix=True
    for j in range(i+1,N):
        try:
            if list[j].index(list[i])==0:
                prefix=False
                break
        except:
            continue
    if prefix:
        answer+=1
print(answer)
