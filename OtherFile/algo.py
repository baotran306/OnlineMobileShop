n = int(input())
string = ""
for i in range(0, n):
    t = input()
    string += t
# print(string)

p = 0
q = len(string) - 1
ans = ""
while n > 0:
    if string[p] > string[q]:
        ans += string[q]
        q -= 1
    elif string[p] == string[q]:
        temp_p = p
        temp_q = q
        equal = False
        while string[temp_p] == string[temp_q]:
            if temp_p >= temp_q:
                equal = True
                break
            temp_p += 1
            temp_q -= 1
        if equal:
            ans += string[p]
        elif string[temp_p] > string[temp_q]:
            ans += string[q]
            q -= 1
        else:
            ans += string[p]
            p += 1
    else:
        ans += string[p]
        p += 1
    n -= 1
if len(ans) <= 80:
    print(ans)
else:
    index = 1
    while index * 80 <= len(ans):
        print(ans[(index - 1) * 80: (index * 80)])
        index += 1
    if len(ans) % 80 != 0:
        print(ans[(index - 1) * 80: (index * 80)])
