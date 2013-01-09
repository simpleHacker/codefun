'''
google-interview-questions 33 Answers

we will name a number "aggregated number" if this number has the following attribute:
just like the Fibonacci numbers
1,1,2,3,5,8,13.....

the digits in the number can divided into several parts, and the later part is the sum of the former parts.

like 112358, because 1+1=2, 1+2=3, 2+3=5, 3+5=8
122436, because 12+24=36
1299111210, because 12+99=111, 99+111=210
112112224, because 112+112=224
so can you provide a function to check whether this number is aggregated number or not?
'''
def checkAggNum(num):
    digits = str(num);
    len = len(digits);
    for long in range(1,len(digits)/2):
        for i in range(len(digits),long):
            ind2 = i+long
            if ind2 < len:
                num1 = digits[i:ind2]
            else :
                break
            ind3 = ind2+long
            if ind3 < len:
                num2 = digits[ind2:ind3]
                if num2 < num1:
                    ind3 += 1
                    long2 = long + 1
            elif ind3 == len:
                return true
            else :
                break
            ind4 = ind3+long2
            if ind4 < len:
                num3 = digit[ind3:ind4]
                if num3 < num2 and ind4+1 < len:
                    ind4 += 1
                    long3 = long2+1
                    num3 = digit[ind3:ind4]
                elif ind4+1 >= len:
                    break      
                if num1+num2 == num3:
                    continue
                else:
                    break
                long = long2
    return false