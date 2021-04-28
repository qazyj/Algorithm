n = input()
n = sorted(n, reverse=True)
sum = 0
if '0' not in n:	# 우선은 input의 디폴트인 string으로 받았기에 '0' 문자로 적음
    print(-1)
else:
    for i in n:
        sum += int(i)
    if sum % 3 != 0 :	# 3의 배수 체크
        print(-1)
    else :
        print(''.join(n))
