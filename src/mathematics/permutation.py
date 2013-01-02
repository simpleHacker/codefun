
def permut(prefix,postfix):
    if len(postfix)<= 1:
        print prefix+postfix
    else:
        for i in range(len(postfix)):
            newStr = prefix+postfix[i]
            permut(newStr,postfix[:i]+postfix[i+1:])
            
# one liner
from itertools import permutations, islice
items = "0123456789"
print next(islice(permutations(items), 999999, 999999 + 1))