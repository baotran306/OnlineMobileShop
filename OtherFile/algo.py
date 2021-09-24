def cong(n):
    if n == 0:
        return 0
    else:
        return cong(n // 10) + (n % 10)


kq = cong(3232131219)
print(kq)