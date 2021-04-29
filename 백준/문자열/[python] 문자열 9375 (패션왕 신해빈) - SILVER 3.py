testcase = int(input())
for i in range(testcase):
    clothes = []
    input2 = int(input())
    
    for i in range(input2):
        a, b = map(str, input().split())

        clothes.append(b)
    cloth = list(set(clothes))
    count = []
    for i in cloth:
        count.append(clothes.count(i))
    result = 1
    for key in count:
        result *=  (key+1)
    print(result-1)
